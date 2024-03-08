package kz.fintech.dbservice.controllers;

import kz.fintech.dbservice.services.LoanerCommHistoryService;
import kz.fintech.models.loaner.LoanerCommHistoryDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/loaner-comm-history")
public class LoanerCommHistoryController {

    private final LoanerCommHistoryService loanerCommHistoryService;

    @PostMapping("/add")
    public LoanerCommHistoryDto addLoanerCommHistory(@RequestBody LoanerCommHistoryDto loanerCommHistoryDto) {
        return loanerCommHistoryService.addLoanerCommHistory(loanerCommHistoryDto);
    }

    @GetMapping("/view/all")
    public List<LoanerCommHistoryDto> getAllLoanerCommHistory() {
        return loanerCommHistoryService.getAllLoanerCommHistory();
    }

    @GetMapping("/by-client-id/{clientId}")
    public List<LoanerCommHistoryDto> getLoanerCommHistoryByClientId(@PathVariable("clientId") Integer clientId) {
        return loanerCommHistoryService.getLoanerCommHistoryByClientId(clientId);
    }
}
