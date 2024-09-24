package com.nemetschek.accounting.transactions.api;

import com.nemetschek.accounting.transactions.model.dto.TransactionDTO;
import com.nemetschek.accounting.transactions.model.dto.TransactionsList;
import com.nemetschek.accounting.transactions.model.type.TransactionType;
import com.nemetschek.accounting.transactions.service.TransactionService;
import com.nemetschek.util.ApiResponse;
import com.nemetschek.util.ListApiResponse;
import com.nemetschek.util.ResponseStatus;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * Transaction API
 *
 * @author arun kumar kandakatla
 */
@RequestMapping("/api/v1/accounts/{accountNumber}/transactions")
@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TransactionDTO>> create(@PathVariable("accountNumber") String accountNumber,
                                                              @Valid @RequestBody TransactionDTO transactionDTO) {
        transactionDTO = transactionDTO.withAccountNumber(accountNumber);
        TransactionDTO response = this.transactionService.createTransaction(transactionDTO);
        return ResponseEntity.ok(new ApiResponse<>(response, ResponseStatus.S));
    }

    /**
     * List all transactions.
     *
     * @param accountNumber
     * @param transactionType
     * @param from
     * @param to
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping
    public ResponseEntity<ListApiResponse<TransactionsList>>
    list(@PathVariable("accountNumber") String accountNumber,
         @RequestParam(name = "type", required = false) TransactionType transactionType,
         @RequestParam(name = "from", required = false) LocalDate from,
         @RequestParam(name = "to", required = false) LocalDate to,
         @RequestParam(name = "page", required = false, defaultValue = "0") int page,
         @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize) {

        // used default transactionDate sort
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("transactionDate").descending());

        TransactionsList transactionsList = this.transactionService.listTransactions(pageable, from, to, accountNumber,
                transactionType);

        return ResponseEntity.ok(new ListApiResponse<>(ResponseStatus.S, transactionsList));
    }

}
