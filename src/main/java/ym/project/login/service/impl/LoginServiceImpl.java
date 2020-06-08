package ym.project.login.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import ym.project.login.dao.LoginDAO;
import ym.project.login.service.LoginService;

import javax.annotation.Resource;
import java.util.HashMap;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Resource(name="loginDAO")
    private LoginDAO loginDAO;

    public HashMap<String, Object> selectMemberInfo(HashMap<String, Object> param) throws Exception {
        return loginDAO.selectMemberInfo(param);
    }

    public Integer insertAccLog(HashMap<String, Object> param) throws Exception {

        Integer retNo = loginDAO.insertAccLog(param);   //정상등록

        param.clear();
        Integer retNo2 = loginDAO.insertAccLog(param);  //예외발생[PK NULL]

        //트랜잭션으로 롤백

        return retNo;
    }

    public Integer insertAccLog2(HashMap<String, Object> param) throws Exception {

        //멀티 트랜잭션 테스트
        Integer retNo = loginDAO.insertAccLog(param);   //정상등록 (DB1)

        param.clear();
        Integer retNo2 = loginDAO.insertAccLog2(param);  //예외발생[PK NULL] (DB2)

        //트랜잭션으로 롤백

        return retNo;
    }

    public Integer insertUserMember(HashMap<String, Object> param) throws Exception {

        Integer retNo = loginDAO.insertUserMember(param);
        return retNo;

    }
}
