package com.nemetschek.accounting.accounts.model.mapper;

import com.nemetschek.accounting.accounts.model.dto.AccountDTO;
import com.nemetschek.accounting.accounts.model.entity.Account;
import org.mapstruct.Mapper;

/**
 * Mapstruct Mapper class for account.
 *
 * @author arun kumar kandakatla
 */
@Mapper(componentModel = "spring")
public interface AccountMapper {

    /**
     * Converts AccountDTO to Account Entity
     *
     * @param dto
     * @return
     */
    Account toEntity(AccountDTO dto);

    /**
     * Converts Account Entity to AccountDTO
     *
     * @param entity
     * @return
     */
    AccountDTO toDto(Account entity);
}
