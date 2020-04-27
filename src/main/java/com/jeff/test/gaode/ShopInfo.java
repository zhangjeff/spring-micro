package com.jeff.test.gaode;

import java.util.List;

public class ShopInfo {

    private String count;

    private String status;

    private String infocode;

    private List<Poi> pois;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfocode() {
        return infocode;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }

    public void setPois(List<Poi> pois) {
        this.pois = pois;
    }

    public List<Poi> getPois() {
        return pois;
    }
}
