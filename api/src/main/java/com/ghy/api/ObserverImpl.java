package com.ghy.api;

import java.util.Observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class ObserverImpl<T> implements Observer {



    void next(T t)
    {
        onNext(t);
    }

    void Subscribe(Disposable d)
    {
        onSubscribe(d);
    }


}
