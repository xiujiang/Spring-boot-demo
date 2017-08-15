package com.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by joel on 2017/8/10.
 */
@Entity
@Table(name="person")
public class Person{
    @Id
    private int id;

    private int age;

    private String name;

    private Date createTime;

    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name="create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
