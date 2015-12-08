package com.pams.entities;
import javax.persistence.*;
/**
 * Created by MattBrown on 12/8/15.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    public String username;

    @Column(nullable = false)
    public String password;
}
