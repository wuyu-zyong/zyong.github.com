/**
 * Project Name:game-preset
 * File Name:RandomUtil.java
 * Package Name:com.zyong.core.util
 * Date:2018年12月12日下午10:00:17
 * Copyright (c) 2018, ZYONG All Rights Reserved. 
 *
 */

package com.zyong.utils;

import java.util.Random;

/**
 * ClassName:RandomUtil <br/>
 * Date:     2018年12月12日 下午10:00:17 <br/>
 * @author    zhouYong
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class RandomUtil {
    
    /**
     * 
     * getRandom:获取指定范围内的随机数.
     * @author  zhouYong
     * @param min 最小值
     * @param max 最大值
     * @return
     */
    public static int getRandom(int min,int max){
     return    new Random().nextInt(max-min+1)+min; 
    }
}

