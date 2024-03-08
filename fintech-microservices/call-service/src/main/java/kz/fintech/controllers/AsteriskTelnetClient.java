package kz.fintech.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.asteriskjava.live.AsteriskServer;
import org.asteriskjava.live.DefaultAsteriskServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@RequestMapping("/call2")
@Slf4j
@RestController
public class AsteriskTelnetClient {
    private Socket socket;
    private OutputStream outputStream;

    @Value("${asterisk.host}")
    private String host;

    @Value("${asterisk.port}")
    private int port;

    public AsteriskTelnetClient() {
        // Конструктор без параметров
    }

    public void sendCommand(String command) throws IOException {
        outputStream.write(command.getBytes(StandardCharsets.US_ASCII));
        outputStream.flush();
    }

    public void close() throws IOException {
        socket.close();
    }

    @SneakyThrows
    @GetMapping("/call")
    public String call() {
        try {

            AsteriskServer asteriskServer = new DefaultAsteriskServer("91.147.93.180", "ami", "Pv2k%GT5iVW36JaczFnUi*$nA");
            asteriskServer.initialize();


            socket = new Socket(host, port);
            outputStream = socket.getOutputStream();

            // Авторизация в AMI
            String amiUsername = "ami";  // Имя пользователя AMI
            String amiPassword = "Pv2k%GT5iVW36JaczFnUi*$nA";  // Пароль AMI

            String amiLogin = "Action: Login\n"
                    + "Username: " + amiUsername + "\n"
                    + "Secret: " + amiPassword + "\n"
                    + "Events: off\n\n";
            sendCommand(amiLogin);

            String fromNumber = "206";  // Номер отправителя
            String toNumber = "77475080880";  // Номер получателя

            // Инициирование вызова
            String originateCommand = "Action: Originate\n"
                    + "Channel: Local/" + fromNumber + "@internal-originate\n"
                    + "Context: all_peers\n"
                    + "Exten: " + toNumber + "\n"
                    + "Priority: 1\n"
                    + "Callerid: " + fromNumber + "\n"
                    + "Variable: SIPADDHEADER=\"Call-Info: answer-after=0,pt1c_cid=" + toNumber + ",ALLOW_MULTY_ANSWER=1\"\n\n";
            sendCommand(originateCommand);

            // Канал для переадресации
            String channelTemplate = "PJSIP/%s-000001";
            String channel = String.format(channelTemplate, fromNumber);

            // Переадресация без консультации
            String redirectCommand = "Action: Redirect\n"
                    + "Channel: " + channel + "\n"
                    + "Context: internal-transfer\n"
                    + "Exten: " + toNumber + "\n"
                    + "Priority: 1\n\n";
            sendCommand(redirectCommand);

            // Переадресация с консультацией
            String attendedTransferCommand = "Action: Atxfer\n"
                    + "Channel: " + channel + "\n"
                    + "Context: internal-transfer\n"
                    + "Exten: " + toNumber + "\n"
                    + "Priority: 1\n\n";
            sendCommand(attendedTransferCommand);

            // Закрытие соединения
            close();
        } catch (Exception ex) {
            return ex.getMessage();
        }
        return "gggg";
    }
}