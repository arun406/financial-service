package com.nemetschek.util;

public record ApiResponse<T>(T content, ResponseStatus status) {
}
