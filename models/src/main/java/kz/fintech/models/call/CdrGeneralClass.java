package kz.fintech.models.call;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CdrGeneralClass {
    private Long id;
    @JsonProperty("start")
    private String start;

    @JsonProperty("endtime")
    private String endTime;

    @JsonProperty("answer")
    private String answer;

    @JsonProperty("src_chan")
    private String srcChan;

    @JsonProperty("src_num")
    private String srcNum;

    @JsonProperty("dst_chan")
    private String dstChan;

    @JsonProperty("dst_num")
    private String dstNum;

    @JsonProperty("UNIQUEID")
    private String uniqueid;

    @JsonProperty("linkedid")
    private String linkedid;

    @JsonProperty("did")
    private String did;

    @JsonProperty("disposition")
    private String disposition;

    @JsonProperty("recordingfile")
    private String recordingfile;

    @JsonProperty("from_account")
    private String fromAccount;

    @JsonProperty("to_account")
    private String toAccount;

    @JsonProperty("dialstatus")
    private String dialStatus;

    @JsonProperty("appname")
    private String appName;

    @JsonProperty("transfer")
    private Integer transfer;

    @JsonProperty("is_app")
    private String isApp;

    @JsonProperty("duration")
    private Integer duration;

    @JsonProperty("billsec")
    private Integer billsec;

    @JsonProperty("work_completed")
    private String workCompleted;

    @JsonProperty("src_call_id")
    private String srcCallId;

    @JsonProperty("dst_call_id")
    private String dstCallId;

    @JsonProperty("verbose_call_id")
    private String verboseCallId;
}
