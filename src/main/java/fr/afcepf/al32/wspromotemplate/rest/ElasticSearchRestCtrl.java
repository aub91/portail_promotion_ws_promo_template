package fr.afcepf.al32.wspromotemplate.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.afcepf.al32.groupe2.ws.dto.CategoryAndKeywordsDto;
import fr.afcepf.al32.groupe2.ws.dto.SearchByCategoryAndKeywordsResponseDto;
import fr.afcepf.al32.wspromotemplate.service.itf.IElasticSearchService;

@RestController
@RequestMapping(value="/rest/Template", headers="Accept=application/json")
public class ElasticSearchRestCtrl {

	@Autowired
	private IElasticSearchService elasticSearchService;

	@PostMapping("/ByShopper")
	public SearchByShopperResponseDto getSearchByShopper(@RequestBody ShopperResquestDto shopperDto) {
		return elasticSearchService.searchByShopper(shopperDto);
	}
}
