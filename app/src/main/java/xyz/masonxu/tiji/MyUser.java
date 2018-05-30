package xyz.masonxu.tiji;

import cn.bmob.v3.BmobUser;

/**
 * Created by Mason on 2017/12/26.
 */

public class MyUser extends BmobUser {
    /**
     * 服务器上传数据
     */

    private Double BMI;
    private Double WHtR;
    private Double WHR;
    private Double Tizhi;

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
