package gaei.cn.baseapp.net.utils;

import android.widget.Toast;

import gaei.cn.baseapp.net.RxHttpManager;



/**
 * @desc:
 * @date: 2018/2/9
 * @author: YangYuKun 04118
 */


public class ToastUtils {

    private static Toast mToast;

    /**
     * Toast提示
     *
     * @param msg 提示内容
     */
    public static void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(RxHttpManager.getContext(), msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }
}
