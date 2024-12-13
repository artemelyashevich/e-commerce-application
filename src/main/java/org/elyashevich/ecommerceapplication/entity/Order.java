package org.elyashevich.ecommerceapplication.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
public class Order extends AbstractEntity {
    private Long id;

    private Long userId;

    private double totalAmount;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}