package org.elyashevich.ecommerceapplication.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

    private String image;
}