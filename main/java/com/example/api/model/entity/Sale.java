package com.example.api.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 销售
 */
@Data
@Entity
@NoArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    //来往公司
    private String company;
    //打款账号
    private String number;
    //商品
    private String commodity;
    //数量
    private String count;
    //总价
    private double price;
    //预留电话
    private String phone;
    //备注
    private String description;
    //是否结款
    private boolean pay;
    //创建时间
    private String createAt;

}
