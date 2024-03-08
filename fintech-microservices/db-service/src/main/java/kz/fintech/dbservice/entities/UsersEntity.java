package kz.fintech.dbservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "public", name = "users")
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String fullName;
    private String position;
    private Boolean isActive;
    private String username;
    @JsonIgnore
    private String password;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id",referencedColumnName = "id")
    private RoleEntity role;
}
