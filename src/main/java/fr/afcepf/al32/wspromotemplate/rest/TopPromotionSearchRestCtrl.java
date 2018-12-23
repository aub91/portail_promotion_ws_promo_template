package fr.afcepf.al32.wspromotemplate.rest;

import fr.afcepf.al32.wspromotemplate.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.afcepf.al32.wspromotemplate.service.itf.IPromotionSearchService;

import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value="/rest/Template", headers="Accept=application/json")
public class TopPromotionSearchRestCtrl {

	@Autowired
	private IPromotionSearchService promotionSearchService;

	@Autowired
	private MongoOperations mongoOps;

	@PostMapping("/ByShopper")
	public void getSearchByShopper() {
		promotionSearchService.searchTopPromotionByShopKeeper(null);
	}

	@PostMapping("/create")
	public void createData() {
		Promotion promo = new Promotion(1L, "promo1", "promo1 desc", new Date(), Duration.ofDays(2L), Duration.ofHours(2), 50.0, false,
				new PercentType(1L, 20.0, 1.0), new Product(1L, 1L, 20.0, "produit1 desc", new Date(), "produit1", new ProductCategory("Prêt à porter", Arrays.asList("Homme", "Femme"), null)));

		promo.getShops().add(new Shop(1L, new Point(20.0, 19.0), 1L));
		promo.getReservations().add(new Reservation(1L, new Date(), 2.0, new Client(1L, new Point(-20.0, 125.0))));

		mongoOps.insert(promo, "promotions");

		Query query = new Query(Criteria.where("product.category.libelle").is("Prêt à porter").andOperator(Criteria.where("product.category.ancestors").is("Homme")));
		List<Promotion> promotions = mongoOps.find(query, Promotion.class, "promotions");
		promotions.forEach(x -> System.out.println(x.getId()));

	}
}
