package kz.fintech.dbservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "public", name = "scripts")
public class ScriptsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scriptsId;
    private String name;
    private String code;
}
