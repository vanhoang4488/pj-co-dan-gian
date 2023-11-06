package vanhoang.dto.base;

import lombok.Getter;

@Getter
public enum ResponseStatus {
    SUCCESS(200, true, "response.status.successed"),
    FAIL(400, false, "response.status.failed");

    private int code;
    private boolean ok;
    private String message;

    ResponseStatus(int code, boolean ok, String message) {
        this.code = code;
        this.ok = ok;
        this.message = message;
    }
}
