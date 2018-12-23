package fr.afcepf.al32.wspromotemplate.entity;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Shop {

    @Column(name = "shop_id")
    private Long shopId;

    @Indexed
    private Point location;

    @Column(name = "shopkeeper_id")
    private Long shopkeeperId;

    public Shop(Long shopId, Point location, Long shopkeeperId) {
        this.shopId = shopId;
        this.location = location;
        this.shopkeeperId = shopkeeperId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Long getShopkeeperId() {
        return shopkeeperId;
    }

    public void setShopkeeperId(Long shopkeeperId) {
        this.shopkeeperId = shopkeeperId;
    }
}
