package org.elyashevich.ecommerceapplication.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@ToString(exclude = "password")
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "address")
    private String address;

    @Column(name = "image")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;
}
