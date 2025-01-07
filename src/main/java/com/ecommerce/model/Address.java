package com.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="address_id")
    private Long address_id;

    @Column(name="street")
    @NotBlank
    @Size(min = 5,message = "street should contain minimum 5 characters")
    private String street;

    @Column(name="city")
    @NotBlank
    @Size(min = 5,message = "city should contain minimum 5 characters")
    private String city;

    @Column(name="state")
    @NotBlank
    @Size(min = 5,message = "state should contain minimum 5 characters")
    private String state;

    @Column(name="country")
    @NotBlank
    @Size(min = 5,message = "country should contain minimum 5 characters")
    private String country;

    @Column(name="pincode")
    @NotBlank
    @Size(min = 6,message = "pincode should contain minimum 5 characters")
    private Integer pincode;


    public Address(String street, String city, String state, String country, Integer pincode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
    }
}
