package com.nemetschek.util;

public record ListApiResponse<T>(
        ResponseStatus status,
        T content) {
}
