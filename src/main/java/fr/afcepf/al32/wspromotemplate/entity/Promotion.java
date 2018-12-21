package fr.afcepf.al32.wspromotemplate.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

@Document(collection = "promotions")
public class Promotion {

    @Id
    private String id;

    private Long promotionId;

    private String name;

    private String description;

    @Column(name="date_of_creation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    @Column(name="date_of_remove")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRemove;

    @Column(name="limit_time_promotion")
    private Duration limitTimePromotion;

    @Column(name="limit_time_take_promotion")
    private Duration limitTimeTakePromotion;

    @Column(name="reserved_product_percentage")
    private Double reservedProductPercentage;

    @Column(name="is_cumulative")
    private Boolean isCumulative;

    @OneToMany(mappedBy = "promotionFK", fetch=FetchType.EAGER)
    @MapKey(name="id")
    private Map<Long, Shop> shops;
    
    @Embedded
    @Column(name="promotion_type")
    private PromotionType promotionType;

    @Embedded
    private Product product;

    @OneToMany(mappedBy = "promotionFK", fetch=FetchType.EAGER)
    @MapKey(name="id")
    private Map<Long, Reservation> reservations;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
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

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateRemove() {
        return dateRemove;
    }

    public void setDateRemove(Date dateRemove) {
        this.dateRemove = dateRemove;
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

    public Boolean getCumulative() {
        return isCumulative;
    }

    public void setCumulative(Boolean cumulative) {
        isCumulative = cumulative;
    }

    public Map<Long, Shop> getShops() {
        return shops;
    }

    public void setShops(Map<Long, Shop> shops) {
        this.shops = shops;
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

    public Map<Long, Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Map<Long, Reservation> reservations) {
        this.reservations = reservations;
    }
}
