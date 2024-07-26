package org.ksh.common.handler;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestException {
    private String name;
    private String code;
    private String message;
}
