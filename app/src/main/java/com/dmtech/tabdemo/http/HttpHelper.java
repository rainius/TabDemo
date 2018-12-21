package com.dmtech.tabdemo.http;

public class HttpHelper {
        //服务器IP地址，根据实际情况改写
        private static final String HOST = "192.168.3.10";
        private static final String HTTP_PREFIX = "http://" + HOST + "/login/";
        private static final String LOGIN_URL = HTTP_PREFIX + "login.php";
        private static final String REGISTER_URL = HTTP_PREFIX + "register.php";
        private static final String USER_INFO_URL = HTTP_PREFIX + "user_info.php";

        // 获取登录页面地址
        public static String getLoginUrl() {
            return LOGIN_URL;
        }
        // 获取注册页面地址
        public static String getRegisterUrl() {
            return REGISTER_URL;
        }
        // 获取查询用户信息页面地址
        public static String getUserInfoUrl() {
            return USER_INFO_URL;
        }
    }

