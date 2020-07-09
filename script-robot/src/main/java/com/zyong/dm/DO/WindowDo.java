/**
 * Project Name:mouse-robot
 * File Name:CoordinateDo.java
 * Package Name:com.zyong.dm.DO
 * Date:2019年12月4日下午4:27:15
 * Copyright (c) 2019, LINEWELL All Rights Reserved. 
 *
*/

package com.zyong.dm.DO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * ClassName:CoordinateDo <br/>
 * Function: 坐标轴信息. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019年12月4日 下午4:27:15 <br/>
 * @author    zhouYong
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Setter
@Getter
@ToString
public class WindowDo {
    //  坐标位置 xl xr yl yr
    private int[] position;
    
    // 窗口句柄
    private int hwnd;
    
    // 方法执行结果
    private Boolean result;
    
    // 窗口类名
    private String className;
    
    // 窗口标题
    private String title;

}

