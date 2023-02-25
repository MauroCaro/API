package co.com.vulky.infraestructure.data.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tbl_users")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user", nullable = false)
    private UUID id;

    @Column(name = "first_name_user", nullable = false, length = 20)
    private String firtsName;

    @Column(name = "last_name_user", nullable = false, length = 40)
    private String lastName;

    @Column(name = "age_user", nullable = false, length = 3, unique = true)
    private Integer age;

}
