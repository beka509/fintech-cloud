package kz.fintech.models;

@SuppressWarnings("unused")
public interface BpmConstants {

    int PAGE_SIZE = 1000;

    String TYPE_STRING = "java.lang.String";
    String TYPE_DOUBLE = "java.lang.Double";
    String TYPE_INTEGER = "java.lang.Integer";
    String TYPE_BOOLEAN = "java.lang.Boolean";

    String VAR_TITLE = "title";
    String VAR_IIN_BIN = "iinBin";
    String VAR_RESULT = "result";

    String MORTGAGE_PROCESS = "MortgageLoanProcess";
    String EXPRESS_PROCESS = "ExpressLoanProcess";
    String ASSIGN_LOAN_MANAGER_PROCESS = "AssignLoanManagerProcess";

    String RESULT_OK = "OK";
    String RESULT_FAILED = "FAILED";
    String RESULT_TO_COMPLIANCE = "TO_COMPLIANCE";

    String VARIABLE_REQUEST = "request";
    String VARIABLE_LOOP_COUNTER = "loopCounter";
    String VARIABLE_CUSTOMER_IIN = "customerIin";
    String VARIABLE_DECISION = "decision";

    String NOVA_FRONT_USER = "NOVA_FRONT";

    String DEFAULT_STANDARD_SCREENING = "default";
    String BUDGET_CORRECTING_PROCESS = "BudgetCorrectingProcess";
    String SUSPICIOUS_OPERATION = "SuspiciousOperationProcess";
    String AHR_BUDGET_CORRECTING_PROCESS = "BudgetAhrProcess";
    String AHR_BUDGET_GROUP_CORRECTING_PROCESS = "BudgetGroupAhrProcess";
    String HR_ENLIST_PROCESS = "EnlistOrderHRProcess";
    String HR_CHANGE_POSITION_PROCESS = "ChangePositionProcess";
    String HR_VACATION_PROCESS = "VacationProcess";
    String HR_TRIP_PROCESS = "BusinessTripProcess";
    String HR_DISMISSION_PROCESS = "DismissionProcess";
    String ADD_COLVIR_ACCESS = "AddColvirAccess";
    String UPLOAD_PROVISION_REPORT = "UploadProvisionReportProcess";
    String MONEY_TRANSFER = "MoneyTransferProcess";
    String EXPRESS_LOAN_ENTREPRENEUR_PROCESS = "ExpressLoanEntrepreneurProcess";
    String INVEST_CARD_PROCESS = "InvestCardProcess";
    String INVEST_CARD_WORLD_PROCESS = "InvestCardWorldProcess";
    String SEND_INVEST_OPERATION_PROCESS = "SendInvestOperationProcess";
    String INVEST_WORLD_OPERATION_PROCESS = "InvestWorldOperationProcess";
    String MORTGAGE_LOAN_IPOTEKA_PROCESS = "MortgageLoanIpotekaProcess";
    String IPOTEKA_LOAN_PROCESS = "IpotekaLoanProcess";
    String MFO_LOAN_PROCESS = "MfoLoanProcess";
    String MFO_DOCS_PROCESS = "MfoLoanDocsProcess";
    String OVERDRAFT_AQUIRING_PROCESS = "OverdraftAquiringProcess";
    String CESSION_CONTRACT_PROCESS = "CessionContractProcess";
    String MFO_PAYMENT = "MfoPaymentProcess";
    String MULTICURRENCY_OPEN_PROCESS = "MulticurrencyCardOpenProcess";
    String MFO_CARD_LANDING_PROCESS = "OpenMfoCardFromLanding";
    String MFO_REVERSE_SALE = "MfoReverseSale";
    String INVEST_WORLD_NOT_RESIDENT = "OpenInvestWorldNotResidentProcess";
    String BRANCH_OPEN_CARD_NOT_RESIDENT_PROCESS = "BranchOpenCardNotResidentProcess";
    String BELIZ_TRANSFER = "TransferBelizProcess";
    String MFO_CARD_PROCESS = "MfoCardOpenProcess";
    String MFO_CARD_SUBPROCESS = "MfoCardOpenSubProcess";
    String CARD_REPLENISHMENT_PROCESS = "CardReplenishmentProcess";
    String MFO_CARD_PAYMENT_PROCESS = "MfoCardPaymentProcess";
    String MFO_CARD_PAYMENT_OGPO_PROCESS = "MfoCardPaymentComProcess";
    String ONLINE_LOAN_REPAYMENT = "OnlineLoanRepayment";
    String SUBMIT_LOAN_PAYMENT = "SubmitOnlineLoanPaymentProcess";
    String HS_COLLECTION_MAIN_PROCESS = "HSCollectionMainProcess";
    String TERMINAL_LOAN_REPAYMENT = "TerminalLoanRepayment";
    String INVEST_CARD_REPAYMENT = "InvestCardRepayment";
    String INVEST_CARD_REFILL = "InvestCardRefill";
    String SMP_SEND_MONEY = "SmpSendMoneyProcess";
    String SMP_RECEIVE_MONEY = "SmpReceiveMoneyProcess";
    String SMP_2_SEND_MONEY = "Smp2SendMoneyProcess";
    String SMP_2_RECEIVE_MONEY = "Smp2ReceiveMoneyProcess";

