package automacaolivre.automationhome;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static final String TAG = MyApplication.class.getSimpleName();
    public static Context mContext = null;

    public MyApplication() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}