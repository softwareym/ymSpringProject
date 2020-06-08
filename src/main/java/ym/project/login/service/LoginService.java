package ym.project.login.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

public interface LoginService {
    /**
     * 사용자 정보를 조회한다.
     * @param param
     * @return
     * @throws Exception
     */
    HashMap<String, Object> selectMemberInfo(HashMap<String, Object> param) throws Exception;

    /**
     * 접속로그 기록
     * @param param
     * @return
     * @throws Exception
     */
    Integer insertAccLog(HashMap<String, Object> param) throws Exception;

    /**
     *  접속 로그 기록(멀티 트랜잭션 테스트용)
     * @param param
     * @return
     * @throws Exception
     */
    Integer insertAccLog2(HashMap<String, Object> param) throws Exception;

    /**
     * 사용자 정보 등록
     * @param param
     * @return
     * @throws Exception
     */
    Integer insertUserMember(HashMap<String, Object> param) throws Exception;
}

