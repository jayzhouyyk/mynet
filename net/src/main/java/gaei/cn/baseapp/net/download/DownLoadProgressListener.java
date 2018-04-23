package gaei.cn.baseapp.net.download;

/**
 * @desc:
 * @date: 2018/2/9
 * @author: YangYuKun 04118
 */

public interface DownLoadProgressListener {

    /**
     * 载进度监听
     *
     * @param bytesRead     已经下载文件的大小
     * @param contentLength 文件的大小
     * @param progress      当前进度
     * @param done          是否下载完成
     * @param filePath      文件路径
     */
    void onResponseProgress(long bytesRead, long contentLength, int progress, boolean done, String filePath);


}
