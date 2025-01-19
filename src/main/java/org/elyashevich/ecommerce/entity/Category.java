package org.elyashevich.ecommerce.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
public class Category extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();
}
