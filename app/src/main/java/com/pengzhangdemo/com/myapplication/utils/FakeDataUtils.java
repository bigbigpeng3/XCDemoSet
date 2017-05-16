package com.pengzhangdemo.com.myapplication.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by zp on 4/15/17.
 */

public class FakeDataUtils {

    /**
     * 统一提供测试数据，不再需要自己手写测试数据了
     *
     * @return
     */
    public static List<String> getFakeStrings() {

        List<String> mData = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            mData.add("你家三爷爷" + i);
        }

        return mData;
    }

    public static List<String> getFakeTags() {

        List<String> flowTag = new ArrayList<>();

        flowTag.add("# 全民夺宝季");
        flowTag.add("# 啊啊啊啊啊啊啊");
        flowTag.add("# 儿童节福利");
        flowTag.add("# 马尔代夫七日双人游");
        flowTag.add("# 刷奖必中");
        flowTag.add("# 用小拳拳捶你胸口");
        flowTag.add("# 斗破苍穹");
        flowTag.add("# 新人求关注");

        return flowTag;
    }



    public static List<String> getMessageDetailsList() {

        List<String> mData = new ArrayList<>();

        mData.add("打扰了,请问你平常几点在线?");
        mData.add("你好");
        mData.add("明白,是的。明白,是的。明白,是的。明白,是的。明白,是的。明白,是的。明白,是的。明白,是的。明白,是的。");
        mData.add("平常都是这个时间段在,怎么了?");
        mData.add("平常都是这个时间段在,怎么了?平常都是这个时间段在,怎么了?平常都是这个时间段在,怎么了?");

        return mData;


    }


    public static List<String> getFakeGiftCount() {

        List<String> mData = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            mData.add("你家三爷爷");
        }

        return mData;
    }


    static Random random = new Random(System.currentTimeMillis());
    static List<String> stringList;

    public static String getRandomStr() {

        if (stringList == null) {
            stringList = new ArrayList<>();
            stringList.add("user1");
            stringList.add("user2");
            stringList.add("user3");
            stringList.add("user4");
            stringList.add("user5");
            stringList.add("user6");
            stringList.add("user7");
            stringList.add("user8");

        }

        int index = random.nextInt(8);
        return stringList.get(index);
    }

}
