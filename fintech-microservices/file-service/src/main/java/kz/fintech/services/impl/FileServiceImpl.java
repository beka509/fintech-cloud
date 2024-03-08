package kz.fintech.services.impl;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import kz.fintech.commons.feignclients.DbServiceClient;
import kz.fintech.models.excel.ExcelExportIin;
import kz.fintech.models.excel.ExcelExportRequest;
import kz.fintech.models.excel.ExcelExportRequestRepo;
import kz.fintech.models.excel.ExcelResponse;
import kz.fintech.props.FileProps;
import kz.fintech.services.FileService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@EnableFeignClients(basePackages = "kz.fintech.commons.feignclients")
public class FileServiceImpl implements FileService {

    private final DbServiceClient dbServiceClient;
    private final FileProps fileProps;

    @Override
    @SneakyThrows
    public String importExcelToDb(MultipartFile file) {
        val dbClients = dbServiceClient.getListClients();
        ExcelResponse excelResponse = exportExcelToObject(file, dbClients);
        if (excelResponse.getExcelExportRequestsNew() != null && !excelResponse.getExcelExportRequestsNew().isEmpty()) {
            dbServiceClient.saveFile(excelResponse.getExcelExportRequestsNew());
        }
        if (excelResponse.getExcelExportRequestsExisting() != null && !excelResponse.getExcelExportRequestsExisting().isEmpty()) {
            dbServiceClient.saveContract(excelResponse.getExcelExportRequestsExisting());
        }
        return "SUCCESS";
    }

    @Override
    @SneakyThrows
    public String sendExcelFtp() {
        log.info("FileServiceImpl");
        List<ExcelExportRequestRepo> excelList = dbServiceClient.getListClientsInformation();
        return sendExcelToFtp(excelList);
    }

    // отправка листа по выбранному сегменту segmentId
    @Override
    @SneakyThrows
    public String sendExcelBySegmentToFtp(Integer segmentId) {
        val excelList = dbServiceClient.findListBySegment(segmentId);
        return sendExcelToFtp(excelList);
    }

