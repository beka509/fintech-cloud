package kz.fintech.controllers;

//import org.asteriskjava.live.AsteriskServer;
//import org.asteriskjava.live.DefaultAsteriskServer;
//import org.asteriskjava.manager.ManagerConnectionState;
import org.asteriskjava.manager.action.OriginateAction;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import sun.plugin2.jvm.RemoteJVMLauncher;

public class ScenarioCall extends OriginateAction {

//    private final Logger log = LoggerFactory.getLogger(ScenarioCall.class);
//
//    private String TRUNK;
//    private final String PHONE_FOR_RINGING;
//    private final String EXTEN_FOR_APP;
//    private final String CONTEXT_FOR_APP;
//
//    public ScenarioCall(String trunk, String phoneForRinging, String extension, String context) {
//        this.TRUNK = trunk;
//        this.PHONE_FOR_RINGING = phoneForRinging;
//        this.EXTEN_FOR_APP = extension;
//        this.CONTEXT_FOR_APP = context;
//        this.init();
//    }
//
//    /**
//     * инициализируем сценарий и уже в конструкторе получаем готовый OriginateAction
//     */
//    private void init() {
//        //номер абонента
//        String callId = ValidValues.getValidCallId(this.PHONE_FOR_RINGING);
//        //канал с которого звоним
//        String channelAsterisk = ValidValues.getValidChannel(this.TRUNK, this.PHONE_FOR_RINGING);
//        this.setContext(CONTEXT_FOR_APP);
//        this.setExten(EXTEN_FOR_APP);
//        this.setPriority(1);
//        this.setAsync(true);
//        this.setCallerId(callId);
//        this.setChannel(channelAsterisk);
//        log.info("Create Scenario Call: phone '{}',chanel '{}',context '{}',extension '{}'",
//                callId,
//                channelAsterisk,
//                CONTEXT_FOR_APP,
//                EXTEN_FOR_APP);
//
//        if (asteriskServer.getManagerConnection().getState().equals(ManagerConnectionState.CONNECTED)
//                || asteriskServer .getManagerConnection().getState().equals(ManagerConnectionState.CONNECTING)
//                || asteriskServer .getManagerConnection().getState().equals(ManagerConnectionState.INITIAL)) {
//            try {
//                ScenarioCall scenarioCall = new ScenarioCall(trank, phone, extension, context);
//                RemoteJVMLauncher.CallBack callBackForScenarioCall = new RemoteJVMLauncher.CallBack();
//                asteriskServer.originateAsync(scenarioCall, callBackForScenarioCall);
//            } catch (ManagerCommunicationException e) {
//                //при падении канала связи, StateConnection  может быть в RECONNECTING, а может вообще отвалиться
//            }
//        }
//    }
}
