package fr.afcepf.al32.wspromotemplate.dao;

import fr.afcepf.al32.wspromotemplate.entity.Promotion;
import fr.afcepf.al32.wspromotemplate.entity.PromotionResultDto;
import org.springframework.data.geo.Point;

import java.util.List;

public interface IPromotionDao {
    List<PromotionResultDto> getTopFivePromotionByShopKeeper(Point sourcePoint, String[] categories);
}
