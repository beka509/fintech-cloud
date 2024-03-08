package kz.fintech.models.refs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Becf {
    private int org_unit_id;
    private String org_unit_id_hi;
    private String company_id;
    private String org_unit_name;
    private String org_unit_longname;
    private String org_unit_code;
    private boolean is_arc;
    private Timestamp created_time;
    private String system_source_id;
    private String system_source_id_hi;
    private String city_kz;
    private String city_ru;
    private String address_kz;
    private String address_ru;
    private String branch_name_kz;
    private String branch_name_ru;
    private String bin;
    private String phones;
    private String emails;
    private String bik;
    private String fax;
    private String corr_account_kzt;
    private String corr_account_usd;
    private String zip_code;
    private String short_name;
}
