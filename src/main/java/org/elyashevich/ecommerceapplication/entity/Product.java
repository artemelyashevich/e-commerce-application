package org.elyashevich.ecommerceapplication.entity;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
public class Product extends AbstractEntity {

    private int id;

    private String name;

    private String description;

    private double price;

    private int categoryId;
}