package fr.afcepf.al32.wspromotemplate.service.impl;

import fr.afcepf.al32.wspromotemplate.dao.IPromotionDao;
import fr.afcepf.al32.wspromotemplate.service.itf.IPromotionSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PromotionSearchService implements IPromotionSearchService {

    @Autowired
    private IPromotionDao promotionDao;

    @Override
    public void searchTopPromotionByShopKeeper(String shopperDto) {
        String[] array = {"\"Prêt à porter\""};
        promotionDao.getTopFivePromotionByShopKeeper(new Point(19.0, 20.0), array);
    }
}
