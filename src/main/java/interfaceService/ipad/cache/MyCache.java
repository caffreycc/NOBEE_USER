package interfaceService.ipad.cache;

import interfaceService.ipad.pojo.user.UserAuthInfo;

/**
 * Created by Administrator on 2017/8/7.
 */

public interface MyCache {
    /**
     * 获取缓存的用户信息
     * @param token
     * @return
     * @throws Exception
     */
    UserAuthInfo get(String token)throws Exception;

    /**
     * 设置用户信息到缓存中
     * @param userAuthInfo
     */
    void set(UserAuthInfo userAuthInfo);

    /**
     * 移除缓存中的用户信息
     * @param token
     */
    void remove(String token);

    /**
     * 清空缓存
     */
    void clear();
}
