package fr.afcepf.al32.wspromotemplate.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PromotionType {

    @Column(name = "promotion_type_id")
    private Long promotionTypeId;

    public Long getPromotionTypeId() {
        return promotionTypeId;
    }

    public void setPromotionTypeId(Long promotionTypeId) {
        this.promotionTypeId = promotionTypeId;
    }
}
