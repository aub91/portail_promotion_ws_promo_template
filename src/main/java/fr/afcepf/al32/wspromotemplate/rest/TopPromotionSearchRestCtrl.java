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
}
