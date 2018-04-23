package gaei.cn.baseapp.net.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @desc:
 * @date: 2018/2/9
 * @author: YangYuKun 04118
 */

public class BaseData<T> {
    /**
     * 错误码
     */
    @SerializedName(value = "code", alternate = {"returnCode", "resultCode"})
    private int code;
    /**
     * 错误描述
     */
    @SerializedName(value = "message", alternate = {"msg"})
    private String msg;

    /**
     * 数据
     */
    @SerializedName(value = "data", alternate = {"result"})
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseData{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
