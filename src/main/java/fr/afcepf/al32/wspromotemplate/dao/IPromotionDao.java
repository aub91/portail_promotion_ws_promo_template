package fr.afcepf.al32.wspromotemplate.dao;

import fr.afcepf.al32.wspromotemplate.entity.Promotion;

import java.util.List;

public interface IPromotionDao {
    List<Promotion> getTopTenPromotionByShopKeeper();
}
