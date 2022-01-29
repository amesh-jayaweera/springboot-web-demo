package com.example.web.entity;

import com.example.web.util.ERole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@Slf4j
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="name", nullable = false)
    private ERole name;

    @ManyToMany(
            mappedBy = "roles",
            fetch = FetchType.LAZY,
            targetEntity = User.class
    )
    @ToString.Exclude
    private Set<User> users;

    public Role() {
    }

    public Role(ERole name) {
        this.name = name;
    }
}