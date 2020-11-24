package cn.stylefeng.guns.sys.core.auth.util;

import cn.stylefeng.roses.core.util.HttpContext;
import cn.stylefeng.roses.core.util.ToolUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static cn.stylefeng.guns.base.consts.ConstantsContext.getTokenHeaderName;

/**
 * 获取token的封装
 *
 * @author fengshuonan
 * @Date 2020/2/16 22:51
 */
public class TokenUtil {

    /**
     * 获取token的两种方法
     *
     * @author fengshuonan
     * @Date 2020/2/16 22:51
     */
    public static String getToken() {

        String authToken = null;
        //获取请求域
        HttpServletRequest request = HttpContext.getRequest();

        //权限校验的头部 这里获取的是"Authorization" 这个字符串名
        String tokenHeader = getTokenHeaderName();
        //然后在根据Authorization去请求头中查看是否携带的token值
        authToken = request.getHeader(tokenHeader);

        //header中没有的话去cookie拿值，以header为准
        if (ToolUtil.isEmpty(authToken)) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (tokenHeader.equals(cookie.getName())) {
                        authToken = cookie.getValue();
                    }
                }
            }
        }

        return authToken;
    }
}
