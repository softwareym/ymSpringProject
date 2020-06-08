package ym.project.mng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ym.project.cmmn.web.CommonExceptionResolver;
import ym.project.login.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
@RequestMapping("/mng")
public class MngController {

    @Autowired
    private LoginService loginService;

    //관리자 로그인 후 메인 화면
    @RequestMapping("/main.do")
    public String main(Model model, @RequestParam HashMap<String, Object> pParam, HttpServletRequest request, HttpServletResponse response) {

        return "mng/main";
    }


}
