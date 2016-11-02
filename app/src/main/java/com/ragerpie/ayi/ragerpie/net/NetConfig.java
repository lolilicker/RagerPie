package com.ragerpie.ayi.ragerpie.net;

/**
 * Created by WangBo on 2016/8/15.
 */
public class NetConfig {
    //    private static final String SERVER_URL_STR = "%s/v%d/";
    private static final String SERVER_URL_STR = "%s";

    // API 要使用的 API 版本
    public final static int VERSION = 2;

    //超时
    public static final int DEFAULT_TIMEOUT = 5;

    // 要去連的環境，這邊設定為正式環境
    public static final Server server = Server.TEST;

    public enum Server {
        TEST("http://192.168.1.85:3000"),
        RELEASE("https://apiv2.okoer.com");

        public final String host;

        Server(String host) {
            this.host = host;
        }
    }

    public static String getEndPoint() {
        return String.format(SERVER_URL_STR, server.host);
    }
}
