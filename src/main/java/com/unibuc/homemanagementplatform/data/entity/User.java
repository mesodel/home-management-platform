package com.unibuc.homemanagementplatform.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USER")
public class User {

    @Id
    @Column(name = "USER_EMAIL")
    private String userEmail;

    @ManyToOne
    @JoinColumn(name = "FAMILY_ID", nullable = false)
    private Family family;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.PERSIST)
    private List<Task> tasks;


}
