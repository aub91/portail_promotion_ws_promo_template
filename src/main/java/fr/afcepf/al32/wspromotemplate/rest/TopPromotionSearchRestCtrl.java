package fr.afcepf.al32.wspromotemplate.rest;

import fr.afcepf.al32.groupe2.ws.wsPromoTemplate.dto.PromotionTemplateResultDto;
import fr.afcepf.al32.groupe2.ws.wsPromoTemplate.dto.TopPromotionTemplateResultDto;
import fr.afcepf.al32.groupe2.ws.wsPromoTemplate.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import fr.afcepf.al32.wspromotemplate.service.itf.IPromotionSearchService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/rest/Template", headers="Accept=application/json")
public class TopPromotionSearchRestCtrl {

	@Autowired
	private IPromotionSearchService promotionSearchService;

	@Autowired
	private MongoOperations mongoOps;

	@GetMapping("/ByShopper")
	public TopPromotionTemplateResultDto getSearchByShopper(@RequestParam String sourceLong, @RequestParam String sourceLat, @RequestParam List<String> categories) {

		List<String> decodedCategories = categories.stream().map(category -> {
			try {
				String decodedCategory = URLDecoder.decode(category, "UTF-8");
				return String.format("'%s'", decodedCategory);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return "";
		}).collect(Collectors.toList());
		List<PromotionTemplateResultDto> result = promotionSearchService.searchTopPromotionByShopKeeper(Double.parseDouble(sourceLong), Double.parseDouble(sourceLat), decodedCategories);

		return new TopPromotionTemplateResultDto(Double.parseDouble(sourceLong), Double.parseDouble(sourceLat), decodedCategories, result);
	}

	/**
	 * Méthode servant à insérer deux entrées différentes dans la base de données MongoDB
	 * pour avoir un retour avec la méthode de recherche.
	 */
	@PostMapping("/create")
	public void createData() {
		Promotion promo = new Promotion(1L, "promo1", "promo1 desc", new Date(), Duration.ofDays(2L), Duration.ofHours(2), 50.0, false,
				new PercentType(1L, 20.0, 1.0), new Product(1L, 1L, 20.0, "produit1 desc", new Date(), "produit1", new ProductCategory("Prêt-à-porter", Arrays.asList("Homme", "Femme"), null)));

		promo.getShops().add(new Shop(1L, new Point(2.3571216637046, 48.8586639350234), 1L));
		promo.getReservations().add(new Reservation(1L, new Date(), 2.0, new Client(1L, new Point(-20.0, 125.0))));

		Promotion promo2 = new Promotion(2L, "promo2", "promo2 desc", new Date(), Duration.ofDays(5L), Duration.ofHours(3), 25.0, false,
				new Pack(2L, 3, 1), new Product(1L, 1L, 20.0, "produit1 desc", new Date(), "produit1", new ProductCategory("Prêt-à-porter", Arrays.asList("Homme", "Femme"), null)));

		promo2.getShops().add(new Shop(1L, new Point(2.3571216637046, 48.8586639350234), 1L));
		promo2.getReservations().add(new Reservation(1L, new Date(), 2.0, new Client(1L, new Point(-20.0, 125.0))));

		mongoOps.insert(promo, "promotions");
		mongoOps.insert(promo2, "promotions");

		Query query = new Query(Criteria.where("product.category.libelle").is("Prêt-à-porter").andOperator(Criteria.where("product.category.ancestors").is("Homme")));
		List<Promotion> promotions = mongoOps.find(query, Promotion.class, "promotions");
		promotions.forEach(x -> System.out.println(x.getId()));

	}
}
