package kz.fintech.dbservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * @author BMC
 * @project fintech-cloud
 * @create_date 24.01.2024
 */


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "REFRESH_TOKENS")
public class RefreshTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String token;
    private Instant expiryDate;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UsersEntity userInfo;

    public RefreshTokenEntity(int id) {
        this.id = id;
    }
}
