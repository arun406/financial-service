package com.nemetschek.accounting.transactions.model.mapper;

import com.nemetschek.accounting.transactions.model.dto.TransactionDTO;
import com.nemetschek.accounting.transactions.model.entity.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    /**
     * Converts TransactionDTO to Transaction Entity
     *
     * @param dto
     * @return
     */
    Transaction toEntity(TransactionDTO dto);

    /**
     * Converts Transaction Entity to TransactionDTO
     *
     * @param entity
     * @return
     */
    TransactionDTO toDto(Transaction entity);
}
