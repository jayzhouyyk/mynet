package gaei.cn.baseapp.net.observer;

import gaei.cn.baseapp.net.RxHttpManager;
import gaei.cn.baseapp.net.interfaces.LoadingView;
import gaei.cn.baseapp.net.observer.base.BaseStringObserver;
import gaei.cn.baseapp.net.utils.ToastUtils;
import io.reactivex.disposables.Disposable;

/**
 * @desc:
 * @date: 2018/2/9
 * @author: YangYuKun 04118
 */

public abstract class StringObserver extends BaseStringObserver {

    private LoadingView mLoadingView;

    public StringObserver() {
    }

    public StringObserver(LoadingView progressDialog) {
        mLoadingView = progressDialog;
    }

    /**
     * 失败回调
     *
     * @param errorMsg 错误信息
     */
    protected abstract void onError(String errorMsg);

    /**
     * 成功回调
     *
     * @param data 结果
     */
    protected abstract void onSuccess(String data);


    @Override
    public void doOnSubscribe(Disposable d) {
        RxHttpManager.addDisposable(d);
    }

    @Override
    public void doOnError(String errorMsg) {
        dismissLoading();
        ToastUtils.showToast(errorMsg);
        onError(errorMsg);
    }

    @Override
    public void doOnNext(String string) {
        onSuccess(string);
    }


    @Override
    public void doOnCompleted() {
        dismissLoading();
    }

    /**
     * 隐藏loading对话框
     */
    private void dismissLoading() {
        if (mLoadingView != null) {
            mLoadingView.dismiss();
        }
    }
}
