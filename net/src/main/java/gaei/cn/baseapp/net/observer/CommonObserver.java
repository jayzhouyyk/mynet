package gaei.cn.baseapp.net.observer;

import gaei.cn.baseapp.net.RxHttpManager;
import gaei.cn.baseapp.net.interfaces.LoadingView;
import gaei.cn.baseapp.net.observer.base.BaseObserver;
import gaei.cn.baseapp.net.utils.ToastUtils;
import io.reactivex.disposables.Disposable;

/**
 * @desc:
 * @date: 2018/2/9
 * @author: YangYuKun 04118
 */

public abstract class CommonObserver<T> extends BaseObserver<T> {


    private LoadingView mLoadingView;

    public CommonObserver() {
    }

    public CommonObserver(LoadingView loadingView) {
        mLoadingView = loadingView;
    }

    /**
     * 失败回调
     *
     * @param errorMsg
     */
    protected abstract void onError(String errorMsg);

    /**
     * 成功回调
     *
     * @param t
     */
    protected abstract void onSuccess(T t);


    @Override
    public void doOnSubscribe(Disposable d) {
        RxHttpManager.addDisposable(d);
    }

    @Override
    public void doOnError(String errorMsg) {
        if (mLoadingView != null) {
            mLoadingView.dismiss();
        }
        ToastUtils.showToast(errorMsg);
        onError(errorMsg);
    }

    @Override
    public void doOnNext(T t) {
        onSuccess(t);
    }

    @Override
    public void doOnCompleted() {
        if (mLoadingView != null) {
            mLoadingView.dismiss();
        }
    }
}
