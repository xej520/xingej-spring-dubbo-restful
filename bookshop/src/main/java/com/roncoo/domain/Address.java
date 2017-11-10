package com.roncoo.domain;

import javax.persistence.Embeddable;

/**
 * 一般地址信息，都会包括省，市，地区，具体的地址描述，邮编
 * 
 * 如果将这些一一写到Author类里的话，不太好；
 * 
 * 因此，单独提到一个类里，
 * 
 * 这是一种思路，其他类似的也是
 * 
 * @author erjun 2017年11月11日 上午6:03:36
 */

// 在多个对象里，嵌入进入
@Embeddable
public class Address {
    private String province;

    private String city;

    private String area;

    private String address;

    private String zipcode;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

}
