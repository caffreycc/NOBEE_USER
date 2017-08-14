package interfaceService.ipad.pojo;


import interfaceService.ipad.pojo.user.UserInfo;

/**
 * Created by Administrator on 2017/7/31.
 */
public class ResultMessage {
    public static String SUCCESS = "SUCCESS";
    public static String FAILED = "FAILED";
    private String code;
    private Long timestamp;
    //报错详细信息
    private String devMsg;
    //错误原因
    private String usrMsg;
    private String token;
    private UserInfo userInfo;

    public ResultMessage(){}

    public ResultMessage(String code, Long timestamp, String token){
        this.code = code;
        this.timestamp = timestamp;
        this.token = token;
    }

    public ResultMessage(String code, Long timestamp, String devMsg, String usrMsg){
        this.code = code;
        this.devMsg = devMsg;
        this.usrMsg = usrMsg;
        this.timestamp = timestamp;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getDevMsg() {
        return devMsg;
    }

    public void setDevMsg(String devMsg) {
        this.devMsg = devMsg;
    }

    public String getUsrMsg() {
        return usrMsg;
    }

    public void setUsrMsg(String usrMsg) {
        this.usrMsg = usrMsg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
