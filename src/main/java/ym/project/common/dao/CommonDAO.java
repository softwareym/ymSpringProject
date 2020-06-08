package ym.project.common.dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Repository("commonDAO")
public class CommonDAO {

    @Autowired
    @Resource(name = "sqlSession2")
    private SqlSession sql2;

    /**
     * 500 에러 로그 기록
     * @param param
     * @return
     * @throws Exception
     */
    public void insert500ErrLog(HashMap<String, Object> param) throws Exception {
        sql2.insert("common.insert500ErrLog", param);
    }

    public void insert404ErrLog(HashMap<String, Object> param) throws Exception {
        sql2.insert("common.insert404ErrLog", param);
    }

    public List<HashMap<String, Object>> selectdelList(HashMap<String, Object> param) throws Exception {
        return sql2.selectList("common.selectdelList", param);
    }

}
