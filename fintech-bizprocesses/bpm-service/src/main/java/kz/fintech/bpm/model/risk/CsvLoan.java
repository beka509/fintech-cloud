package kz.fintech.bpm.model.risk;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class CsvLoan {

    @CsvBindByName(column = "UNIQUE_ID")
    private long uniqueId;
    @CsvBindByName(column = "PRODUCT_NAME")
    private String productName;
    @CsvBindByName(column = "REPORT_DATE")
    private String reportDate;
    @CsvBindByName(column = "CLIENT_ID")
    private String clientId;
    @CsvBindByName(column = "CONTRACT_ID")
    private String contractId;
    @CsvBindByName(column = "CURRENCY")
    private String currency;
    @CsvBindByName(column = "BEGIN_DATE")
    private String beginDate;
    @CsvBindByName(column = "END_DATE")
    private String endDate;
    @CsvBindByName(column = "END_DATE_FACT")
    private String endDateFact;
    @CsvBindByName(column = "ANNUITY_FLAG")
    private String annuityFlag;
    @CsvBindByName(column = "GIVEN_LOAN")
    private String givenLoan;
    @CsvBindByName(column = "LIMIT")
    private String limit;
    @CsvBindByName(column = "DEBT")
    private String debt;
    @CsvBindByName(column = "INTEREST")
    private String interest;
    @CsvBindByName(column = "IFRS_DEBT")
    private String ifrsDebt;
    @CsvBindByName(column = "DEFAULT_DATE")
    private String defaultDate;
    @CsvBindByName(column = "DEFAULT_FLAG")
    private String defaultFlag;
    @CsvBindByName(column = "RESTR_DATE")
    private String restrDate;
    @CsvBindByName(column = "RESTR_TYPE")
    private String restrType;
    @CsvBindByName(column = "DELAY")
    private String delay;
    @CsvBindByName(column = "RATING")
    private String rating;
    @CsvBindByName(column = "SEGMENT")
    private String segment;
    @CsvBindByName(column = "CONTRACT_NUMBER_BEFORE_RESTR")
    private String contractNumberBeforeRestr;
    @CsvBindByName(column = "WATCH_LIST")
    private String watchList;
    @CsvBindByName(column = "BRANCH")
    private String branch;
    @CsvBindByName(column = "PRODUCT_NAME_OLD")
    private String productNameOld;
    @CsvBindByName(column = "P_flag")
    private String pFlag;
}
