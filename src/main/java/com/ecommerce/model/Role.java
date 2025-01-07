package com.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name="role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Long role_id;

    @Column(name="rolename")
    @NotBlank
    private AppRole role_name;

    public Role(AppRole role_name) {
        this.role_name = role_name;
    }


}
