package com.nemetschek.accounting.accounts.service.impl;

import com.nemetschek.accounting.accounts.model.type.UniqueIdType;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UniqueIdGenerator {

    /**
     * Basic logic to generate a unique account number;
     *
     * @return
     */
    public static String generateUniqueId(UniqueIdType type) {
        String id = UUID.randomUUID().toString().replace("-", "");
        return switch (type) {
            case null -> throw new RuntimeException("Invalid Account Id Type");
            case A, T -> type.getPrefix().concat(id);
        };
    }
}
