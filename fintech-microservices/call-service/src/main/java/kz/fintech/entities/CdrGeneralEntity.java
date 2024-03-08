package kz.fintech.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "cdr_general")
public class CdrGeneralEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start")
    private String start;

    @Column(name = "endtime")
    private String endTime;

    @Column(name = "answer")
    private String answer;

    @Column(name = "src_chan")
    private String srcChan;

    @Column(name = "src_num")
    private String srcNum;

    @Column(name = "dst_chan")
    private String dstChan;

    @Column(name = "dst_num")
    private String dstNum;

    @Column(name = "UNIQUEID")
    private String uniqueid;

    @Column(name = "linkedid")
    private String linkedid;

    @Column(name = "did")
    private String did;

    @Column(name = "disposition")
    private String disposition;

    @Column(name = "recordingfile")
    private String recordingfile;

    @Column(name = "from_account")
    private String fromAccount;

    @Column(name = "to_account")
    private String toAccount;

    @Column(name = "dialstatus")
    private String dialStatus;

    @Column(name = "appname")
    private String appName;

    @Column(name = "transfer")
    private Integer transfer;

    @Column(name = "is_app")
    private String isApp;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "billsec")
    private Integer billsec;

    @Column(name = "work_completed")
    private String workCompleted;

    @Column(name = "src_call_id")
    private String srcCallId;

    @Column(name = "dst_call_id")
    private String dstCallId;

    @Column(name = "verbose_call_id")
    private String verboseCallId;
}
