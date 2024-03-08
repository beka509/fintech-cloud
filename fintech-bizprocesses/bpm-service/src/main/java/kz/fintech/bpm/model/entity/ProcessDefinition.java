package kz.fintech.bpm.model.entity;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(schema="camunda", name = "act_re_procdef")
public class ProcessDefinition {
    @Id
    @Column(name = "key_")
    private String processDefinitionKey;

    @Column(name = "name_")
    private String processDefinitionName;

//    private List<ActHiProcinst> actHiProcinsts;
}
