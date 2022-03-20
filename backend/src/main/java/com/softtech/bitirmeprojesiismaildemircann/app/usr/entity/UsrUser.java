package com.softtech.bitirmeprojesiismaildemircann.app.usr.entity;

import com.softtech.bitirmeprojesiismaildemircann.app.gen.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "USR_USER")
public class UsrUser extends BaseEntity {

    @Id
    @SequenceGenerator(name = "UsrUser", sequenceName = "USR_USER_ID_SEQ")
    @GeneratedValue(generator = "UsrUser")
    private Long id;

    @Column(name = "USERNAME", length = 100, nullable = false)
    private String username;

    @Column(name = "PASSWORD", length = 100, nullable = false)
    private String password;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "SURNAME", length = 100, nullable = false)
    private String surname;
}
