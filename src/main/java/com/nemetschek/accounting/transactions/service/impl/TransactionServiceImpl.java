package com.nemetschek.accounting.transactions.service.impl;

import com.nemetschek.accounting.transactions.model.dto.TransactionDTO;
import com.nemetschek.accounting.transactions.model.dto.TransactionsList;
import com.nemetschek.accounting.transactions.model.entity.Transaction;
import com.nemetschek.accounting.transactions.model.mapper.TransactionMapper;
import com.nemetschek.accounting.transactions.model.repository.TransactionRepository;
import com.nemetschek.accounting.transactions.model.repository.TransactionSpecifications;
import com.nemetschek.accounting.transactions.model.type.TransactionStatus;
import com.nemetschek.accounting.transactions.model.type.TransactionType;
import com.nemetschek.accounting.transactions.service.AccountTransactionService;
import com.nemetschek.accounting.transactions.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Transaction Service Impl
 *
 * @author arun kumar kandakatla
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    /**
     * Creates the Transaction, Debit the account if the transaction type is O,
     * Credit the account if the transaction type is I.
     *
     * @param transactionDTO
     * @return
     */
    @Transactional
    @Override
    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        Transaction entity = this.transactionMapper.toEntity(transactionDTO);


        // transaction service based on transaction type
        AccountTransactionService transactionService = AccountTransactionServiceFactory
                .getService(transactionDTO.transactionType());

        boolean result = transactionService.performTransaction(transactionDTO);
        // update the transaction status.
        entity.setTransactionStatus(result ? TransactionStatus.S : TransactionStatus.F);

        this.transactionRepository.save(entity);

        return this.transactionMapper.toDto(entity);
    }

    @Override
    public TransactionsList listTransactions(Pageable pageable, LocalDate from, LocalDate to, String accountNumber,
                                             TransactionType type) {
        Specification<Transaction> spec = Specification.where(null);
        if (StringUtils.hasText(accountNumber)) {
            spec = spec.and(TransactionSpecifications.hasAccountNumber(accountNumber));
        }

        if (type != null) {
            spec = spec.and(TransactionSpecifications.hasTransactionType(type));
        }

        if (from != null && to != null) {
            spec = spec.and(TransactionSpecifications.hasDateRange(from, to));
        }

        Page<Transaction> all = this.transactionRepository.findAll(spec, pageable);
        if (all != null) {
            all.getTotalElements();
            all.getNumber();
            all.getSize();

            List<TransactionDTO> dtos = all.stream().map(entity -> this.transactionMapper.toDto(entity)).collect(Collectors.toList());
            return new TransactionsList(dtos, all.getNumber(), all.getSize(), all.getTotalPages());
        }
        return null;
    }
}
