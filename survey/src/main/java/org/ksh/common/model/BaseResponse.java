package org.ksh.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"header", "body"})
public class BaseResponse<T> {
    private Header header = new Header();
    private T body;

    public BaseResponse(T body) {
        setHeaderWithBaseStatusCode(BaseStatusCode.SUCCESS);
        this.body = body;
    }
    public BaseResponse(BaseStatusCode statusCode, T body) {
        setHeaderWithBaseStatusCode(statusCode);
        this.body = body;
    }

    private void setHeaderWithBaseStatusCode(BaseStatusCode statusCode) {
        this.header.setStatus(statusCode.getStatus());
        this.header.setMessage(statusCode.getMessage());
    }

    @Getter
    @Setter
    @JsonPropertyOrder({"status", "message"})
    private static class Header {
        private int status;
        private String message;

        public Header() {
            this.status = BaseStatusCode.SUCCESS.getStatus();
            this.message = BaseStatusCode.SUCCESS.getMessage();
        }
    }

}
