package kz.fintech.bpm.func;

import java.io.IOException;
import java.text.ParseException;

public interface Runner<TResult, TArg> {
    TResult execute(TArg expressLoanRequest);
}
