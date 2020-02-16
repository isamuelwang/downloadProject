package com.owwang.yunzhang.pojo;

import java.io.Serializable;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * other
 * @author 
 */
@Table(name = "other")
@Entity
public class Other implements Serializable {
    @Id
    private Integer id;

    private String cookie;

    private static final long serialVersionUID = 1L;

    public Other() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}