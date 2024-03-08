package kz.fintech.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import kz.fintech.config.AsteriskDatabaseConnector;
import kz.fintech.entities.CdrGeneralEntity;
import kz.fintech.models.call.*;
import kz.fintech.props.CallProps;
import kz.fintech.props.SshProps;
import kz.fintech.repos.CdrGeneralRepository;
import kz.fintech.services.CallServices;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.ManagerConnectionState;
import org.asteriskjava.manager.action.OriginateAction;
import org.asteriskjava.manager.response.ManagerResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CallServicesImpl implements CallServices {
    private final CdrGeneralRepository cdrGeneralRepository;
    private final AsteriskDatabaseConnector asteriskDatabaseConnector;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    private final CallProps props;
    private final SshProps sshProps;

    public CallServicesImpl(CdrGeneralRepository cdrGeneralRepository,
                            AsteriskDatabaseConnector asteriskDatabaseConnector,
                            ObjectMapper objectMapper,
                            CallProps props, SshProps sshProps) {
        this.cdrGeneralRepository = cdrGeneralRepository;
        this.asteriskDatabaseConnector = asteriskDatabaseConnector;
        this.objectMapper = objectMapper;
        this.props = props;
        this.sshProps = sshProps;
        this.restTemplate = new RestTemplate();
    }

    @SneakyThrows
    public List<CdrGeneral> getAllCallData() {
        List<CdrGeneral> cdrGeneralList = new ArrayList<>();
        List<CdrGeneralEntity> res = cdrGeneralRepository.findAll();

        for (CdrGeneralEntity entity : res) {
            CdrGeneral cdrGeneral = CdrGeneral.builder()
                    .start(entity.getStart())
                    .srcNum(entity.getSrcNum())
                    .dstNum(entity.getDstNum())
                    .billsec(entity.getBillsec())
                    .build();
            cdrGeneralList.add(cdrGeneral);
        }
        return cdrGeneralList;
    }

    @SneakyThrows
    public List<CdrGeneral> getByPhoneSrcNum(@PathVariable("srcNum") String srcNum) {
        List<CdrGeneral> cdrGeneralList = new ArrayList<>();
        List<CdrGeneralClass> res = cdrGeneralRepository.findListBySrcNum(srcNum);

        for (CdrGeneralClass entity : res) {
            CdrGeneral cdrGeneral = CdrGeneral.builder()
                    .start(entity.getStart())
                    .srcNum(entity.getSrcNum())
                    .dstNum(entity.getDstNum())
                    .billsec(entity.getBillsec())
                    .build();
            cdrGeneralList.add(cdrGeneral);
        }
        return cdrGeneralList;
    }

    @SneakyThrows
    public List<CdrGeneralClass> getByPhoneSrcNumAllParam(@PathVariable("srcNum") String srcNum) {
        return cdrGeneralRepository.findListBySrcNum(srcNum);
    }

    @SneakyThrows
    public List<CdrGeneral> getByPhoneDstNum(@PathVariable("dstNum") String dstNum) {
        List<CdrGeneral> cdrGeneralList = new ArrayList<>();
        List<CdrGeneralClass> res = cdrGeneralRepository.findListByDstNum(dstNum);

        for (CdrGeneralClass entity : res) {
            CdrGeneral cdrGeneral = CdrGeneral.builder()
                    .start(entity.getStart())
                    .srcNum(entity.getSrcNum())
                    .dstNum(entity.getDstNum())
                    .billsec(entity.getBillsec())
                    .build();
            cdrGeneralList.add(cdrGeneral);
        }
        return cdrGeneralList;
    }

    @SneakyThrows
    public List<CdrGeneralClass> getByPhoneDstNumAllParam(@PathVariable("dstNum") String dstNum) {
        return cdrGeneralRepository.findListByDstNum(dstNum);
    }

    @SneakyThrows
    public List<CdrGeneralClass> getByPhoneDid(@PathVariable("did") String did) {
        return cdrGeneralRepository.findListByDid(did);
    }

    @SneakyThrows
    public List<CdrGeneralClass> saveCallByAll() {
        List<CdrGeneralClass> res = new ArrayList<>();
        List<CdrGeneralEntity> dbAll = cdrGeneralRepository.findAll();

        try (Connection connection = asteriskDatabaseConnector.getConnection()) {
            String sql = "SELECT start, endtime, answer, src_chan, src_num, dst_chan, dst_num, " +
                    "uniqueid, linkedid, did, disposition, recordingfile, from_account, to_account, " +
                    "dialstatus, appname, transfer, is_app, duration, billsec, work_completed, " +
                    "src_call_id, dst_call_id, verbose_call_id " +
                    "FROM cdr_general";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String uniqueid = resultSet.getString("uniqueid");

                        // Проверяем, существует ли запись с таким uniqueid в PostgreSQL
                        boolean alreadyExists = dbAll.stream()
                                .anyMatch(entry -> uniqueid.equals(entry.getUniqueid()));

                        if (!alreadyExists) {
                            CdrGeneralEntity cdrGeneralEntity = new CdrGeneralEntity();
                            CdrGeneralClass cdrGeneralClass = new CdrGeneralClass();

                            // Заполняем данные из ResultSet в объекты
                            cdrGeneralEntity.setStart(resultSet.getString("start"));
                            cdrGeneralEntity.setEndTime(resultSet.getString("endtime"));
                            cdrGeneralEntity.setAnswer(resultSet.getString("answer"));
                            cdrGeneralEntity.setSrcChan(resultSet.getString("src_chan"));
                            cdrGeneralEntity.setSrcNum(resultSet.getString("src_num"));
                            cdrGeneralEntity.setDstChan(resultSet.getString("dst_chan"));
                            cdrGeneralEntity.setDstNum(resultSet.getString("dst_num"));
                            cdrGeneralEntity.setUniqueid(uniqueid); // Set uniqueid
                            cdrGeneralEntity.setLinkedid(resultSet.getString("linkedid"));
                            cdrGeneralEntity.setDid(resultSet.getString("did"));
                            cdrGeneralEntity.setDisposition(resultSet.getString("disposition"));
                            cdrGeneralEntity.setRecordingfile(resultSet.getString("recordingfile"));
                            cdrGeneralEntity.setFromAccount(resultSet.getString("from_account"));
                            cdrGeneralEntity.setToAccount(resultSet.getString("to_account"));
                            cdrGeneralEntity.setDialStatus(resultSet.getString("dialstatus"));
                            cdrGeneralEntity.setAppName(resultSet.getString("appname"));
                            cdrGeneralEntity.setTransfer(resultSet.getInt("transfer"));
                            cdrGeneralEntity.setIsApp(resultSet.getString("is_app"));
                            cdrGeneralEntity.setDuration(resultSet.getInt("duration"));
                            cdrGeneralEntity.setBillsec(resultSet.getInt("billsec"));
                            cdrGeneralEntity.setWorkCompleted(resultSet.getString("work_completed"));
                            cdrGeneralEntity.setSrcCallId(resultSet.getString("src_call_id"));
                            cdrGeneralEntity.setDstCallId(resultSet.getString("dst_call_id"));
                            cdrGeneralEntity.setVerboseCallId(resultSet.getString("verbose_call_id"));

                            // Заполняем объект CdrGeneralClass
                            BeanUtils.copyProperties(cdrGeneralEntity, cdrGeneralClass);

                            res.add(cdrGeneralClass);
                            cdrGeneralRepository.save(cdrGeneralEntity);
                        }
                    }
                } catch (Exception ex) {
                    throw new Exception("Error => " + ex.getMessage());
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error => " + ex.getMessage());
        }
        return res;
    }

    @SneakyThrows
    public List<CdrGeneralClass> saveCallBySrcNum(@PathVariable("srcNum") String srcNum) {
        List<CdrGeneralClass> res = new ArrayList();

        try (Connection connection = asteriskDatabaseConnector.getConnection()) {
            String sql = "SELECT start, endtime, answer, src_chan, src_num, dst_chan, dst_num, " +
                    "uniqueid, linkedid, did, disposition, recordingfile, from_account, to_account, " +
                    "dialstatus, appname, transfer, is_app, duration, billsec, work_completed, " +
                    "src_call_id, dst_call_id, verbose_call_id " +
                    "FROM cdr_general WHERE src_num = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, srcNum);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String uniqueid = resultSet.getString("uniqueid");

                        // Проверяем, существует ли запись с таким uniqueid в PostgreSQL БД
                        List<CdrGeneralClass> existingRecords = cdrGeneralRepository.findListByUniqueid(uniqueid);

                        if (existingRecords.isEmpty()) {
                            CdrGeneralEntity cdrGeneralEntity = new CdrGeneralEntity();
                            CdrGeneralClass cdrGeneralClass = new CdrGeneralClass();

                            cdrGeneralEntity.setStart(resultSet.getString("start"));
                            cdrGeneralEntity.setEndTime(resultSet.getString("endtime"));
                            cdrGeneralEntity.setAnswer(resultSet.getString("answer"));
                            cdrGeneralEntity.setSrcChan(resultSet.getString("src_chan"));
                            cdrGeneralEntity.setSrcNum(resultSet.getString("src_num"));
                            cdrGeneralEntity.setDstChan(resultSet.getString("dst_chan"));
                            cdrGeneralEntity.setDstNum(resultSet.getString("dst_num"));
                            cdrGeneralEntity.setUniqueid(uniqueid);
                            cdrGeneralEntity.setLinkedid(resultSet.getString("linkedid"));
                            cdrGeneralEntity.setDid(resultSet.getString("did"));
                            cdrGeneralEntity.setDisposition(resultSet.getString("disposition"));
                            cdrGeneralEntity.setRecordingfile(resultSet.getString("recordingfile"));
                            cdrGeneralEntity.setFromAccount(resultSet.getString("from_account"));
                            cdrGeneralEntity.setToAccount(resultSet.getString("to_account"));
                            cdrGeneralEntity.setDialStatus(resultSet.getString("dialstatus"));
                            cdrGeneralEntity.setAppName(resultSet.getString("appname"));
                            cdrGeneralEntity.setTransfer(resultSet.getInt("transfer"));
                            cdrGeneralEntity.setIsApp(resultSet.getString("is_app"));
                            cdrGeneralEntity.setDuration(resultSet.getInt("duration"));
                            cdrGeneralEntity.setBillsec(resultSet.getInt("billsec"));
                            cdrGeneralEntity.setWorkCompleted(resultSet.getString("work_completed"));
                            cdrGeneralEntity.setSrcCallId(resultSet.getString("src_call_id"));
                            cdrGeneralEntity.setDstCallId(resultSet.getString("dst_call_id"));
                            cdrGeneralEntity.setVerboseCallId(resultSet.getString("verbose_call_id"));

                            // Заполняем объект CdrGeneralClass
                            BeanUtils.copyProperties(cdrGeneralEntity, cdrGeneralClass);
                            res.add(cdrGeneralClass);
                            cdrGeneralRepository.save(cdrGeneralEntity);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error => " + ex.getMessage());
        }
        return res;
    }

    @SneakyThrows
    public MakeCallResponse makeCall(MakeCallRequest request) {
        String baseUrl = props.getUrl();
        MakeCallResponse status = new MakeCallResponse();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            val reqJson = objectMapper.writeValueAsString(MakeCallRequest.builder()
                    .fromNumber(request.getFromNumber())
                    .toNumber(request.getToNumber())
                    .build());

            ResponseEntity<MakeCallResponse> response = restTemplate.exchange(baseUrl + "/make_call", HttpMethod.POST,
                    new HttpEntity<>(reqJson, headers), MakeCallResponse.class);

            if (response.getStatusCode() == HttpStatus.TEMPORARY_REDIRECT) {
                // Если получен код 307, переходите по новому URL
                String newUrl = response.getHeaders().getLocation().toString();
                response = restTemplate.exchange(newUrl, HttpMethod.POST,
                        new HttpEntity<>(reqJson, headers), MakeCallResponse.class);
            }
            status = objectMapper.convertValue(response.getBody(), MakeCallResponse.class);
        } catch (Exception ex) {
            log.error("Error, makeCall => {}", ex.getMessage());
            throw new RuntimeException("Error, makeCall => " + ex.getMessage());
        }
        return status;
    }

    @SneakyThrows
    public CheckCallResponse checkCall(CheckCallRequest request) {
        String baseUrl = props.getUrl();
        CheckCallResponse status = new CheckCallResponse();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            val reqJson = objectMapper.writeValueAsString(CheckCallRequest.builder()
                    .toNumber(request.getToNumber())
                    .build());

            ResponseEntity<CheckCallResponse> response = restTemplate.exchange(baseUrl + "/check_call", HttpMethod.POST,
                    new HttpEntity<>(reqJson, headers), CheckCallResponse.class);

            if (response.getStatusCode() == HttpStatus.TEMPORARY_REDIRECT) {
                // Если получен код 307, переходите по новому URL
                String newUrl = response.getHeaders().getLocation().toString();
                response = restTemplate.exchange(newUrl, HttpMethod.POST,
                        new HttpEntity<>(reqJson, headers), CheckCallResponse.class);
            }
            status = objectMapper.convertValue(response.getBody(), CheckCallResponse.class);
        } catch (Exception ex) {
            log.error("Error, makeCall => {}", ex.getMessage());
            throw new RuntimeException("Error, makeCall => " + ex.getMessage());
        }
        return status;
    }

    @SneakyThrows
    public CallStatus callStatus(@PathVariable("phoneNumber") String phoneNumber) {
        CallStatus res = new CallStatus();
        String command = "ssh root@" + sshProps.getUrl() + " 'php /storage/usbdisk1/mikopbx/custom_modules/call_status_api.php " + phoneNumber + "'";
        try {
            JSch jsch = new JSch();
            Session firstSession = jsch.getSession(sshProps.getUser(), sshProps.getHost(), sshProps.getPort());
            firstSession.setPassword(sshProps.getPassword());
            firstSession.setConfig("StrictHostKeyChecking", "no");
            firstSession.connect();

            val response = executeCommand(firstSession, command);
            res = objectMapper.readValue(response, CallStatus.class);
            firstSession.disconnect();
        } catch (Exception ex) {
            throw new RuntimeException("Error in method callStatus => " + ex.getMessage());
        }
        return res;
    }

    @SneakyThrows
    private static String executeCommand(Session session, String command) throws Exception {
        Channel channel = session.openChannel("exec");
        ((ChannelExec)channel).setCommand(command);
        channel.setInputStream(null);
        InputStream in = channel.getInputStream();
        channel.connect();

        StringBuilder outputBuffer = new StringBuilder();
        int readByte;
        while((readByte = in.read()) != -1) {
            outputBuffer.append((char) readByte);
        }

        channel.disconnect();
        return outputBuffer.toString();
    }

    @SneakyThrows
    public List<CdrGeneralClass> addCallHistory(@RequestBody CallHistoryRequest request) {
        List<CdrGeneralClass> res = new ArrayList();
        String command = "ssh root@" + sshProps.getUrl() + " 'php /storage/usbdisk1/mikopbx/custom_modules/call_status_api.php " + request.getPhoneNumber() + "'";
        try {
            JSch jsch = new JSch();
            Session firstSession = jsch.getSession(sshProps.getUser(), sshProps.getHost(), sshProps.getPort());
            firstSession.setPassword(sshProps.getPassword());
            firstSession.setConfig("StrictHostKeyChecking", "no");
            firstSession.connect();

            val response = executeCommand(firstSession, command);
            CallStatus obj = objectMapper.readValue(response, CallStatus.class);
            firstSession.disconnect();
            res = saveCallByDstNum(obj, request.getPhoneNumber());
        } catch (Exception ex) {
            throw new RuntimeException("Error in method callStatus => " + ex.getMessage());
        }
        return res;
    }

    @SneakyThrows
    public List<CdrGeneralClass> saveCallByDstNum(@RequestBody CallStatus callStatus, @PathVariable("phoneNumber") String phoneNumber) {
        List<CdrGeneralClass> res = new ArrayList();
        List<CdrGeneralClass> data = callStatus.getData(); // Получаем список данных из CallStatus

        try {
            for (CdrGeneralClass cdrGeneralClass : data) {
                // Проверяем, существует ли запись с таким uniqueid в PostgreSQL БД
                List<CdrGeneralClass> existingRecords = cdrGeneralRepository.findListByUniqueid(cdrGeneralClass.getUniqueid());

                if (existingRecords.isEmpty()) {
                    CdrGeneralEntity cdrGeneralEntity = new CdrGeneralEntity();

                    // Заполняем cdrGeneralEntity данными из cdrGeneralClass
                    cdrGeneralEntity.setStart(cdrGeneralClass.getStart());
                    cdrGeneralEntity.setEndTime(cdrGeneralClass.getEndTime());
                    cdrGeneralEntity.setAnswer(cdrGeneralClass.getAnswer());
                    cdrGeneralEntity.setSrcChan(cdrGeneralClass.getSrcChan());
                    cdrGeneralEntity.setSrcNum(cdrGeneralClass.getSrcNum());
                    cdrGeneralEntity.setDstChan(cdrGeneralClass.getDstChan());
                    cdrGeneralEntity.setDstNum(cdrGeneralClass.getDstNum());
                    cdrGeneralEntity.setUniqueid(cdrGeneralClass.getUniqueid());
                    cdrGeneralEntity.setLinkedid(cdrGeneralClass.getLinkedid());
                    cdrGeneralEntity.setDid(cdrGeneralClass.getDid());
                    cdrGeneralEntity.setDisposition(cdrGeneralClass.getDisposition());
                    cdrGeneralEntity.setRecordingfile(cdrGeneralClass.getRecordingfile());
                    cdrGeneralEntity.setFromAccount(cdrGeneralClass.getFromAccount());
                    cdrGeneralEntity.setToAccount(cdrGeneralClass.getToAccount());
                    cdrGeneralEntity.setDialStatus(cdrGeneralClass.getDialStatus());
                    cdrGeneralEntity.setAppName(cdrGeneralClass.getAppName());
                    cdrGeneralEntity.setTransfer(cdrGeneralClass.getTransfer());
                    cdrGeneralEntity.setIsApp(cdrGeneralClass.getIsApp());
                    cdrGeneralEntity.setDuration(cdrGeneralClass.getDuration());
                    cdrGeneralEntity.setBillsec(cdrGeneralClass.getBillsec());
                    cdrGeneralEntity.setWorkCompleted(cdrGeneralClass.getWorkCompleted());
                    cdrGeneralEntity.setSrcCallId(cdrGeneralClass.getSrcCallId());
                    cdrGeneralEntity.setDstCallId(cdrGeneralClass.getDstCallId());
                    cdrGeneralEntity.setVerboseCallId(cdrGeneralClass.getVerboseCallId());

                    // Заполняем объект CdrGeneralClass
                    BeanUtils.copyProperties(cdrGeneralEntity, cdrGeneralClass);
                    res.add(cdrGeneralClass);
                    cdrGeneralRepository.save(cdrGeneralEntity);
                }
            }
        }catch (Exception ex) {
            throw new RuntimeException("Ошибка в методе saveCallByDstNum => " + ex.getMessage());
        }
        return res;
    }

    @SneakyThrows
    public String call() {
    ManagerConnectionFactory factory = new ManagerConnectionFactory(
            //"91.147.93.98", 5038,
            "91.147.93.180", 5038,
            "ami",
            "Pv2k%GT5iVW36JaczFnUi*$nA"
    );
    ManagerConnection managerConnection = factory.createManagerConnection();

        try {
            managerConnection.login();

            if (managerConnection.getState() == ManagerConnectionState.CONNECTED) {

                OriginateAction originateAction = new OriginateAction();
                originateAction.setChannel("PJSIP/206");
                originateAction.setContext("from-internal");
                originateAction.setExten("77475080880"); // Внешний номер, на который хотите сделать вызов
                originateAction.setPriority(1);
                //originateAction.setCallerId("206"); // Установка CallerID
                //originateAction.setVariable("SIPADDHEADER", "Call-Info: answer-after=0, pt1c_cid=77016677497, ALLOW_MULTY_ANSWER=1");


                ManagerResponse originateResponse = managerConnection.sendAction(originateAction, 30000);
                if (originateResponse.getResponse() != null && originateResponse.getResponse().toLowerCase().equals("error")) {
                    String errorMessage = originateResponse.getMessage();
                    log.info("Originate response error: " + errorMessage);
                } else {
                    // Обработайте успешный ответ, если необходимо
                    log.info("успешный ответ!!!");
                }
                return "Originate response: " + originateResponse.getMessage();
            } else {
                log.error("AMI connection is not established.");
            }
        } catch (Exception ex) {
            log.error("Error => " + ex.getMessage());
            ex.printStackTrace();
            return "Error: " + ex.getMessage();
        } finally {
            managerConnection.logoff();
        }
    return "Originate response: " + managerConnection.getHostname();
    }
}