    String SMP_2_INBOUND_RETURN = "Smp2InboundReturnProcess";
    String OVERDRAFT_LIMIT = "OverdraftLimit";
    String BIND_CARD_TO_LOAN_REPAYMENT = "BindCardToLoanRepaymentProcess";
    String RECURRENT_LOAN_REPAYMENT = "RecurrentLoanRepaymentProcess";
    String OVERDRAFT_SALARY_PROCESS = "OverdraftSalaryProcess";
    String DELIVERY_CARD = "PrintDeliveryCard";
    String DELIVERY_CARD_PROCESS = "PrintDeliveryCardProcess";
    String MFO_CARD_PARTNER_PAYMENT_PROCESS = "MfoCardPartnerPaymentProcess";
    String MFO_CARD_CASHBACK_PAYMENT_PROCESS = "MfoCardCashbackPaymentProcess";
    String SERVICE_PROVIDER_PAYMENT_PROCESS = "ServiceProviderPaymentProcess";
    String CARD_PROCESS = "CardProcess";
    String SD_GRANT_ROLE = "ServiceDeskGrantRole";
    String GUARANTEE_OPERATION = "GuaranteeOperationProcess";
    String GUARANTEE_CREATE = "GuaranteeCreateProcess";
    String SMART_VISTA_PERSON_PRODUCT = "SmartVistaPersonProduct";
    String MORTGAGE_LOAN_SALE_PROCESS = "MortgageLoanSaleProcess";
    String MORTGAGE_LOAN_SALE_KFU_PROCESS = "MortgageLoanSaleKfuProcess";
    String MORTGAGE_LOAN_SALE_KFU_PROCESS_DEV = "MortgageLoanSaleKfuProcessDev";
    String MORTGAGE_LOAN_SALE_KFU_SUB_PROCESS = "MortgageLoanSaleKfuSubProcess";
    String MORTGAGE_LOAN_SALE_KFU_SUB_PROCESS_DEV = "MortgageLoanSaleKfuSubProcessDev";
    String MORTGAGE_LOAN_SALE_SUBPROCESS = "MortgageLoanSaleSubprocess";
    String MORTGAGE_LOAN_SALE_PAY_PROCESS = "MortgageLoanSalePayProcess";
    String OVERDRAFT_TARIFF = "OverdraftTariff";
    String GIFT_CARD = "GiftCardProcess";
    String INVOICE_PROCESS = "ElectronicInvoiceProcess";
    String SCORING_FREEPAY_PROCESS = "ScoringFreepayProcess";
    String FREEPAY_PRE_APPROVAL = "FreepayPreApproval";
    String LEGAL_ACCOUNT_PROCESS = "LegalAccountProcess";
    String FREEPAY_CARD_GENERAL_PROCESS = "FreepayCardOpenGeneralProcess";
    String DIGITAL_DOCUMENTS_PROCESS = "DigitalDocumentsDrawdownProcess";
    String PAYMENT_MOBILE_WORLD_PROCESS = "PaymentMobileWorldProcess";
    String CLIENT_AGREEMENT_PROCESS = "ClientAgreementProcess";
    String SYSTEM_LIST_HANDLER_PROCESS = "SystemListHandlerProcess";
    String SYSTEM_LIST_HANDLER_SUBPROCESS = "SystemListHandlerSubProcess";
    String BROKERAGE_REFILL = "BrokerageRefillProcess";
    String SWIFT_TRANSFER_PROCESS = "SwiftTransfer";
    String TINKOFF_COMMISSIONS_PROCESS = "TinkoffCommissionsProcess";
    String SWIFT_INCOMING_TRANSFER_PROCESS = "SwiftIncomingTransferProcess";
    String CIFRA_OUTGOING_TRANSFER_PROCESS = "CifraOutgoingTransferProcess";

