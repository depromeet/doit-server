package com.server.doit.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
class ResponseWrapper<T> {
    private String message;
    private T result;
}
