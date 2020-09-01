package ym.project.common.controller;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ym.project.cmmn.web.CommonExceptionResolver;
import ym.project.common.Level;
import ym.project.common.service.CommonService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

//@EnableWebMvc
@Controller
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private CommonService commonService;

    //로그인 후 사용자 화면
    @RequestMapping("/main.do")
    public String main(Model model, @RequestParam HashMap<String, Object> pParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return "common/main";
    }

    @RequestMapping("/error404Log.do")
    public String error404Log(Model model, @RequestParam HashMap<String, Object> pParam, HttpServletRequest request) throws Exception {

        CommonExceptionResolver cer = new CommonExceptionResolver();

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

        String user_ip = cer.getClientIP(request);
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

        String requestCookies = "";
        Cookie cookies[] = request.getCookies();
        for(Cookie cookie : cookies) {
            String name = cookie.getName();
            String value = cookie.getValue();
            requestCookies += name+"="+value+";";
        }

        String userAgent = request.getHeader("User-Agent");
        requestCookies = requestCookies.replace("%5F","_");
        param.put("err_num", "404");    // 404 error
        param.put("error_url", error_url);
        param.put("server_ip", server_ip);
        param.put("domain", domain);
        param.put("user_ip", user_ip);
        param.put("kind","404 error");
        param.put("cookies", requestCookies);
        param.put("msg", error_trace.toString());
        param.put("err_url", error_url);
        param.put("data_post", queryString);
        param.put("referer", referer);
        param.put("user_agent", userAgent);

        try {
            commonService.insert404ErrLog(param);
        }
        catch (Exception e)
        {
            // e.printStackTrace();
        }


        return "common/error404";
    }

    /**
     * ftp 연결 및 파일 삭제
     * @param model
     * @param pParam
     * @param request
     * @param response
     * @param session
     * @throws Exception
     */
    @RequestMapping("/ftpDelImgFile")
    @ResponseBody
    public String ftpDelImgFile(Model model, @RequestParam HashMap<String, Object> pParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        String ret = "";

        HashMap<String, Object> param = new HashMap<String, Object>();

        List<HashMap<String, Object>> selectdelImgList = commonService.selectdelList(param);

        for (int i = 0; i < selectdelImgList.size(); i++) {
            String imgFolderPath =  (String) selectdelImgList.get(i).get("imgFolderPath");
            imgFolderPath = imgFolderPath.substring(0, imgFolderPath.lastIndexOf("/")+1);

            String imgFileName = (String) selectdelImgList.get(i).get("imgFileName");
            int refundSeq = Integer.parseInt(selectdelImgList.get(i).get("RefundSeq").toString());

            try {
                deleteFtp(imgFolderPath, imgFileName, refundSeq);
            } catch (Exception e) {
                e.getStackTrace();
            }
        }

        ret = "파일 삭제가 완료되었습니다. 삭제된 파일은 복구 불가능합니다.";
        return ret;
    }

    public int deleteFtp(String remoteFilePath, String fileName, int refundSeq) throws Exception {

        FTPClient ftp = null; // FTP Client 객체
        FileInputStream fis = null; // File Input Stream

        //연결 테스트 완료 XD
        String url  = "0.0.0.0";
        String id  = "id";
        String pwd  = "pwd";
        String port = "21";

        int result = -1;
        try{
            ftp = new FTPClient(); // FTP Client 객체 생성

            ftp.setControlEncoding("ISO-8859-1"); // 문자 코드를 UTF-8로 인코딩
            ftp.connect(url, Integer.parseInt(port)); // 서버접속 " "안에 서버 주소 입력 또는 "서버주소", 포트번호
            ftp.login(id, pwd); // FTP 로그인 ID, PASSWORLD 입력
            ftp.enterLocalPassiveMode(); // Passive Mode 접속일때
            ftp.changeWorkingDirectory(remoteFilePath); // 작업 디렉토리 변경
            ftp.setFileType(FTP.BINARY_FILE_TYPE); // 업로드 파일 타입 셋팅

            String test_filepath = remoteFilePath;

            //연결테스트
            if(ftp.isConnected()){
                System.out.println("연결됨");
//				String[] ftpFiles = ftp.listNames();
                int reply = ftp.getReplyCode();
                test_filepath = test_filepath + URLDecoder.decode(URLEncoder.encode(fileName,"euc-kr"),"iso-8859-1");
                System.out.println(test_filepath);
                //ftp.logout(); // FTP Log Out
            }else{
                System.out.println("연결실패");
            }

            try{

                boolean isSuccess = ftp.deleteFile(test_filepath);//파일삭제

                HashMap<String, Object> logMap = new HashMap<String, Object>();
                logMap.put("refundSeq",refundSeq);
                logMap.put("fileName",fileName);

                if (isSuccess){
                    result = 1; // 성공 -정상삭제
                }
                else{
                    System.out.println("파일을 삭제 할 수 없습니다.");            //파일없음[삭제X]
                }

            } catch(IOException ex){
                System.out.println("IO Exception : " + ex.getMessage());
            }finally{
                if (fis != null){
                    try{
                        fis.close(); // Stream 닫기
                        return result;

                    }
                    catch(IOException ex){
                        System.out.println("IO Exception : " + ex.getMessage());
                    }
                }
            }
            ftp.logout(); // FTP Log Out


        }catch(IOException e){
            System.out.println("IO:"+e.getMessage());
        }finally{
            if (ftp != null && ftp.isConnected()){
                try{
                    ftp.disconnect(); // 접속 끊기
                    return result;
                }
                catch (IOException e){
                    System.out.println("IO Exception : " + e.getMessage());
                }
            }
        }
        return result;
    }

	//path 밑의 모든 폴더와 모든 파일 삭제
	public void deleteFtpFolder(String path) {
		File folder = new File(path);
		try {
			if(folder.exists()){
				File[] folder_list = folder.listFiles(); //파일리스트 얻어오기

				for (int i = 0; i < folder_list.length; i++) {
					if(folder_list[i].isFile()) {
						folder_list[i].delete();
						System.out.println("파일이 삭제되었습니다.");//
					}else {
                        deleteFtpFolder(folder_list[i].getPath()); //재귀함수호출
						System.out.println("폴더가 삭제되었습니다.");
					}
					folder_list[i].delete();
				}
				folder.delete(); //폴더 삭제
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

    @RequestMapping("/javascriptEx.do")
    public String javascriptEx(Model model, @RequestParam HashMap<String, Object> pParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return "common/javascriptEx";
    }


    /**
     * enum 이용한 리팩토링
     * @param model
     * @param pParam
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/enumData.do")
    public String getEnumData(Model model, @RequestParam HashMap<String, Object> pParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HashMap<String, Object> param = new HashMap<String, Object>();
        String[] arrTitle1;
        String[] arrTitleDetail1;

        String[] arrTitle2;
        String[] arrTitleDetail2;

        // enum 이용한 리팩토링
        /*
         * level 별로 처리하여 세팅하는 값을 클래스로 분류하여 값 세팅
         * 하드코딩 된 값은 다른곳 에서 사용할 수 있으면
         * 하나의 클래스에서 관리하고 가독성있는 명으로 관리하자
         *
         */
        Level levelInfo = Level.findBy(1);
        arrTitle1 = levelInfo.getArrTitle();
        arrTitleDetail1 = levelInfo.getArrTitleDetail();

        model.addAttribute("arrTitle1", Arrays.toString(arrTitle1));
        model.addAttribute("arrTitleDetail1", Arrays.toString(arrTitleDetail1));

        Level levelInfo2 = Level.findBy(2);
        arrTitle2 = levelInfo2.getArrTitle();
        arrTitleDetail2 = levelInfo2.getArrTitleDetail();

        model.addAttribute("arrTitle2", Arrays.toString(arrTitle2));
        model.addAttribute("arrTitleDetail2", Arrays.toString(arrTitleDetail2));

        //지정 값이 없을 경우
        Level levelInfo3 = Level.findBy(3);
        model.addAttribute("levelInfo3", levelInfo3);
        model.addAttribute("levelValue", levelInfo3.getLevelValue());


        return "common/enumData";
    }

}
