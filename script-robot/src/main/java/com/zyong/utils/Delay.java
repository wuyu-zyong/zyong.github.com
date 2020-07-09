/**
 * Project Name:game-preset File Name:Delay.java Package Name:com.zyong.core.util Date:2018年12月13日下午12:47:19 Copyright
 * (c) 2018, ZYONG All Rights Reserved.
 *
 */

package com.zyong.utils;

/**
 * ClassName:Delay <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018年12月13日 下午12:47:19 <br/>
 * 
 * @author zhouYong
 * @version
 * @since JDK 1.8
 * @see
 */
public class Delay {
    /**
     * 等待
     * @param time 毫秒
     */
    public static void start(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }
    public static void start(int minTime,int maxTime) {
        int time = RandomUtil.getRandom(minTime, maxTime);
        start(time);
    }
}
