package com.ProductSystem.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long addressId;
    private String city;
    private String province;
    private String postalCode;
    private String country;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;


}
