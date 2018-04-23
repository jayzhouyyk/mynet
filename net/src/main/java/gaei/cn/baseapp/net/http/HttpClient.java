package gaei.cn.baseapp.net.http;

import okhttp3.OkHttpClient;

/**
 * @desc:
 * @date: 2018/2/9
 * @author: YangYuKun 04118
 */

public class HttpClient {
    private static volatile HttpClient instance = null;
    private OkHttpClient.Builder builder;

    private HttpClient() {
        builder = new OkHttpClient.Builder();
    }
    public static HttpClient getInstance() {
        if (instance == null) {
            synchronized (HttpClient.class) {
                if (instance == null) {
                    instance = new HttpClient();
                }
            }
        }
        return instance;
    }

    public OkHttpClient.Builder getBuilder() {
        return builder;
    }
}
