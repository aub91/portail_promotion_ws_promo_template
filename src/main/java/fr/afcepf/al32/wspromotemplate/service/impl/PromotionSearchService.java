package fr.afcepf.al32.wspromotemplate.service.impl;

import fr.afcepf.al32.wspromotemplate.dao.IPromotionDao;
import fr.afcepf.al32.wspromotemplate.service.itf.IPromotionSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionSearchService implements IPromotionSearchService {

    @Autowired
    private IPromotionDao promotionDao;

    @Override
    public void searchTopPromotionByShopKeeper(String shopperDto) {
        promotionDao.getTopTenPromotionByShopKeeper();
    }
}
