package kz.fintech.models.refs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EsfInvoice {
    private Long id;
    private Long depId;
    private String nameUs;
    private String regNum;
    private Long pstDepId;
    private String pstDepCode;
    private String pstName;
    private String pstIin;
    private String pstAdr;
    private String cliCode;
    private String cliTaxcode;
    private String cliAdr;
    private String dval;
    private String nameTarif;
    private String sdok;
    private String sdokNds;
    private int statusEsf;
    private LocalDateTime createDate;
    private String esfNumber;

    @JsonIgnore
    public Double sdokDoubleValue() {
        return Double.parseDouble(sdok);
    }

    @JsonIgnore
    public Double sdokNdsDoubleValue() {
        return Double.parseDouble(sdokNds);
    }
}
