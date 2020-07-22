package ro.msg.learning.shop.entity;


import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Address {

    private String country;

    private String city;

    private String streetAddress;

    public Address(){

    }

    public Address(String country, String city, String streetAddress){
        this.city=city;
        this.country=country;
        this.streetAddress=streetAddress;
    }
}
