package ym.project.login.controller;

import org.aspectj.lang.annotation.Around;
import org.codehaus.jackson.map.util.JSONPObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ym.project.cmmn.web.CommonExceptionResolver;
import ym.project.login.service.LoginService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@EnableWebMvc
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    //로그인 화면
    @RequestMapping("/main.do")
    public String main(Model model, @RequestParam HashMap<String, Object> pParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return "login/login";
    }

    //트랜잭션 테스트
    @RequestMapping("/transactionTest")
    public void transactionTest(Model model, @RequestParam HashMap<String, Object> pParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HashMap<String, Object> param = new HashMap<String, Object>();

       /* String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }*/
        CommonExceptionResolver cer = new CommonExceptionResolver();
        String ipAddress = cer.getClientIP(request);

        String userAgent = (String) request.getHeader("User-Agent");

        param.put("userid", "software");
        param.put("ip", ipAddress);
        param.put("userAgent", userAgent);
        param.put("state", "T");

        Integer retNo = loginService.insertAccLog(param);

    }

    //멀티 트랜잭션 테스트
    @RequestMapping("/multiTransactionTest")
    public void multiTransactionTest(Model model, @RequestParam HashMap<String, Object> pParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HashMap<String, Object> param = new HashMap<String, Object>();

        CommonExceptionResolver cer = new CommonExceptionResolver();
        String ipAddress = cer.getClientIP(request);

        String userAgent = (String) request.getHeader("User-Agent");

        param.put("userid", "software");
        param.put("ip", ipAddress);
        param.put("userAgent", userAgent);
        param.put("state", "T");

        Integer retNo = loginService.insertAccLog2(param);

    }

    @SuppressWarnings("unchecked")      //검증되지 않은 연산자 관련 경고 억제
    @RequestMapping(value = "/loginOk.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public HashMap<String, Object> userLoginOk(Model model, @RequestBody HashMap<String, Object> pParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        session.setAttribute("S_USERID", pParam.get("userid"));

        HashMap<String, Object> param = new HashMap<String, Object>();

        param.put("userid", (String)pParam.get("userid"));
        HashMap<String, Object> resultMemInfo = loginService.selectMemberInfo(param);
        //model.addAttribute("resultMemInfo", resultMemInfo);

        HashMap<String, Object> result = new HashMap<String, Object>();

        //등록된 사용자가 아니면
        if(resultMemInfo != null){

            if("M".equals(resultMemInfo.get("gbn"))){
                //관리자일 경우 관리자 홈 메인으로
                result.put("status","MNG_OK");
            }else{
                //사용자일 경우
                result.put("status","USER_OK");
            }
            result.put("msg","정상적으로 로그인 되었습니다.");
            result.put("userid", (String) pParam.get("userid"));
        }else{
            result.put("status","NO");
            result.put("msg","해당 ID가 없습니다.");
            result.put("userid", (String) pParam.get("userid"));
        }

        return result;
    }



}
