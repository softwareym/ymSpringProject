package ym.project.common.service.impl;

import org.springframework.stereotype.Service;
import ym.project.common.dao.CommonDAO;
import ym.project.common.service.CommonService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

@Service("commonService")
public class CommonServiceImpl implements CommonService {

    @Resource(name="commonDAO")
    private CommonDAO commonDAO;

    public void insert500ErrLog(HashMap<String, Object> param) throws Exception {
        commonDAO.insert500ErrLog(param);
    }

    public void insert404ErrLog(HashMap<String, Object> param) throws Exception {
        commonDAO.insert404ErrLog(param);
    }

    public ArrayList<HashMap<String, Object>> selectdelList(HashMap<String, Object> param) throws Exception {
        return (ArrayList<HashMap<String, Object>>) commonDAO.selectdelList(param);
    }
}
