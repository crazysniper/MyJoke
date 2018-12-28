package application.mybind;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Gao on 2018/12/28.
 */

public final class MyBind {

    static final Map<Class<?>, Constructor<? extends MyUnbinder>> BINDINGS = new LinkedHashMap<>();

    public static MyUnbinder bind(Activity target) {
        View sourceView = target.getWindow().getDecorView();
        Log.e("MyBind", "11111");
        return createBinding(target, sourceView);
    }

    public static MyUnbinder bind(View target) {
        return createBinding(target, target);
    }


    private static MyUnbinder createBinding(Object target, View source) {
        Class<?> targetClass = target.getClass();
        Log.e("MyBind", "222222");
        Constructor<? extends MyUnbinder> constructor = findBindingConstructorForClass(targetClass);

        Log.e("MyBind", "333333333");
        if (constructor == null) {
            return MyUnbinder.EMPTY;
        }
        try {
            Log.e("MyBind", "44444");
            return constructor.newInstance(target, source);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to invoke " + constructor, e);
        } catch (InstantiationException e) {
            throw new RuntimeException("Unable to invoke " + constructor, e);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
                throw (RuntimeException) cause;
            }
            if (cause instanceof Error) {
                throw (Error) cause;
            }
            throw new RuntimeException("Unable to create binding instance.", cause);
        }
    }

    private static Constructor<? extends MyUnbinder> findBindingConstructorForClass(Class<?> cls) {
        Constructor<? extends MyUnbinder> bindingCtor = BINDINGS.get(cls);
        if (bindingCtor != null) {
            return bindingCtor;
        }
        String clsName = cls.getName();
        Log.e("MyBind", "555555  clsName=" + clsName);
        if (clsName.startsWith("android.") || clsName.startsWith("java.")) {
            return null;
        }
        try {
            Log.e("MyBind", "66666666");
            Class<?> bindingClass = Class.forName(clsName + "_MyViewBinding");
            Log.e("MyBind", "7777777");
            bindingCtor = (Constructor<? extends MyUnbinder>) bindingClass.getConstructor(cls, View.class);
        } catch (ClassNotFoundException e) {
            Log.e("MyBind", "88888888");
            bindingCtor = findBindingConstructorForClass(cls.getSuperclass());
        } catch (NoSuchMethodException e) {
            Log.e("MyBind", "没有找到该文件_MyViewBinding");
            throw new RuntimeException("Unable to find binding constructor for " + clsName, e);
        }
        BINDINGS.put(cls, bindingCtor);
        return bindingCtor;
    }
}
