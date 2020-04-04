package com.ies.listener;

import com.ies.utils.WebUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author fuchen on 2018/3/27
 */
@Component
public class SessionTokenInterceptor implements HandlerInterceptor {

    protected final static Logger log = Logger.getLogger(SessionTokenInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("SessionTokenInterceptor sessionId: " + request.getSession().getId());
        Object obj = WebUtils.getHttpSession().getAttribute("user");
        if (obj == null) {
            request.getRequestDispatcher("/videos/login/toLogin").forward(request,response);
            return false;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
