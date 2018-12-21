package fr.afcepf.al32.wspromotemplate.entity;

import org.springframework.data.geo.Point;

import javax.persistence.Embeddable;

@Embeddable
public class Client {

    private Long client_id;

    private Point location;

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
