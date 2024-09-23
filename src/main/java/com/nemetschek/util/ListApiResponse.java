package com.nemetschek.util;

public record ListApiResponse<T>(
        ResponseStatus status,
        T body,
        Long total,
        int page,
        int size) {
}
