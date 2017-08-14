package interfaceService.ipad.service;

import interfaceService.ipad.cache.MyCache;
import interfaceService.ipad.config.EOSInterfaceConfig;
import interfaceService.ipad.pojo.user.UserAuthInfo;
import interfaceService.ipad.utils.FileUtil;
import interfaceService.ipad.utils.HttpUtil;
import interfaceService.ipad.utils.JsonUtil;
import interfaceService.ipad.utils.StringUtil;
import net.sf.json.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/1.
 */
@Service
public class MyOperatService {

    private Logger LOGGER = LogManager.getLogger(MyOperatService.class);

    @Resource(name="simpleCache")
    private MyCache myCache;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private EOSInterfaceConfig eosInterfaceConfig;

    /**
     * 获取导购个人信息
     * @param token
     * @return
     */
    public String getUserInfo(String token){
        return null;
    }

    /**
     * 文件下载
     * @param postData
     * @param response
     * @param token
     * @throws Exception
     */
    public void fileDown(Map<String, String> postData, HttpServletResponse response, String token) throws Exception{

        UserAuthInfo userAuthInfo = myCache.get(token);
        try {
            Boolean result = HttpUtil.getInstance().fileDown("http://milinkinfo.com/nobee/measure/com.sfy.dms.file.fileDown.flow", postData, response,userAuthInfo.getSessionId());
            if(!result){
                //session失效或未登录
                LOGGER.info("session失效尝试重新登录！");
                userAuthService.login(userAuthInfo.getUsername(), userAuthInfo.getPassword(), token);
                fileDown(postData, response, token);
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info(e.getMessage());
        }
    }

    /**
     * 上传文件
     * @param request
     * @return
     * @throws Exception
     */
    public JSON fileUpload(HttpServletRequest request) throws Exception{

        String token = request.getHeader("Authorization");
        UserAuthInfo userAuthInfo = myCache.get(token);

        //将request变成多部分request
        MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
        //获取multiRequest 中所有的文件名
        Iterator iter=multiRequest.getFileNames();

        String path = System.getProperty("user.dir").substring(0, System.getProperty("user.dir").lastIndexOf("\\"))
                + File.separator + "work" + File.separator + StringUtil.UUID();
        try{
            //生成临时文件夹
            File temporaryPath = new File(path);
            if (temporaryPath.exists()) {
                if (!temporaryPath.isDirectory()) {
                    temporaryPath.mkdir();
                }
            } else {
                temporaryPath.mkdir();
            }
            LOGGER.info("生成临时文件----" + path);

            while(iter.hasNext())
            {
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null)
                {
                    //EOS仅支持上传单文件
                    String filePath = path + File.separator + file.getOriginalFilename();
                    File temporary = new File(filePath);
                    //生成临时文件
                    file.transferTo(temporary);
                    //上传
                    Map<String, Object> map = new HashMap<>();
                    map.put("Filename", file.getOriginalFilename());
                    map.put("userId", "402828c7543cf35a01543d69b5c003c0");
                    map.put("tempPath", "upload\\xdj\\201708");
                    map.put("groupCode", "xdj");
                    map.put("Fdata", temporary);
                    map.put("Upload", "Submit Query");
                    String result = HttpUtil.getInstance().fileUpload("http://milinkinfo.com/nobee/commons/fileuploadComponents/com.sfy.dms.file.filecomponent.uploadFile.biz.ext", map, userAuthInfo.getSessionId());
                    if(result.indexOf("session失效或者用户未登陆") != -1){
                        //session失效或未登录
                        LOGGER.info("session失效尝试重新登录！");
                        userAuthService.login(userAuthInfo.getUsername(), userAuthInfo.getPassword(), token);
                        return fileUpload(request);
                    }
                    return JsonUtil.objectToJSON(result);
                }
            }
            return null;
        }catch (IOException e) {
            e.printStackTrace();
            LOGGER.info(e.getMessage());
            return null;
        }
        finally {
            //删除临时文件
            try {
                FileUtil.delFolder(path);
            }catch (RuntimeException e){
                LOGGER.info("删除临时文件失败！------------------");
                e.printStackTrace();
            }
        }
    }
}
