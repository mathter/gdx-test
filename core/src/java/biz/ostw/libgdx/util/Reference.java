package biz.ostw.libgdx.util;

import com.badlogic.gdx.utils.Disposable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.atomic.AtomicLong;

public class Reference<T> implements InvocationHandler {

    private AtomicLong referenceCount = new AtomicLong(0);

    private final T reference;

    public static final <T extends Disposable> T get(T disposable) {

        T wrapper = (T) Proxy.newProxyInstance(disposable.getClass().getClassLoader(), new Class[]{Disposable.class}, new Reference<T>(disposable));

        return wrapper;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        return null;
    }

    private Reference(T reference) {

        this.reference = reference;
        this.add();
    }

    public void add() {
        this.referenceCount.incrementAndGet();
    }

    public void release() {
        if (this.referenceCount.decrementAndGet() == 0) {

        }
    }
}
