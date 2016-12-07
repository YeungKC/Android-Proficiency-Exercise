package yeungkc.com.gankio_for_android_proficiency_exercise.model.db;


import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

import yeungkc.com.gankio_for_android_proficiency_exercise.RoboApp;
import yeungkc.com.gankio_for_android_proficiency_exercise.model.bean.GankResult;
import yeungkc.com.gankio_for_android_proficiency_exercise.util.MockModelFabric;

@RunWith(RobolectricTestRunner.class)
@Config(application = RoboApp.class)
public class GankDbTest {

    private GankDb mGankDb;
    private static final String TYPE = "Android";
    private static final int size = 20;

    @Before
    public void setUp() {
        mGankDb = new GankDb(RuntimeEnvironment.application);
    }

    @org.junit.Test
    public void saveAndGetGank() throws Exception {
        mGankDb.saveGank(MockModelFabric.newListOfGankResult(size, TYPE));
        List<GankResult> dataSet = mGankDb.getDataSet(TYPE);
        Assert.assertNotNull(dataSet);
        Assert.assertEquals(size, dataSet.size());
    }

    @org.junit.Test
    public void updateAndGetGank() throws Exception {
        String id;

        List<GankResult> dataSet = MockModelFabric.newListOfGankResult(size, TYPE);
        mGankDb.saveGank(dataSet);

        GankResult gankResult = dataSet.get(0);
        id = gankResult._id;
        gankResult.desc = "test";
        dataSet.set(0,gankResult);

        mGankDb.saveGank(dataSet);

        dataSet = mGankDb.getDataSet(TYPE);
        Assert.assertNotNull(gankResult);
        for (GankResult result : dataSet) {
            if (!result._id.equals(id)) continue;

            Assert.assertEquals("test", result.desc);
        }
    }
}