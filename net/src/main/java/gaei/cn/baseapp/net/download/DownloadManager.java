package gaei.cn.baseapp.net.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import gaei.cn.baseapp.net.RxHttpManager;
import okhttp3.ResponseBody;

/**
 * @desc:
 * @date: 2018/2/9
 * @author: YangYuKun 04118
 */

public class DownloadManager {


    /**
     * 保存文件
     *
     * @param response     ResponseBody
     * @param destFileName 文件名（包括文件后缀）
     * @return 返回
     * @throws IOException
     */
    public File saveFile(ResponseBody response, final String destFileName, DownLoadProgressListener downLoadProgressListener) throws IOException {

        String destFileDir = RxHttpManager.getContext().getExternalFilesDir(null) + File.separator;

        long contentLength = response.contentLength();
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        try {
            is = response.byteStream();

            long sum = 0;

            File file =  new File(destFileName);
            if(!file.exists()){
                File dir = new File(destFileDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                file = new File(dir, destFileName);
            }
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                sum += len;
                fos.write(buf, 0, len);

                final long finalSum = sum;

                downLoadProgressListener.onResponseProgress(finalSum, contentLength, (int) ((finalSum * 1.0f / contentLength) * 100), finalSum == contentLength, file.getAbsolutePath());
            }
            fos.flush();

            return file;

        } finally {
            try {
                response.close();
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
            }

        }
    }


}
