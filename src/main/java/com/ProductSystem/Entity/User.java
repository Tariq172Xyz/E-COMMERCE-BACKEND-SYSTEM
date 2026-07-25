package com.ProductSystem.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false, unique = true)
    private String password;
    private String phoneNumber;
    @Column(nullable = false,unique = true)
    private String email;

    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    private List<Address> address;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Order> orders;


}
