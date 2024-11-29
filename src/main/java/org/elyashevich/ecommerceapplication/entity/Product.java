package org.elyashevich.ecommerceapplication.entity;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
public class Product extends AbstractEntity {

    private Long id;

    private String name;

    private String description;

    private double price;

    private Long categoryId;
}