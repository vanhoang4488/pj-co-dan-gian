package vanhoang.dto.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.MessageSource;
import vanhoang.iterceptor.I18nIterceptor;
import vanhoang.util.BeanUtil;

@Setter
@Getter
public class ResultResponse<T> {
    private int code;
    private String message;
    private T data;

    public static ResultResponse.Builder builder() {
        return new ResultResponse.Builder();
    }

    static class Builder {
        int code;
        String message;

        public Builder code(int code) {
            this.code = code;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public <T> ResultResponse<T> build() {
            MessageSource i18n = BeanUtil.getBean(MessageSource.class);
            ResultResponse<T> result = new ResultResponse<>();
            result.setCode(this.code);
            String message = i18n.getMessage(this.message, null, this.message, I18nIterceptor.LOCALE_THREAD_LOCAL.get());
            result.setMessage(message);
            return result;
        }
    }

    public void setData (T data) {
        this.data = data;
    }

    public static <T> ResultResponse<T> success() {
        return ResultResponse.builder()
                .code(ResponseStatus.SUCCESS.getCode())
                .message(ResponseStatus.SUCCESS.getMessage())
                .build();
    }
    public static <T> ResultResponse<T> success(T data) {
        ResultResponse<T> result = ResultResponse.success();
        result.setData(data);
        return result;
    }

    public static <T> ResultResponse<T> failed() {
        return ResultResponse.builder()
                .code(ResponseStatus.FAIL.getCode())
                .message(ResponseStatus.FAIL.getMessage())
                .build();
    }
}
