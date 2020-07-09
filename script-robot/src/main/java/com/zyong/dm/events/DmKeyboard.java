package com.zyong.dm.events;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.zyong.dm.DO.WindowDo;
import com.zyong.dm.Dm;
import com.zyong.utils.Delay;

import java.util.Objects;

public class DmKeyboard extends Dm {

    /**
     *
     * KeyDown:按下某个按键.
     *
     * @author zhouYong
     * @param vkCode
     * @return
     */
    public WindowDo keyDown(byte vkCode) {
        WindowDo windowDo = new WindowDo();
        ComThread.InitSTA();
        int state = 0;
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        Variant result = new Variant();
        result = Dispatch.call(com, "KeyDown", (int)vkCode);
        state = result.getInt();
        windowDo.setResult(Objects.equals(result.getInt(), 1));
        // 释放资源
        com.safeRelease();
        dm.safeRelease();
        ComThread.Release();
        return windowDo;
    }

    /**
     * 抬起按键
     * @param vkCode
     * @return
     */
    public WindowDo keyUp(byte vkCode) {
        WindowDo windowDo = new WindowDo();
        ComThread.InitSTA();
        int state = 0;
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        Variant result = new Variant();
        result = Dispatch.call(com, "KeyUp", (int)vkCode);
        state = result.getInt();
        windowDo.setResult(Objects.equals(result.getInt(), 1));
        // 释放资源
        com.safeRelease();
        dm.safeRelease();
        ComThread.Release();
        return windowDo;
    }

    /**
     * 按某个按键
     * @param vkCode
     * @return
     */
    public void keyPress(byte vkCode) {
        keyDown(vkCode);
        Delay.start(100,200);
        keyUp(vkCode);
    }
}
