package yeungkc.com.gankio_for_android_proficiency_exercise.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import yeungkc.com.gankio_for_android_proficiency_exercise.model.bean.GankResult;

public class MockModelFabric {
    public static String randomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(62);
            buf.append(str.charAt(num));
        }
        return buf.toString();
    }

    public static List<GankResult> newListOfGankResult(int size,String type) {
        ArrayList<GankResult> gankResults = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            gankResults.add(newGankResult(i, type));
        }
        return gankResults;
    }

    public static GankResult newGankResult(int id,String type) {
        Random random = new Random();

        GankResult gankResult = new GankResult();
        gankResult.set_id( randomString(23) + id);
        gankResult.setDesc(randomString(23));
        gankResult.setPublishedAt(new Date(random.nextLong()));
        gankResult.setType(type);
        gankResult.setUrl("http" + randomString(23));
        gankResult.setWho(randomString(9));

        return gankResult;
    }
}
