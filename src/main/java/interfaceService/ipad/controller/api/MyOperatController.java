package interfaceService.ipad.controller.api;


import interfaceService.ipad.pojo.user.UserInfo;
import interfaceService.ipad.service.MyOperatService;
import net.sf.json.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/1.
 */
@Controller
@RequestMapping("/sec/sguser")
public class MyOperatController {

    @Autowired
    private MyOperatService myOperatService;

    /**
     * 获取用户信息
     * @return
     */
    @RequestMapping("/getuser")
    @ResponseBody
    public UserInfo getUserInfo(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("402828c74d48ae57014d7a4611eb4e41");
        userInfo.setUsername("15920148161");
        userInfo.setPhone("15920148161");
        userInfo.setEmail("test@qq.com");
        userInfo.setPosition("老板");
        userInfo.setOrgId("140b527361c443f58781bdd64d63a3c5");

/*        RLOrganizationModel rlOrganizationModel = new RLOrganizationModel();
        rlOrganizationModel.setOrgId("140b527361c443f58781bdd64d63a3c5");
        rlOrganizationModel.setName("广州索菲亚");
        rlOrganizationModel.setOrgType("零售经销商");
        rlOrganizationModel.setParOrgId("07dbea1cef964399b62d8a141b86d609");
        rlOrganizationModel.setOrgNum("Y1168");
        rlOrganizationModel.setDealerName("殷达明");
        rlOrganizationModel.setProvince("广东（粤）");
        rlOrganizationModel.setCity("广州");
        rlOrganizationModel.setCounty("待定");
        rlOrganizationModel.setAddress("待定");
        rlOrganizationModel.setWorkPhone("待定");
        rlOrganizationModel.setCellPhone("18688394977");
        rlOrganizationModel.setCreditLevel("");
        rlOrganizationModel.setCityLevel("CL004");
        rlOrganizationModel.setOrgStatus("有效");
        rlOrganizationModel.setEmail("待定");
        rlOrganizationModel.setStartDate(new Date());
        rlOrganizationModel.setCircleName("");
        rlOrganizationModel.setMallName("");
        rlOrganizationModel.setProvinceId("P012");
        rlOrganizationModel.setCityId("C196");
        userInfo.setOrganization(rlOrganizationModel);

        List<RLRoleModel> list = new ArrayList<>();
        RLRoleModel rlRoleModel = new RLRoleModel();
        rlRoleModel.setRoleCode("DEALER_ADMINISTRATOR");
        rlRoleModel.setRoleId("40280e0048ff3e360148ff503df60002");
        rlRoleModel.setRoleName("经销商系统管理员");
        list.add(rlRoleModel);
        RLRoleModel rlRoleModel1 = new RLRoleModel();
        rlRoleModel1.setRoleCode("ALIGN_ORDER");
        rlRoleModel1.setRoleId("402828c74affb956014b2571851d00b3");
        rlRoleModel1.setRoleName("经销商排单员");
        list.add(rlRoleModel1);
        userInfo.setRoles(list);*/

        return userInfo;

    }

    /**
     * 文件下载
     * @param fileId
     * @param type
     * @param request
     * @param response
     */
    @RequestMapping("/fileDown")
    @ResponseBody
    public void fileDown(String fileId, String type, HttpServletRequest request, HttpServletResponse response){
        Map<String, String> map = new HashMap<>();
        map.put("fileId", fileId);
        map.put("type", type);
        try {
            myOperatService.fileDown(map, response, request.getHeader("Authorization"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件上传
     * @param request
     * @throws IOException
     */
    @RequestMapping("/fileUpload")
    @ResponseBody
    public JSON fileUpload(HttpServletRequest request){
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request))
        {
            try {
                return myOperatService.fileUpload(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
