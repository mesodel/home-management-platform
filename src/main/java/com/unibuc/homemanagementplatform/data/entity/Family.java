package com.unibuc.homemanagementplatform.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="FAMILY")
public class Family {
    @Id
    @Column(name="FAMILY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long familyId;

    @Column(name="FAMILY_NAME")
    private String familyName;

    @OneToMany(mappedBy = "family",cascade = CascadeType.PERSIST)
    private List<User> familyMembers;

}
