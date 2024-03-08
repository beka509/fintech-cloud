package kz.fintech.models.refs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor

public class BranchAccountants {
    private int id;
    private int accountantOrgUnitId;
    private int branchOrgUnitId;
    private String branchesName;
    private String branchOrgUnitCode;
}
