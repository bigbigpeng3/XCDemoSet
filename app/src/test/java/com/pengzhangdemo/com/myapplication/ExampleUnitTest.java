package com.pengzhangdemo.com.myapplication;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
//        System.out.println((-2) % 10);
        List<Integer> ints = new ArrayList<>();
        for (int i = 1; i < 31; i++) {
            ints.add(i);
        }

        System.out.println(ints);

        int days[] = new int[]{
            1, 2, 5, 6, 7, 8, 9, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 26, 27, 28, 29, 30
        } ;

        System.out.println(days.length);
    }


}