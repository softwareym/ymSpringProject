package ym.project.cmmn.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import ym.project.common.service.CommonService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * (500) exception 처리
 *     => DispatcherServlet은 해당 HandlerExceptionResolver 에게 발생된 예외 처리에 대한 구현이 되어 있는 지 확인하여 처리가 되어 있다면 해당 HandlerExceptionResolver 에게 처리를 위임 한다.
 * 	    그리고 이 HandlerExceptionResolver 에서는 ModelAndView 를 리턴하여 처리 하는 등 Controller 처럼 처리가 가능하게 해준다
 *
 */
public class CommonExceptionResolver implements HandlerExceptionResolver {

    @Autowired
    private CommonService commonService;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object params, Exception ex) {

        InetAddress inet;
        String server_ip;
        try {
            inet = InetAddress.getLocalHost();
            server_ip = inet.getHostAddress();
        }
        catch(UnknownHostException e) {
            server_ip = "";
        }

        String domain = request.getHeader("host");
        String host_name = request.getScheme() + "://" + domain;
        String referer = request.getHeader("referer");
        String user_ip = getClientIP(request);
        HttpSession session = request.getSession();

        String queryString = "";
        String temp = "";
        HashMap<String, Object> param = new HashMap<String, Object>();

        Enumeration elems = request.getParameterNames();
        while(elems.hasMoreElements())
        {
            temp = (String) elems.nextElement();
            queryString += temp + "=" + request.getParameter(temp) + "&";
        }

        if(queryString.length() > 0)
            queryString = queryString.substring(0, queryString.length() - 1);

        String error_url = request.getRequestURI();
        StringBuilder error_trace = new StringBuilder();

        for(int i = 0; i < ex.getStackTrace().length; i++) {
            error_trace.append(ex.getStackTrace()[i] + "\r\n");
        }

        String requestCookies = "";
        Cookie cookies[] = request.getCookies();
        for(Cookie cookie : cookies) {
            String name = cookie.getName();
            String value = cookie.getValue();
            requestCookies += name+"="+value+";";
        }

        String userAgent = request.getHeader("User-Agent");
        requestCookies = requestCookies.replace("%5F","_");
        param.put("err_num", "500");    // 500 error
        param.put("error_url", error_url);
        param.put("server_ip", server_ip);
        param.put("domain", domain);
        param.put("user_ip", user_ip);
        param.put("kind", ex.getMessage().replace("\r\n", " "));
        param.put("cookies", requestCookies);
        param.put("msg", error_trace.toString());
        param.put("err_url", error_url);
        param.put("data_post", queryString);
        param.put("referer", referer);
        param.put("user_agent", userAgent);

        try {
            commonService.insert500ErrLog(param); // 에러 로그 저장
        }
        catch (Exception e)
        {
           // e.printStackTrace();
        }

        return new ModelAndView("common/error500");
    }

    public String getClientIP(HttpServletRequest request) {
        String userip = request.getHeader("X-Forwarded-For");

        if ( userip == null  || "".equals(userip) ) {
            userip = request.getRemoteAddr();
        }

        if ( userip == null  || "".equals(userip) ) {
            return "";
        }

        String[] userips = userip.split(",");
        userips[0] = StringUtils.replace(userips[0], "0:0:0:0:0:0:0:1", "127.0.0.1");
        return userips[0];
    }
}
