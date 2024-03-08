package kz.fintech.services;

import kz.fintech.entities.CdrGeneralEntity;
import kz.fintech.models.call.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CallServices {
    List<CdrGeneralClass> saveCallByAll();

    List<CdrGeneral> getAllCallData();

    List<CdrGeneralClass> getByPhoneSrcNumAllParam(String srcNum);

    List<CdrGeneral> getByPhoneSrcNum(String srcNum);

    List<CdrGeneral> getByPhoneDstNum(String dstNum);

    List<CdrGeneralClass> getByPhoneDstNumAllParam(String dstNum);

    List<CdrGeneralClass> saveCallBySrcNum(String srcNum);

    List<CdrGeneralClass> getByPhoneDid(String did);
    MakeCallResponse makeCall(MakeCallRequest request);
    CheckCallResponse checkCall(CheckCallRequest request);
    CallStatus callStatus(String phoneNumber);
    List<CdrGeneralClass> addCallHistory(CallHistoryRequest request);
    String call();
}
