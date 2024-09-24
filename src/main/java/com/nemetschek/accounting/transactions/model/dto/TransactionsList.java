package com.nemetschek.accounting.transactions.model.dto;

import java.util.List;

public record TransactionsList(List<TransactionDTO> list, int page, int size, int total) {
}
