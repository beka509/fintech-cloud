package kz.fintech.validators.validators;

import kz.fintech.models.refs.OrgUnit;
import kz.fintech.validators.constraints.OrgUnitRequiredConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OrgUnitRequiredValidator implements ConstraintValidator<OrgUnitRequiredConstraint, OrgUnit> {

    @Override
    public void initialize(OrgUnitRequiredConstraint orgUnitRequiredConstraint) {
    }

    @Override
    public boolean isValid(OrgUnit orgUnit, ConstraintValidatorContext cxt) {
        return orgUnit != null && orgUnit.getId() > 0;
    }

}