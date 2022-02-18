package com.academy.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="user_details")
@NoArgsConstructor
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

    public UserDetail(String adress, Integer age, String phoneNumber)
    {
        this.setAdress(adress);
        this.setAge(age);
        this.setPhoneNumber(phoneNumber);
    }
}