package kz.fintech.services.impl;

import lombok.AllArgsConstructor;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.action.OriginateAction;
import org.asteriskjava.manager.response.ManagerResponse;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AsteriskManager {
//        public static void main(String[] args) throws Exception {
//            ManagerConnectionFactory factory = new ManagerConnectionFactory(
//                    "91.147.93.98",
//                    5038,
//                    "201",
//                    "be7a654b0b6575ababf897d2b78508b6"
//            );
//            ManagerConnection managerConnection = factory.createManagerConnection();
//            try {
//                managerConnection.login();
//
//                OriginateAction originateAction = new OriginateAction();
//                originateAction.setChannel("SIP/201"); // Замените на нужный канал
//                originateAction.setContext("from-internal");
//                originateAction.setExten("202"); // Замените на нужное расширение
//                originateAction.setPriority(1);
//
//                ManagerResponse originateResponse = managerConnection.sendAction(originateAction, 30000);
//
//                System.out.println("Originate response: " + originateResponse.getResponse());
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                managerConnection.logoff();
//            }
//        }
}
