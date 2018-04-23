package gaei.cn.baseapp.net.observer.base;

/**
 * @desc:
 * @date: 2018/2/9
 * @author: YangYuKun 04118
 */

import gaei.cn.baseapp.net.bean.BaseData;
import gaei.cn.baseapp.net.exception.ApiException;
import gaei.cn.baseapp.net.interfaces.IDataSubscriber;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public  abstract class BaseDataObserver<T> implements Observer<BaseData<T>>, IDataSubscriber<T> {

    @Override
    public void onSubscribe(Disposable d) {
        doOnSubscribe(d);
    }

    @Override
    public void onNext(BaseData<T> baseData) {
        doOnNext(baseData);
    }

    @Override
    public void onError(Throwable e) {
        String error = ApiException.handleException(e).getMessage();
        setError(error);
    }

    @Override
    public void onComplete() {
        doOnCompleted();
    }


    private void setError(String errorMsg) {
        doOnError(errorMsg);
    }

}
