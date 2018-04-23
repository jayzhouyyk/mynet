package gaei.cn.baseapp.net.upload;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

/**
 * @desc:
 * @date: 2018/2/9
 * @author: YangYuKun 04118
 */

public interface UploadFileApi {

    /**
     * 上传
     *
     * @param uploadUrl 地址
     * @param file      文件
     * @return ResponseBody
     */
    @Multipart
    @POST
    Observable<ResponseBody> uploadFile(@Url String uploadUrl,
                                        @Part MultipartBody.Part file);
}
