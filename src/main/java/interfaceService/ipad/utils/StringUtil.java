package interfaceService.ipad.utils;

import java.util.UUID;

/**
 * Created by Administrator on 2017/8/2.
 */
public class StringUtil {

    /**
     * UUID
     * @return
     */
    public static String UUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
