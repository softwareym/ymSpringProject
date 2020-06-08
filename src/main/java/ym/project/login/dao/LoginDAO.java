package ym.project.login.dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;

@Repository("loginDAO")
public class LoginDAO {

    @Autowired
    @Resource(name = "sqlSession")
    private SqlSession sql;

    @Autowired
    @Resource(name = "sqlSession2")
    private SqlSession sql2;

    /**
     * 사용자 정보를 조회
     * @param param
     * @return
     * @throws Exception
     */
    public HashMap<String, Object> selectMemberInfo(HashMap<String, Object> param) throws Exception {
        return sql2.selectOne("login.selectMemberInfo", param);
    }

    /**
     * 접속 로그 기록
     * @param param
     * @return
     * @throws Exception
     */
    public Integer insertAccLog(HashMap<String, Object> param) throws Exception {
        return sql2.insert("login.insertAccLog", param);
    }

    /**
     * 접속 로그 기록(멀티 트랜잭션 테스트용)
     * @param param
     * @return
     * @throws Exception
     */
    public Integer insertAccLog2(HashMap<String, Object> param) throws Exception {
        return sql2.insert("login.insertAccLog", param);
    }

    /**
     * 사용자 정보 등록
     * @param param
     * @return
     * @throws Exception
     */
    public Integer insertUserMember(HashMap<String, Object> param) throws Exception {
        return sql2.insert("login.insertUserMember", param);
    }


}
