package com.protto.api.data.entities;

import lombok.*;
import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_person")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long id;
    @Column(name = "person_firstName")
    private String firstName;
    @Column(name = "person_lastName")
    private String lastName;
    @Column(name = "person_email")
    private String email;
    @Column(name = "person_password")
    private String password;

}
