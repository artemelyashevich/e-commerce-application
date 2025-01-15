package org.elyashevich.ecommerceapplication.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "image")
    private String image;
}