    @SneakyThrows
    private String sendExcelToFtp(List<ExcelExportRequestRepo> excelList){
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Лист1");

        Row headerRow = sheet.createRow(0);// Создаем и заполняем ячейки для каждого заголовка
        Cell headerCellFio = headerRow.createCell(0);
        headerCellFio.setCellValue("FIO");

        Cell headerCellIin = headerRow.createCell(1);
        headerCellIin.setCellValue("IIN");

        Cell headerCellPhoneNumber = headerRow.createCell(2);
        headerCellPhoneNumber.setCellValue("Phone_number");

        Cell headerCellPhoneNumber2 = headerRow.createCell(3);
        headerCellPhoneNumber2.setCellValue("Phone_number2");

        Cell headerCellContactNumber = headerRow.createCell(4);
        headerCellContactNumber.setCellValue("Contact_number");

        Cell headerCellEmail = headerRow.createCell(5);
        headerCellEmail.setCellValue("E-mail");

        Cell headerCellResidenceAddress = headerRow.createCell(6);
        headerCellResidenceAddress.setCellValue("Residence_adress");

        Cell headerCellRegistrAddress = headerRow.createCell(7);
        headerCellRegistrAddress.setCellValue("Registr_adress");

        Cell headerCellLoanAmount = headerRow.createCell(8);
        headerCellLoanAmount.setCellValue("loan_amount");

        Cell headerCellTotalDebtAmount = headerRow.createCell(9);
        headerCellTotalDebtAmount.setCellValue("total_debt_amount");

        Cell headerCellPaymentAmount = headerRow.createCell(10);
        headerCellPaymentAmount.setCellValue("payment_amount");

        Cell headerCellOverdueAmount = headerRow.createCell(11);
        headerCellOverdueAmount.setCellValue("overdue_amount");

        Cell headerCellPenalty = headerRow.createCell(12);
        headerCellPenalty.setCellValue("penalty");

        Cell headerCellPaymentDate = headerRow.createCell(13);
        headerCellPaymentDate.setCellValue("payment_date");

        Cell headerCellOverdueDay = headerRow.createCell(14);
        headerCellOverdueDay.setCellValue("overdue_day");

        Cell headerCellRepaymentDate = headerRow.createCell(15);
        headerCellRepaymentDate.setCellValue("repayment_date");

        Cell headerCellProductType = headerRow.createCell(16);
        headerCellProductType.setCellValue("Product_type");

        Cell headerCellProductName = headerRow.createCell(17);
        headerCellProductName.setCellValue("product_name");

        Cell headerCellContractNumber = headerRow.createCell(18);
        headerCellContractNumber.setCellValue("contract_number");

        Cell headerCellContractOpenDate = headerRow.createCell(19);
        headerCellContractOpenDate.setCellValue("contract_open_date");

        Cell headerCellDateEnd = headerRow.createCell(20);
        headerCellDateEnd.setCellValue("date_end");

        for (int i = 0; i < excelList.size(); i++) {
            Row dataRow = sheet.createRow(i + 1);

            // Создаем и заполняем ячейки для каждого поля данных
            Cell dataCellFio = dataRow.createCell(0);
            dataCellFio.setCellValue(excelList.get(i).getFio());

            Cell dataCellIin = dataRow.createCell(1);
            dataCellIin.setCellValue(excelList.get(i).getIin());

            Cell dataCellPhoneNumber = dataRow.createCell(2);
            dataCellPhoneNumber.setCellValue(excelList.get(i).getPhoneNumber());

            Cell dataCellPhoneNumber2 = dataRow.createCell(3);
            dataCellPhoneNumber2.setCellValue(excelList.get(i).getPhoneNumber2());

            Cell dataCellContactNumber = dataRow.createCell(4);
            dataCellContactNumber.setCellValue(excelList.get(i).getContractNumber());

            Cell dataCellEmail = dataRow.createCell(5);
            dataCellEmail.setCellValue(excelList.get(i).getEmail());

            Cell dataCellResidenceAddress = dataRow.createCell(6);
            dataCellResidenceAddress.setCellValue(excelList.get(i).getResidenceAddress());

            Cell dataCellRegistrAddress = dataRow.createCell(7);
            dataCellRegistrAddress.setCellValue(excelList.get(i).getRegistrAddress());

            Cell dataCellLoanAmount = dataRow.createCell(8);
            dataCellLoanAmount.setCellValue(excelList.get(i).getLoanAmount());

            Cell dataCellTotalDebtAmount = dataRow.createCell(9);
            dataCellTotalDebtAmount.setCellValue(excelList.get(i).getTotalDebtAmount());

            Cell dataCellPaymentAmount = dataRow.createCell(10);
            dataCellPaymentAmount.setCellValue(excelList.get(i).getPaymentAmount());

            Cell dataCellOverdueAmount = dataRow.createCell(11);
            dataCellOverdueAmount.setCellValue(excelList.get(i).getOverdueAmount());

            Cell dataCellPenaltyAmount = dataRow.createCell(12);
            dataCellPenaltyAmount.setCellValue(excelList.get(i).getPenaltyAmount());

            Cell dataCellPaymentDate = dataRow.createCell(13);
            OffsetDateTime paymentDate = excelList.get(i).getContractOpenDate();
            dataCellPaymentDate.setCellValue(paymentDate != null ? paymentDate.toString() : "");

            Cell dataCellOverdueDay = dataRow.createCell(14);
            dataCellOverdueDay.setCellValue(excelList.get(i).getOverdueDay());

            Cell dataCellRepaymentDate = dataRow.createCell(15);
            OffsetDateTime repaymentDate = excelList.get(i).getContractOpenDate();
            dataCellRepaymentDate.setCellValue(repaymentDate != null ? repaymentDate.toString() : "");

            Cell dataCellProductType = dataRow.createCell(16);
            dataCellProductType.setCellValue(excelList.get(i).getProductType());

            Cell dataCellProductName = dataRow.createCell(17);
            dataCellProductName.setCellValue(excelList.get(i).getProductName());

            Cell dataCellContractNumber = dataRow.createCell(18);
            dataCellContractNumber.setCellValue(excelList.get(i).getProductName());

            Cell dataCellContractOpenDate = dataRow.createCell(19);
            OffsetDateTime contractOpenDate = excelList.get(i).getContractOpenDate();
            dataCellContractOpenDate.setCellValue(contractOpenDate != null ? contractOpenDate.toString() : "");

            Cell dataCellDateEnd = dataRow.createCell(20);
            OffsetDateTime dateEnd = excelList.get(i).getDateEnd();
            dataCellDateEnd.setCellValue(dateEnd != null ? dateEnd.toString() : "");
        }
        //FileOutputStream outputStream = new FileOutputStream("D://ftp/ftp.xlsx");
        /*FileOutputStream outputStream = new FileOutputStream("/home/fintech/http/upload/ftp.xlsx");
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();*/

        JSch jsch = new JSch();
        Session session = jsch.getSession(fileProps.getUsername(), fileProps.getUrl(), Integer.parseInt(fileProps.getPort()));
        session.setPassword(fileProps.getPassword());
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
        channel.connect();

        OutputStream os = channel.put(fileProps.getPath());
        workbook.write(os);
        os.close();
        channel.disconnect();
        session.disconnect();

        return "SUCCESS";
    };

