package kz.fintech.dbservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BMC
 * @project fintech-cloud
 * @create_date 24.01.2024
 */
@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "public", name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    private String name;
    private Boolean edited;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_managers",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "manager_id", referencedColumnName = "id"))
    private List<ManagerEntity> manager = new ArrayList<>();

    public RoleEntity(long roleId) {
        this.id=roleId;
    }
}