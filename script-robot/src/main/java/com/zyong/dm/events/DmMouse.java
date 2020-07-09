/**
 * Project Name:mouse-robot
 * File Name:DmMouse.java
 * Package Name:com.zyong.dm.events
 * Date:2019年12月4日下午5:52:26
 * Copyright (c) 2019, LINEWELL All Rights Reserved.
 */

package com.zyong.dm.events;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.zyong.dm.Dm;
import com.zyong.utils.Delay;

import java.util.Objects;

/**
 * ClassName:DmMouse <br/>
 * Function: 大漠鼠标功能. <br/>
 * Date:     2019年12月4日 下午5:52:26 <br/>
 * @author zhouYong
 * @version
 * @since JDK 1.8
 * @see
 */
public class DmMouse extends Dm {

    /**
     * 鼠标移动到指定位置
     * @param x
     * @param y
     * @return
     */
    public boolean moveTo(int x, int y) {
        ComThread.InitSTA();
        int state = 0;
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        Variant result = new Variant();
        result = Dispatch.call(com, "MoveTo", x, y);
        state = result.getInt();
        // 释放资源
        com.safeRelease();
        dm.safeRelease();
        ComThread.Release();
        return Objects.equals(state, 1);
    }

    /**
     * 移动到指定位置并左键单击
     * @param x
     * @param y
     * @return
     */
    public boolean moveAndLClick(int x, int y) {
        if (moveTo(x, y)) {
            Delay.start(50);
            return leftClick();
        } else {
            return false;
        }
    }

    public boolean leftClick() {
        ComThread.InitSTA();
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        Dispatch.call(com, "LeftDown");
        Delay.start(30, 60);
        Variant result2 = Dispatch.call(com, "LeftUp");
        Delay.start(30, 60);
        int state2 = result2.getInt();
        // 释放资源
        com.safeRelease();
        dm.safeRelease();
        ComThread.Release();
        return Objects.equals(state2, 1);
    }

}

