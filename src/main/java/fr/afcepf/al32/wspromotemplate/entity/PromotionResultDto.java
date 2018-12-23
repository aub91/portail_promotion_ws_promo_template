package fr.afcepf.al32.wspromotemplate.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.DurationDeserializer;
import fr.afcepf.al32.wspromotemplate.util.PromotionTypeDeserializer;

import java.time.Duration;
import java.util.Date;

public class PromotionResultDto {
    private Long promotionId;

    private Date timestamp;

    private String name;

    private String description;

    @JsonDeserialize(using = DurationDeserializer.class)
    private Duration limitTimePromotion;

    @JsonDeserialize(using = DurationDeserializer.class)
    private Duration limitTimeTakePromotion;

    private Double reservedProductPercentage;

    @JsonDeserialize(using = PromotionTypeDeserializer.class)
    private PromotionType promotionType;

    private Product product;

    private Double dist;

    private int reservationsNumber;

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Duration getLimitTimePromotion() {
        return limitTimePromotion;
    }

    public void setLimitTimePromotion(Duration limitTimePromotion) {
        this.limitTimePromotion = limitTimePromotion;
    }

    public Duration getLimitTimeTakePromotion() {
        return limitTimeTakePromotion;
    }

    public void setLimitTimeTakePromotion(Duration limitTimeTakePromotion) {
        this.limitTimeTakePromotion = limitTimeTakePromotion;
    }

    public Double getReservedProductPercentage() {
        return reservedProductPercentage;
    }

    public void setReservedProductPercentage(Double reservedProductPercentage) {
        this.reservedProductPercentage = reservedProductPercentage;
    }

    public PromotionType getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(PromotionType promotionType) {
        this.promotionType = promotionType;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getDist() {
        return dist;
    }

    public void setDist(Double dist) {
        this.dist = dist;
    }

    public int getReservationsNumber() {
        return reservationsNumber;
    }

    public void setReservationsNumber(int reservationsNumber) {
        this.reservationsNumber = reservationsNumber;
    }
}
