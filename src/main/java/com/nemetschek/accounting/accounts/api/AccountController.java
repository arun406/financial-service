package com.nemetschek.accounting.accounts.api;

import com.nemetschek.accounting.accounts.exception.InvalidInputException;
import com.nemetschek.accounting.accounts.model.dto.AccountDTO;
import com.nemetschek.accounting.accounts.service.AccountService;
import com.nemetschek.util.ApiResponse;
import com.nemetschek.util.ResponseStatus;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Account API
 *
 * @author arun kumar kandakatla
 */
@RequestMapping("/api/v1/accounts")
@RestController
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Endpoint to create the Account.
     *
     * @param accountDTO
     * @return
     */
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<AccountDTO>> save(@Valid @RequestBody AccountDTO accountDTO) {

        AccountDTO account = this.accountService.createAccount(accountDTO);
        ApiResponse<AccountDTO> apiResponse = new ApiResponse<>(account, ResponseStatus.S);
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/{accountId}")
    public ResponseEntity<ApiResponse<AccountDTO>> get(@PathVariable("accountId") String accountNumber) {

        if (!StringUtils.hasText(accountNumber)) {
            throw new InvalidInputException("Account number is mandatory");
        }

        AccountDTO account = this.accountService.getAccount(accountNumber);
        return ResponseEntity.ok(new ApiResponse<>(account, ResponseStatus.S));
    }

    /**
     * Update the account information.
     *
     * @param accountNumber
     * @param accountDTO
     * @return
     */
    @PutMapping("/{accountId}")
    public ResponseEntity<ApiResponse<AccountDTO>> update(@PathVariable("accountId") String accountNumber,
                                                          @RequestBody AccountDTO accountDTO) {
        if (!StringUtils.hasText(accountNumber) || accountDTO == null) {
            throw new InvalidInputException("Account information is mandatory");
        }
        AccountDTO account = this.accountService.updateAccount(accountNumber, accountDTO);
        ApiResponse<AccountDTO> apiResponse = new ApiResponse<>(account, ResponseStatus.S);
        return ResponseEntity.ok(apiResponse);
    }


    /**
     * Close the Account by account number.
     *
     * @param accountNumber
     * @return
     */
    @DeleteMapping("/{accountId}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable("accountId") String accountNumber) {

        if (!StringUtils.hasText(accountNumber)) {
            throw new InvalidInputException("Account number is mandatory");
        }

        this.accountService.deleteAccount(accountNumber);
        return ResponseEntity.ok(new ApiResponse<String>("Account closed successfully.", ResponseStatus.S));
    }
}
