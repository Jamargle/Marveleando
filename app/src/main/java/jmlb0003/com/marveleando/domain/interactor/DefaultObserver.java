package jmlb0003.com.marveleando.domain.interactor;

import io.reactivex.observers.DisposableObserver;

/**
 * Default {@link DisposableObserver} base class to be used whenever you want default error handling.
 */
public class DefaultObserver<T> extends DisposableObserver<T> {

    @Override
    public void onNext(final T t) {
        onAny();
        processOnNext(t);
    }

    @Override
    public void onComplete() {
        processOnComplete();
    }

    @Override
    public void onError(final Throwable exception) {
        onAny();
        processOnError(exception);
    }

    public void onAny() {
        // no-op by default.
    }

    public void processOnNext(final T t) {
        // no-op by default.
    }

    public void processOnComplete() {
        // no-op by default.
    }

    public void processOnError(final Throwable exception) {
        // no-op by default.
    }

}
