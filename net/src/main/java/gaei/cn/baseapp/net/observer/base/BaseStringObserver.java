package gaei.cn.baseapp.net.observer.base;

import gaei.cn.baseapp.net.exception.ApiException;
import gaei.cn.baseapp.net.interfaces.IStringSubscriber;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @desc:
 * @date: 2018/2/9
 * @author: YangYuKun 04118
 */

public abstract class BaseStringObserver implements Observer<String>, IStringSubscriber {

    @Override
    public void onSubscribe(Disposable d) {
        doOnSubscribe(d);
    }

    @Override
    public void onNext(String string) {
        doOnNext(string);
    }

    @Override
    public void onError(Throwable e) {
        String error = ApiException.handleException(e).getMessage();
        doOnError(error);
    }

    @Override
    public void onComplete() {
        doOnCompleted();
    }

}
