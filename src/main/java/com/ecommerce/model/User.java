package com.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name="user",
uniqueConstraints = {
        @UniqueConstraint(columnNames="user_name"),
        @UniqueConstraint(columnNames="email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long user_id;

    @NotBlank
    @Size(min = 3, message = "username must contain atleast 3 characters")
    @Column(name = "username")
    private String user_name;

    @NotBlank
    @Email
    @Size(min = 5, message = "email must contain atleast 5 characters")
    @Column(name = "email")
    private String email;

    @NotBlank
    @Size(min = 5, message = "password must contain atleast 5 characters")
    @Column(name = "password")
    private String password;


    public User(String user_name, String email, String password) {
        this.user_name = user_name;
        this.email = email;
        this.password = password;
    }

    @Getter
    @Setter
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name="user_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    private Set<Role> roles=new HashSet<>();

    @Getter
    @Setter
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name="user_address",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="address_id")
    )
    private List<Address> addresses=new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private Set<Product> products=new HashSet<>();


}
