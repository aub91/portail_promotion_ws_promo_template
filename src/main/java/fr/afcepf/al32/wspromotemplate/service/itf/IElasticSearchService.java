package fr.afcepf.al32.wspromotemplate.service.itf;

import org.springframework.web.bind.annotation.RequestBody;

import fr.afcepf.al32.wspromotemplate.rest.SearchByShopperResponseDto;
import fr.afcepf.al32.wspromotemplate.rest.ShopperResquestDto;

public interface IElasticSearchService {
	SearchByShopperResponseDto searchByShopper(ShopperResquestDto shopperDto);
	
	


}
