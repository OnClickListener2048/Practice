package s.practice;

import android.support.v4.media.MediaBrowserCompat;
import android.util.Log;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by dagou on 2017/9/19.
 */

public class P<T1,T2> {
    private static final String TAG  = P.class.getSimpleName();
    public P() {

        Type type = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType)type;
        Log.d(TAG, "getClass: "+getClass());
        Log.d(TAG, "type: "+type);
        Log.d(TAG, "parameterizedType: "+parameterizedType);
        Log.d(TAG, "parameterizedType1: "+parameterizedType.getActualTypeArguments()[0]);
        Log.d(TAG, "parameterizedType2: "+parameterizedType.getActualTypeArguments()[1]);
        Log.d(TAG, "P: ");
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        Type ownerType = parameterizedType.getOwnerType();
        Type rawType = parameterizedType.getRawType();
        Log.d(TAG, "actualTypeArguments: "+actualTypeArguments);
        Log.d(TAG, "ownerType: "+ownerType);
        Log.d(TAG, "rawType: "+rawType);

    }
}

class S extends P<ArrayList,LinkedList>{
    public S() {

    }
}
