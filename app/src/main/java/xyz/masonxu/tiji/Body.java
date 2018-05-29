package xyz.masonxu.tiji;

import org.litepal.crud.DataSupport;

public class Body extends DataSupport {
    private int id;
    private Double BMI;
    private Double WHtR;
    private Double WHR;
    private Double Tizhi;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getBMI() {
        return BMI;
    }

    public void setBMI(Double BMI) {
        this.BMI = BMI;
    }

    public Double getWHtR() {
        return WHtR;
    }

    public void setWHtR(Double WHtR) {
        this.WHtR = WHtR;
    }

    public Double getWHR() {
        return WHR;
    }

    public void setWHR(Double WHR) {
        this.WHR = WHR;
    }

    public Double getTizhi() {
        return Tizhi;
    }

    public void setTizhi(Double tizhi) {
        Tizhi = tizhi;
    }
}
