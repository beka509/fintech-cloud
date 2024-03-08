package kz.fintech.dbservice.controllers;

import kz.fintech.dbservice.services.LoanerAddressService;
import kz.fintech.dbservice.services.LoanerCallResultDemoService;
import kz.fintech.dbservice.services.LoanerContactService;
import kz.fintech.dbservice.services.LoanerService;
import kz.fintech.models.loaner.LoanerAddressDto;
import kz.fintech.models.loaner.LoanerCallResultDemoDto;
import kz.fintech.models.loaner.LoanerContactDto;
import kz.fintech.models.loaner.LoanerDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/loaner")
public class LoanerController {

    private final LoanerService loanerService;
    private final LoanerContactService loanerContactService;

    private final LoanerAddressService loanerAddressService;
    private final LoanerCallResultDemoService loanerCallResultDemoService;

    @GetMapping("/view/{loanerId}")
    public LoanerDto getLoaner(@PathVariable("loanerId") Integer loanerId) {
        return loanerService.getLoanerById(loanerId);
    }

    @GetMapping("/view/all")
    public List<LoanerDto> getAllLoaners() {
        return loanerService.getAllLoaners();
    }

    @GetMapping("/view/all/by-status")
    public List<LoanerDto> getAllLoanersByStatus() {
        return loanerService.getAllLoanersByStatus();
    }

    @PostMapping("/add")
    public LoanerDto createLoaner(@RequestBody LoanerDto loanerDTO) {
        return loanerService.saveLoaner(loanerDTO);
    }

    @PutMapping("/update/{loanerId}")
    public LoanerDto updateLoaner(@PathVariable("loanerId") Integer loanerId,
                                  @RequestBody LoanerDto loanerDto) {
        loanerService.getLoanerById(loanerId);
        loanerDto.setLoanerId(loanerId);
        return loanerService.saveLoaner(loanerDto);
    }

    @PutMapping("/update-status/{loanerId}")
    public LoanerDto updateStatusLoaner(@PathVariable("loanerId") Integer loanerId,
                                  @RequestBody LoanerDto loanerDto) {
        LoanerDto data = loanerService.getLoanerById(loanerId);
        data.setLoanerId(loanerId);
        data.setStatus(1);
        return loanerService.saveLoaner(data);
    }

    @DeleteMapping("/delete/{loanerId}")
    public void deleteLoaner(@PathVariable("loanerId") Integer loanerId) {
        loanerService.getLoanerById(loanerId);
        loanerService.deleteLoaner(loanerId);
    }

    // методы LoanerContact
    @GetMapping("/contact/view/{contactId}")
    public LoanerContactDto getLoanerContact(@PathVariable("contactId") Integer contactId) {
        return loanerContactService.getLoanerContactById(contactId);
    }

    @GetMapping("/contact/view/all")
    public List<LoanerContactDto> getAllLoanerContacts() {
        return loanerContactService.getAllLoanerContacts();
    }

    @GetMapping("/contact/by-loaner/{loanerId}")
    public List<LoanerContactDto> getLoanerContactsByLoanerId(@PathVariable("loanerId") Integer loanerId) {
        return loanerContactService.getLoanerContactByLoanerId(loanerId);
    }

    @PostMapping("/contact/add")
    public LoanerContactDto createLoanerContact(@RequestBody LoanerContactDto loanerContactDto) {
        return loanerContactService.saveLoanerContact(loanerContactDto);
    }

    @PutMapping("/contact/update/{contactId}")
    public LoanerContactDto updateLoanerContact(@PathVariable("contactId") Integer contactId,
                                                @RequestBody LoanerContactDto loanerContactDto) {
        return loanerContactService.updateLoanerContact(contactId, loanerContactDto);
    }

    @DeleteMapping("/contact/delete/{contactId}")
    public void deleteLoanerContact(@PathVariable("contactId") Integer contactId) {
        loanerContactService.deleteLoanerContact(contactId);
    }

    // Методы LoanerAddress
    @GetMapping("/address/view/{addressId}")
    public LoanerAddressDto getLoanerAddress(@PathVariable("addressId") Integer addressId) {
        return loanerAddressService.getLoanerAddressById(addressId);
    }

    @GetMapping("/address/view/all")
    public List<LoanerAddressDto> getAllLoanerAddresses() {
        return loanerAddressService.getAllLoanerAddresses();
    }

    @PostMapping("/address/add")
    public LoanerAddressDto createLoanerAddress(@RequestBody LoanerAddressDto loanerAddressDto) {
        return loanerAddressService.saveLoanerAddress(loanerAddressDto);
    }

    @PutMapping("/address/update/{addressId}")
    public LoanerAddressDto updateLoanerAddress(@PathVariable("addressId") Integer addressId,
                                                @RequestBody LoanerAddressDto loanerAddressDto) {
        return loanerAddressService.updateLoanerAddress(addressId, loanerAddressDto);
    }

    @DeleteMapping("/address/delete/{addressId}")
    public void deleteLoanerAddress(@PathVariable("addressId") Integer addressId) {
        loanerAddressService.deleteLoanerAddress(addressId);
    }

    @GetMapping("/address/by-loaner/{loanerId}")
    public List<LoanerAddressDto> getAddressByLoanerId(@PathVariable("loanerId") Integer loanerId) {
        return loanerAddressService.getAddressesByLoanerId(loanerId);
    }

    //LoanerCallResultDemo
    @GetMapping("/loanerCallResultDemo/view/{id}")
    public LoanerCallResultDemoDto getLoanerCallResultDemoById(@PathVariable("id") Integer id) {
        LoanerCallResultDemoDto dto = loanerCallResultDemoService.getById(id);
        return dto;
    }

    @GetMapping("/loanerCallResultDemo/view/all")
    public List<LoanerCallResultDemoDto> getAllLoanerCallResultDemos() {
        List<LoanerCallResultDemoDto> dtos = loanerCallResultDemoService.getAll();
        return dtos;
    }

    @PostMapping("/loanerCallResultDemo/add")
    public LoanerCallResultDemoDto saveLoanerCallResultDemo(@RequestBody LoanerCallResultDemoDto dto) {
        LoanerCallResultDemoDto savedDto = loanerCallResultDemoService.save(dto);
        return savedDto;
    }


    @PutMapping("/loanerCallResultDemo/update/{id}")
    public LoanerCallResultDemoDto updateLoanerCallResultDemo(
            @PathVariable Integer id,
            @RequestBody LoanerCallResultDemoDto dto
    ) {
        LoanerCallResultDemoDto updatedDto = loanerCallResultDemoService.update(id, dto);
        return updatedDto;
    }

    @DeleteMapping("/loanerCallResultDemo/delete/{id}")
    public void deleteLoanerCallResultDemo(@PathVariable Integer id) {
        loanerCallResultDemoService.delete(id);
    }

    @GetMapping("/loanerCallResultDemo/by-client/{clientId}")
    public ResponseEntity<List<LoanerCallResultDemoDto>> getLoanerCallResultDemosByLoanerId(@PathVariable("clientId") Integer clientId) {
        List<LoanerCallResultDemoDto> dtos = loanerCallResultDemoService.getByClientId(clientId);
        return ResponseEntity.ok(dtos);
    }
}