    String CARD_CHANGE_PRODUCT = "CardChangeProcess";
    String DEPOSIT_APPLICATION_PROCESS = "DepositApplicationProcess";

    String BINANCE_REFILL_PROCESS = "BinanceRefillProcess";
    String BINANCE_WITHDRAWAL_PROCESS = "BinanceWithdrawalProcess";
    String CARD_CRUD_JUR_PROCESS = "CardCrudJurProcess";
    String IP_OPEN_CARD_PROCESS = "IpCardOpenProcess";
    String NEW_BUDGET_PROCESS = "BudgetProcess";
    String NEW_BUDGET_SUBPROCESS = "BudgetSubProcess";

    String TRADERNET_TRANSACTION_PROCESS = "TransactionTradernetProcess";
    String TRADERNET_TRANSACTION_PAYMENT_PROCESS = "TransactionTradernetPaymentProcess";
    String TRADERNET_TRANSACTION_PAYMENT_INC_PROCESS = "TransactionTradernetPaymentIncProcess";
    String TRADERNET_TRANSACTION_PAYMENT_DEC_PROCESS = "TransactionTradernetPaymentDecProcess";
    String TRADERNET_TRANSACTION_OPERATION_PROCESS = "TransactionTradernetOperationProcess";
    String AIFC_ACCOUNT_BOOKING_PROCESS = "AifcAccountBookingProcess";
    String SERVICE_DESK_GRANT_ROLE = "ServiceDeskGrantRole";

    String CASH_ADVANCE_PROCESS = "CashAdvanceProcess";
    String NEW_BUDGET_SUBPROCESS1 = "BudgetSubProcess1";
    String NEW_BUDGET_SUBPROCESS2 = "BudgetSubProcess2";
    String OPEN_ACCOUNT_PROCESS = "OpenAccountProcess";

    String COLVIR_CREATE_PAYMENT_ORDER = "ColvirCreatePaymentOrder";

    String RZAP_RECEIVE_PROCESS = "RzapReceiveRrocess";
    String EAR_RECEIVE_PROCESS = "EarReceiveRrocess";
    String PT_RECEIVE_PROCESS = "PtReceiveRrocess";

    String DBZ_NOT_EXISTS_51 = "DBZ_NOT_EXISTS_51";
    String DOC_NOT_ACCEPTED_54 = "DOC_NOT_ACCEPTED_54";
    String FIO_IIN_NOT_MATCHES_55 = "FIO_IIN_NOT_MATCHES_55";
    String ACC_NOT_FOUND_12 = "ACC_NOT_FOUND_12";
    String INVALID_EKNP_15 = "INVALID_EKNP_15";
    String ACCOUNT_IS_CLOSED_13 = "ACCOUNT_IS_CLOSED_13";
    String SPECIAL_ACCOUNT_52 = "SPECIAL_ACCOUNT_52";
    String OK_01 = "OK_01";
    String SWIFT_INTERNATIONAL_TRANSFER_PROCESS = "SwiftInternationalTransferProcess";
    String SWIFT_INTERNATIONAL_TRANSFER_INITIATOR = "mobileapp";
    String SWIFT_INTERNATIONAL_TRANSFER_REFER = "refer";
    String SWIFT_INTERNATIONAL_TRANSFER_ID = "swiftTransferId";
    String SWIFT_INTERNATIONAL_TRANSFER_TITLE = "title";
    String SWIFT_INTERNATIONAL_TRANSFER_NEED_COMPLIANCE = "needCompliance";
    String SWIFT_INTERNATIONAL_TRANSFER_COMPLIANCE_APPROVED = "complianceApproved";
    String SWIFT_INTERNATIONAL_TRANSFER_COMPLIANCE_MANAGER_FIO = "complianceManagerFio";

    String RESERVE_ACCOUNT = "ReserveAccountV1";

    String RZAP_SEND_PROCESS = "RzapSendProcess";
    String EAR_SEND_PROCESS = "EarSendProcess";
    String BANKRUPT_SEND_REPORT_PROCESS = "BankruptSendReportProcess";
}