    @SneakyThrows
    public ExcelResponse exportExcelToObject(MultipartFile file, List<ExcelExportIin> dbClients) {
        ExcelResponse excelResponse = new ExcelResponse();
        List<ExcelExportRequest> excelExportRequestsNew = new ArrayList<>();
        List<ExcelExportRequest> excelExportRequestsExisting = new ArrayList<>();
        int numColumns = 0; // Объявляем переменную numColumns

        // Создаем множество для отслеживания уникальных iin значений из dbClients
        Set<String> dbIins = dbClients.stream().map(ExcelExportIin::getIin).collect(Collectors.toSet());
        Set<String> dbContractNumbers = dbClients.stream().map(ExcelExportIin::getContractNumber).collect(Collectors.toSet());

        InputStream excelFile = file.getInputStream();
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet sheet = workbook.getSheetAt(0); // номер страницы

        for (Row row : sheet) {
            if (row.getRowNum() == 0) { // Пропустить первую строку (заголовок)
                numColumns = row.getLastCellNum();
                continue;
            }
            ExcelExportRequest excelExportRequest = new ExcelExportRequest();
            for (int colNum = 0; colNum < numColumns; colNum++) {
                Cell cell = row.getCell(colNum);
                if (cell != null) {
                    switch (colNum) {
                        case 0:
                            excelExportRequest.setFio(cell.getStringCellValue());
                            break;
                        case 1:
                            long iin = (long) cell.getNumericCellValue();
                            excelExportRequest.setIin(String.valueOf(iin));
                            break;
                        case 2:
                            long phoneNumber = (long) cell.getNumericCellValue();
                            excelExportRequest.setPhoneNumber(String.valueOf(phoneNumber));
                            break;
                        case 3:
                            long phoneNumber2 = (long) cell.getNumericCellValue();
                            excelExportRequest.setPhoneNumber2(String.valueOf(phoneNumber2));
                            break;
                        case 4:
                            excelExportRequest.setContractNumber(String.valueOf((int) cell.getNumericCellValue()));
                            break;
                        case 5:
                            excelExportRequest.setEmail(cell.getStringCellValue());
                            break;
                        case 6:
                            excelExportRequest.setResidenceAddress(cell.getStringCellValue());
                            break;
                        case 7:
                            excelExportRequest.setRegistrAddress(cell.getStringCellValue());
                            break;
                        case 8:
                            excelExportRequest.setLoanAmount((int) cell.getNumericCellValue());
                            break;
                        case 9:
                            excelExportRequest.setTotalDebtAmount((int) cell.getNumericCellValue());
                            break;
                        case 10:
                            excelExportRequest.setPaymentAmount((int) cell.getNumericCellValue());
                            break;
                        case 11:
                            excelExportRequest.setOverdueAmount((int) cell.getNumericCellValue());
                            break;
                        case 12:
                            excelExportRequest.setPenalty((int) cell.getNumericCellValue());
                            break;
                        case 13:
                            excelExportRequest.setPaymentDate(OffsetDateTime.from(cell.getLocalDateTimeCellValue()));
                            break;
                        case 14:
                            excelExportRequest.setOverdueDay((int) cell.getNumericCellValue());
                            break;
                        case 15:
                            excelExportRequest.setRepaymentDate(OffsetDateTime.from(cell.getLocalDateTimeCellValue()));
                            break;
                        case 16:
                            excelExportRequest.setProductType(cell.getStringCellValue());
                            break;
                        case 17:
                            excelExportRequest.setProductName(cell.getStringCellValue());
                            break;
                        case 18:
                            excelExportRequest.setContractNumber(cell.getStringCellValue());
                            break;
                        case 19:
                            excelExportRequest.setContractOpenDate(OffsetDateTime.from(cell.getLocalDateTimeCellValue()));
                            break;
                        case 20:
                            excelExportRequest.setDateEnd(OffsetDateTime.from(cell.getLocalDateTimeCellValue()));
                            break;
                        case 21:
                            excelExportRequest.setIsArc(false);
                            break;
                        case 22:
                            excelExportRequest.setCreateDate(OffsetDateTime.from(cell.getLocalDateTimeCellValue()));
                            break;
                        case 23:
                            excelExportRequest.setCompanyId(cell.getStringCellValue());
                            break;
                        case 24:
                            excelExportRequest.setCompanyName(cell.getStringCellValue());
                            break;
                    }
                }
            }
            if (!dbContractNumbers.contains(excelExportRequest.getContractNumber())) {
                if (!excelExportRequestsNew.stream().anyMatch(req -> req.getContractNumber().equals(excelExportRequest.getContractNumber()))) {
                    excelExportRequestsNew.add(excelExportRequest);
                }
            }
            else {
                ExcelExportIin matchingIin = dbClients.stream()
                        .filter(iin -> iin.getIin().equals(excelExportRequest.getIin()))
                        .findFirst()
                        .orElse(null);

                if (matchingIin != null) {
                    excelExportRequest.setClientId(matchingIin.getClientId());
                }
                excelExportRequestsExisting.add(excelExportRequest);
            }
        }
        excelResponse.setExcelExportRequestsNew(excelExportRequestsNew);
        excelResponse.setExcelExportRequestsExisting(excelExportRequestsExisting);
        return excelResponse;
    }

//    @SneakyThrows
//    public ExcelResponse exportExcelToObject(MultipartFile file, List<ExcelExportIin> dbClients) {
//        ExcelResponse excelResponse = new ExcelResponse();
//        List<ExcelExportRequest> excelExportRequestsNew = new ArrayList<>();
//        List<ExcelExportRequest> excelExportRequestsExisting = new ArrayList<>();
//        int numColumns = 0; // Объявляем переменную numColumns
//
//        // Создаем множество для отслеживания уникальных iin значений из dbClients
//        Set<String> dbIins = dbClients.stream().map(ExcelExportIin::getIin).collect(Collectors.toSet());
//        Set<String> dbContractNumber = dbClients.stream().map(ExcelExportIin::getIin).collect(Collectors.toSet());
//
//        InputStream excelFile = file.getInputStream();
//        Workbook workbook = new XSSFWorkbook(excelFile);
//        Sheet sheet = workbook.getSheetAt(0); // номер страницы
//
//        for (Row row : sheet) {
//            if (row.getRowNum() == 0) { // Пропустить первую строку (заголовок)
//                numColumns = row.getLastCellNum();
//                continue;
//            }
//            ExcelExportRequest excelExportRequest = new ExcelExportRequest();
//            for (int colNum = 0; colNum < numColumns; colNum++) {
//                Cell cell = row.getCell(colNum);
//                if (cell != null) {
//                    switch (colNum) {
//                        case 0:
//                            excelExportRequest.setFio(cell.getStringCellValue());
//                            break;
//                        case 1:
//                            long iin = (long) cell.getNumericCellValue();
//                            excelExportRequest.setIin(String.valueOf(iin));
//                            break;
//                        case 2:
//                            long phoneNumber = (long) cell.getNumericCellValue();
//                            excelExportRequest.setPhoneNumber(String.valueOf(phoneNumber));
//                            break;
//                        case 3:
//                            long phoneNumber2 = (long) cell.getNumericCellValue();
//                            excelExportRequest.setPhoneNumber2(String.valueOf(phoneNumber2));
//                            break;
//                        case 4:
//                            excelExportRequest.setContractNumber(String.valueOf((int) cell.getNumericCellValue()));
//                            break;
//                        case 5:
//                            excelExportRequest.setEmail(cell.getStringCellValue());
//                            break;
//                        case 6:
//                            excelExportRequest.setResidenceAddress(cell.getStringCellValue());
//                            break;
//                        case 7:
//                            excelExportRequest.setRegistrAddress(cell.getStringCellValue());
//                            break;
//                        case 8:
//                            excelExportRequest.setLoanAmount((int) cell.getNumericCellValue());
//                            break;
//                        case 9:
//                            excelExportRequest.setTotalDebtAmount((int) cell.getNumericCellValue());
//                            break;
//                        case 10:
//                            excelExportRequest.setPaymentAmount((int) cell.getNumericCellValue());
//                            break;
//                        case 11:
//                            excelExportRequest.setOverdueAmount((int) cell.getNumericCellValue());
//                            break;
//                        case 12:
//                            excelExportRequest.setPenalty((int) cell.getNumericCellValue());
//                            break;
//                        case 13:
//                            excelExportRequest.setPaymentDate(LocalDate.from(cell.getLocalDateTimeCellValue()));
//                            break;
//                        case 14:
//                            excelExportRequest.setOverdueDay((int) cell.getNumericCellValue());
//                            break;
//                        case 15:
//                            excelExportRequest.setRepaymentDate(LocalDate.from(cell.getLocalDateTimeCellValue()));
//                            break;
//                        case 16:
//                            excelExportRequest.setProductType(cell.getStringCellValue());
//                            break;
//                        case 17:
//                            excelExportRequest.setProductName(cell.getStringCellValue());
//                            break;
//                        case 18:
//                            excelExportRequest.setContractNumber(cell.getStringCellValue());
//                            break;
//                        case 19:
//                            excelExportRequest.setContractOpenDate(LocalDate.from(cell.getLocalDateTimeCellValue()));
//                            break;
//                        case 20:
//                            excelExportRequest.setDateEnd(LocalDate.from(cell.getLocalDateTimeCellValue()));
//                            break;
//                        case 21:
//                            excelExportRequest.setIsArc(false);
//                            break;
//                        case 22:
//                            excelExportRequest.setCreateDate(LocalDate.from(cell.getLocalDateTimeCellValue()));
//                            break;
//                        case 23:
//                            excelExportRequest.setCompanyId(cell.getStringCellValue());
//                            break;
//                        case 24:
//                            excelExportRequest.setCompanyName(cell.getStringCellValue());
//                            break;
//                    }
//                }
//            }
//            if (!dbIins.contains(excelExportRequest.getIin()) && !dbIins.contains(excelExportRequest.getContractNumber())) {
//                excelExportRequestsNew.add(excelExportRequest);
//            } else {
//                ExcelExportIin matchingIin = dbClients.stream()
//                        .filter(iin -> iin.getIin().equals(excelExportRequest.getIin()))
//                        .findFirst()
//                        .orElse(null);
//
//                if (matchingIin != null) {
//                    excelExportRequest.setClientId(matchingIin.getClientId());
//                }
//                excelExportRequestsExisting.add(excelExportRequest);
//            }
//        }
//
//        excelResponse.setExcelExportRequestsNew(excelExportRequestsNew);
//        excelResponse.setExcelExportRequestsExisting(excelExportRequestsExisting);
//        return excelResponse;
//    }

//    @SneakyThrows
//    public List<ExcelExportRequest> exportExcelToObject(MultipartFile file, List<ExcelExportRequestRepo> dbClients) {
//        List<ExcelExportRequest> excelExportRequests = new ArrayList<>();
//        int numColumns = 0; // Объявляем переменную numColumns
//
//        // Создаем множество для отслеживания уникальных iin значений из dbClients
//        Set<String> dbIins = dbClients.stream().map(ExcelExportRequestRepo::getIin).collect(Collectors.toSet());
//
//        InputStream excelFile = file.getInputStream();
//        Workbook workbook = new XSSFWorkbook(excelFile);
//        Sheet sheet = workbook.getSheetAt(0); // номер страницы
//
//        for (Row row : sheet) {
//            if (row.getRowNum() == 0) { // Пропустить первую строку (заголовок)
//                numColumns = row.getLastCellNum();
//                continue;
//            }
//            ExcelExportRequest excelExportRequest = new ExcelExportRequest();
//            for (int colNum = 0; colNum < numColumns; colNum++) {
//                Cell cell = row.getCell(colNum);
//                if (cell != null) {
//                    switch (colNum) {
//                        case 0:
//                            excelExportRequest.setFio(cell.getStringCellValue());
//                            break;
//                        case 1:
//                            long iin = (long) cell.getNumericCellValue();
//                            excelExportRequest.setIin(String.valueOf(iin));
//                            break;
//                        case 2:
//                            long phoneNumber = (long) cell.getNumericCellValue();
//                            excelExportRequest.setPhoneNumber(String.valueOf(phoneNumber));
//                            break;
//                        case 3:
//                            long phoneNumber2 = (long) cell.getNumericCellValue();
//                            excelExportRequest.setPhoneNumber2(String.valueOf(phoneNumber2));
//                            break;
//                        case 4:
//                            excelExportRequest.setContractNumber(String.valueOf((int) cell.getNumericCellValue()));
//                            break;
//                        case 5:
//                            excelExportRequest.setEmail(cell.getStringCellValue());
//                            break;
//                        case 6:
//                            excelExportRequest.setResidenceAddress(cell.getStringCellValue());
//                            break;
//                        case 7:
//                            excelExportRequest.setRegistrAddress(cell.getStringCellValue());
//                            break;
//                        case 8:
//                            excelExportRequest.setLoanAmount((int) cell.getNumericCellValue());
//                            break;
//                        case 9:
//                            excelExportRequest.setTotalDebtAmount((int) cell.getNumericCellValue());
//                            break;
//                        case 10:
//                            excelExportRequest.setPaymentAmount((int) cell.getNumericCellValue());
//                            break;
//                        case 11:
//                            excelExportRequest.setOverdueAmount((int) cell.getNumericCellValue());
//                            break;
//                        case 12:
//                            excelExportRequest.setPenalty((int) cell.getNumericCellValue());
//                            break;
//                        case 13:
//                            excelExportRequest.setPaymentDate(LocalDate.from(cell.getLocalDateTimeCellValue()));
//                            break;
//                        case 14:
//                            excelExportRequest.setOverdueDay((int) cell.getNumericCellValue());
//                            break;
//                        case 15:
//                            excelExportRequest.setRepaymentDate(LocalDate.from(cell.getLocalDateTimeCellValue()));
//                            break;
//                        case 16:
//                            excelExportRequest.setProductType(cell.getStringCellValue());
//                            break;
//                        case 17:
//                            excelExportRequest.setProductName(cell.getStringCellValue());
//                            break;
//                        case 18:
//                            excelExportRequest.setContractNumber(cell.getStringCellValue());
//                            break;
//                        case 19:
//                            excelExportRequest.setContractOpenDate(LocalDate.from(cell.getLocalDateTimeCellValue()));
//                            break;
//                        case 20:
//                            excelExportRequest.setDateEnd(LocalDate.from(cell.getLocalDateTimeCellValue()));
//                            break;
//                    }
//                }
//            }
//
//            // Проверяем, существует ли iin в множестве уникальных iin значений из dbClients
//            if (!dbIins.contains(excelExportRequest.getIin())) {
//                excelExportRequests.add(excelExportRequest);
//            }
//        }
//        return excelExportRequests;
//    }
}
