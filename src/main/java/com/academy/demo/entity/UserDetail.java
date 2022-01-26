package com.academy.demo.entity;

import javax.persistence.*;

@Entity
@Table(name="user_details")
public class UserDetail {

    @Id
    @Column(name="user_id")
    private Integer Id;

    @Column
    private String adress;

    @Column
    private Integer age;

    @Column(name="phone_number")
    private String phoneNumber;

    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}