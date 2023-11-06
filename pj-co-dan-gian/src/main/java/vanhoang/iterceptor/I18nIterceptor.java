package vanhoang.iterceptor;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class I18nIterceptor implements HandlerInterceptor {

    private final static String PARAM_NAME = "lang";
    public final static ThreadLocal<Locale> LOCALE_THREAD_LOCAL = new ThreadLocal<>();

    @Override
    public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) {
        String valueParam = request.getParameter(PARAM_NAME);
        Locale locale = (valueParam != null ? StringUtils.parseLocale(valueParam) : new Locale("vi", "vn"));
        LOCALE_THREAD_LOCAL.set(locale);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                         @Nullable Exception ex) throws Exception {
        LOCALE_THREAD_LOCAL.remove();
    }
}
