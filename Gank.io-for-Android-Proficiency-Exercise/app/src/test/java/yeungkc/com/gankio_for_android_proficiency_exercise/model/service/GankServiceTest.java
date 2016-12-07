package yeungkc.com.gankio_for_android_proficiency_exercise.model.service;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.plugins.RxJavaPlugins;
import rx.plugins.RxJavaSchedulersHook;
import rx.schedulers.Schedulers;
import yeungkc.com.gankio_for_android_proficiency_exercise.model.bean.GankResult;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GankServiceTest {

    private static Observable<List<GankResult>> mObservable;
    private static int mCount;

    @BeforeClass
    public static void beforeClass() {
        RxJavaPlugins.getInstance().registerSchedulersHook(new RxJavaSchedulersHook(){
            @Override
            public Scheduler getIOScheduler() {
                return Schedulers.immediate();
            }
        });

        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook(){
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });
    }

    @Before
    public void setUp() {
        String categorical = "Android";
        int page = 1;
        mCount = 20;

        mObservable = GankService.Companion.getApi().categoricalData(categorical, page, mCount)
                .map(new HttpResultFunc<List<GankResult>>())
                .observeOn(AndroidSchedulers.mainThread());
    }


    @Test
    public void testCategoricalDataIsNotNull() throws Exception {
        mObservable
                .subscribe(new Action1<List<GankResult>>() {
                    @Override
                    public void call(List<GankResult> gankResults) {
                        assertNotNull(gankResults);
                    }
                });
    }

    @Test
    public void testCategoricalDataIsSizeEqualsCount() throws Exception {
        mObservable
                .subscribe(new Action1<List<GankResult>>() {
                    @Override
                    public void call(List<GankResult> gankResults) {
                        assertEquals(mCount, gankResults.size());
                    }
                });
    }
}
