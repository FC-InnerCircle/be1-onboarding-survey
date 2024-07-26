package org.ksh.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum BaseStatusCode {
    SUCCESS(200, "Success"),
    BAD_REQUEST(400, "Bad Request"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "API Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    // 커스텀 코드
    DATA_NOT_FOUND(900, "Data Not Found");

    private int status;
    private String message;

    private static final Map<Integer, BaseStatusCode> STATUS_CODE_MAP = new HashMap<>();

    static {
        for (BaseStatusCode baseStatusCode : BaseStatusCode.values()) {
            STATUS_CODE_MAP.put(baseStatusCode.status, baseStatusCode);
        }
    }

    public static BaseStatusCode getBaseStatusCodeByStatus(int status) {
        return STATUS_CODE_MAP.get(status);
    }

}
