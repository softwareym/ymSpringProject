package ym.project.cmmn.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import ym.project.login.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * https://victorydntmd.tistory.com/176
 * Interceptor : DispatcherServlet이 실행된 후 컨트롤러에 들어오는 요청 HttpRequest와 컨트롤러가 응답하는 HttpResponse를 가로채는 역할
 */
public class CommonInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginService loginService;

    //Controller 실행 전 실행되는 메소드, false를 리턴하면 컨트롤러를 실행하지 않음
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURL().toString();

/*
        HttpSession session = request.getSession();
        String s_userid = (String)session.getAttribute("S_USERID");

        if(s_userid == null){                                   //세션이 없으면
            response.sendRedirect("/login/main.do");            //로그인 화면으로 이동
            return false;                                       //요청받은 컨트롤러를 실행하지 않는다
        }
*/
        HandlerMethod method = (HandlerMethod) handler;
        Method methodObj = method.getMethod();

        System.out.println("Bean : " + method.getBean());
        System.out.println("Method : " + methodObj );
        System.out.println("url : " + url);

        return true;


    }

    //Controller 실행 후 실행되는 메소드, 이셉션을 발생하면 실행되지 않음
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    //뷰가 클라이언트 응답을 전송한 뒤 실행
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
