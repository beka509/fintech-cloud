package kz.fintech.bpm.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema="camunda", name = "act_hi_procinst")
public class ActHiProcinst {

    @Id
    @Column(name = "proc_inst_id_")
    private String processInstanceId;

    @Column(name ="start_user_id_")
    private String startUserId;

    @Column(name = "start_time_")
    private Date startTime;

    @Column(name = "end_time_")
    private Date endTime;

    @Column(name = "state_")
    private String state;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "proc_def_key_", nullable = false)
    private ProcessDefinition processDefinition;
}