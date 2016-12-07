package yeungkc.com.gankio_for_android_proficiency_exercise;

import android.app.Application;
import android.content.Context;

public class RoboApp extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    public static Context getContext() {
        return sContext;
    }
}
