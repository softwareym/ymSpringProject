package ym.project.common.service;

import java.util.HashMap;
import java.util.List;

public interface CommonService {

    /**
     *  500 에러 로그 기록
     * @param param
     * @return
     * @throws Exception
     */
    void insert500ErrLog(HashMap<String, Object> param) throws Exception;

    void insert404ErrLog(HashMap<String, Object> param) throws Exception;

    List<HashMap<String, Object>> selectdelList(HashMap<String, Object> param) throws Exception;
}

