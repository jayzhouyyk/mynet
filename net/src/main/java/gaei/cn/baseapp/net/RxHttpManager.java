package gaei.cn.baseapp.net;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import gaei.cn.baseapp.net.constants.SPKeys;
import gaei.cn.baseapp.net.download.DownloadRetrofit;
import gaei.cn.baseapp.net.http.GlobalRxHttp;
import gaei.cn.baseapp.net.http.SingleRxHttp;
import gaei.cn.baseapp.net.upload.UploadRetrofit;
import gaei.cn.baseapp.net.utils.SPUtils;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * @desc:网络请求
 * @date: 2018/2/9
 * @author: YangYuKun 04118
 */

public class RxHttpManager {

    private static volatile RxHttpManager instance = null;
    private static List<Disposable> disposables;
    private static Application context;

    private RxHttpManager() {
        if (instance != null) {
            throw new RuntimeException("you can not create me");
        }
    }

    public static RxHttpManager getInstance() {
        checkInitialize();
        if (instance == null) {
            synchronized (RxHttpManager.class) {
                if (instance == null) {
                    instance = new RxHttpManager();
                    disposables = new ArrayList<>();
                }
            }
        }
        return instance;
    }

    /**
     * 必须在全局Application先调用，获取context上下文，否则缓存无法使用
     *
     * @param app Application
     */
    public static void init(Application app) {
        context = app;
    }

    /**
     * 检测是否调用初始化方法
     */
    private static void checkInitialize() {
        if (context == null) {
            throw new ExceptionInInitializerError("请先在全局Application中调用 RxHttpUtils.init() 初始化！");
        }
    }

    /**
     * @return 全局配置的变量
     */
    public GlobalRxHttp config() {
        return GlobalRxHttp.getInstance();
    }

    /**
     * 获取全局上下文
     */
    public static Context getContext() {
        checkInitialize();
        return context;
    }

    /**
     * 获取disposable 在onDestroy方法中取消订阅disposable.dispose()
     *
     * @param disposable disposable
     */
    public static void addDisposable(Disposable disposable) {
        if (disposables != null) {
            disposables.add(disposable);
        }
    }

    /**
     * 获取单个请求配置实例
     *
     * @return SingleRxHttp
     */
    public static SingleRxHttp getSInstance() {

        return SingleRxHttp.getInstance();
    }

    /**
     * 下载文件
     *
     * @param fileUrl
     * @return
     */
    public static Observable<ResponseBody> downloadFile(String fileUrl) {
        return DownloadRetrofit.downloadFile(fileUrl);
    }

    /**
     * 上传单张图片
     *
     * @param uploadUrl 地址
     * @param filePath  文件路径
     * @return ResponseBody
     */
    public static Observable<ResponseBody> uploadFile(String uploadUrl, String filePath) {
        return UploadRetrofit.uploadFile(uploadUrl, filePath);
    }

    /**
     * 获取Cookie
     *
     * @return HashSet
     */
    public static HashSet<String> getCookie() {
        HashSet<String> preferences = (HashSet<String>) SPUtils.get(SPKeys.COOKIE, new HashSet<String>());
        return preferences;
    }

    /**
     * 取消所有请求
     */
    public static void cancelAllRequest() {
        if (disposables != null) {
            for (Disposable disposable : disposables) {
                disposable.dispose();
            }
            disposables.clear();
        }
    }

    /**
     * 取消单个请求
     */
    public static void cancelSingleRequest(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    /**
     * 使用全局参数创建请求
     *
     * @param cls Class
     * @param <K> K
     * @return 返回
     */
    public static <K> K createApi(Class<K> cls) {
        return GlobalRxHttp.createGApi(cls);
    }

}
