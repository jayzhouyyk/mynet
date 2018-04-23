package gaei.cn.baseapp.net.observer;

import gaei.cn.baseapp.net.RxHttpManager;
import gaei.cn.baseapp.net.bean.BaseData;
import gaei.cn.baseapp.net.interfaces.LoadingView;
import gaei.cn.baseapp.net.observer.base.BaseDataObserver;
import gaei.cn.baseapp.net.utils.ToastUtils;
import io.reactivex.disposables.Disposable;

/**
 * @desc:
 * @date: 2018/2/9
 * @author: YangYuKun 04118
 */

public abstract class DataObserver<T> extends BaseDataObserver<T> {

    private LoadingView mLoadingView;

    public DataObserver() {

    }

    public DataObserver(LoadingView loadingView) {
        mLoadingView = loadingView;
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
    protected abstract void onSuccess(T data);


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
    public void doOnNext(BaseData<T> data) {
        onSuccess(data.getData());
        //可以根据需求对code统一处理
        switch (data.getCode()) {
            case 0:
            case 200:
                onSuccess(data.getData());
                break;
            case 300:
            case 500:
                onError(data.getMsg());
                break;
            default:
        }
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