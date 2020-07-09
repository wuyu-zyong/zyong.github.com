/**
 * Project Name:mouse-robot
 * File Name:DmWindows.java
 * Package Name:com.zyong.dm
 * Date:2019年12月4日下午4:02:17
 * Copyright (c) 2019, LINEWELL All Rights Reserved. 
 *
 */

package com.zyong.dm.events;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.Objects;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.zyong.constant.KeyConst;
import com.zyong.dm.DO.ElementPositionDo;
import com.zyong.dm.Dm;
import com.zyong.dm.DO.WindowDo;
import com.zyong.exception.DmException;
import com.zyong.exception.DmExceptionEnum;
import com.zyong.utils.Delay;


/**
 * ClassName:DmWindows <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019年12月4日 下午4:02:17 <br/>
 * @author    zhouYong
 * @version
 * @since    JDK 1.8
 * @see
 */
public class DmWindows extends Dm {


    /**
     * ClientToScreen:把窗口坐标转换为屏幕坐标 .
     * @author  zhouYong
     * @param hwnd 指定的窗口句柄
     * @param x 窗口X坐标
     * @param y 窗口Y坐标
     * @return xy坐标和方法调用结果
     */
    public WindowDo clientToScreen(int hwnd,int x,int y) {
        WindowDo windowDo = new WindowDo();
        windowDo.setHwnd(hwnd);
        ComThread.InitSTA();
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        Variant xVariant = new Variant(x);
        Variant yVariant = new Variant(y);
        Variant result = new Variant();
        result = Dispatch.call(com, "ClientToScreen",hwnd, xVariant, yVariant);
        int[] pos = new int[4];
        pos[0] = xVariant.getInt();;
        pos[0] = yVariant.getInt();
        windowDo.setPosition(pos);
        windowDo.setResult(Objects.equals(result.getLong(), 1L));
        // 释放资源
        com.safeRelease();
        dm.safeRelease();
        ComThread.Release();
        return windowDo;
    }

    /**
     * FindWindow:查找符合类名或者标题名的顶层可见窗口返回句柄.
     * @author  zhouYong
     * @param className 窗口类名，如果为空，则匹配所有. 这里的匹配是模糊匹配.
     * @param title 窗口标题,如果为空，则匹配所有.这里的匹配是模糊匹配.
     * @return 窗口句柄
     */
    public WindowDo findWindow(String className, String title) {
        WindowDo windowDo = new WindowDo();
        ComThread.InitSTA();
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        Variant result = new Variant();
        result = Dispatch.call(com, "FindWindow", className, title);
        // 没找到返回0
        int hwnd = result.getInt();
        windowDo.setResult(!Objects.equals(hwnd, 0));
        windowDo = getClientRect(hwnd);
        windowDo.setHwnd(hwnd);
        windowDo.setClassName(className);
        windowDo.setTitle(title);
        // 释放资源
        com.safeRelease();
        dm.safeRelease();
        ComThread.Release();
        return windowDo;
    }

    /**
     * BindWindow:后台绑定指定的窗口,并指定这个窗口的屏幕颜色获取方式,鼠标仿真模式,键盘仿真模式,以及模式设定.
     * @author  zhouYong
     * @param hwnd 句柄
     * @param display 屏幕颜色获取方式 取值有以下几种：
     * "normal" : 正常模式,平常我们用的前台截屏模式
     * "gdi" : gdi模式,用于窗口采用GDI方式刷新时. 此模式占用CPU较大.
     * "gdi2" : gdi2模式,此模式兼容性较强,但是速度比gdi模式要慢许多,如果gdi模式发现后台不刷新时,可以考虑用gdi2模式.
     * "dx2" : dx2模式,用于窗口采用dx模式刷新,如果dx方式会出现窗口所在进程崩溃的状况,可以考虑采用这种.采用这种方式要保证窗口有一部分在屏幕外.win7或者vista不需要移动也可后台.此模式占用CPU较大.
     * "dx3" : dx3模式,同dx2模式,但是如果发现有些窗口后台不刷新时,可以考虑用dx3模式,此模式比dx2模式慢许多. 此模式占用CPU较大.
     * "dx" : dx模式,等同于BindWindowEx中，display设置的"dx.graphic.2d|dx.graphic.3d",具体参考BindWindowEx注意此模式需要管理员权限
     * @param mouse 鼠标仿真模式 取值有以下几种：
     * "normal" : 正常模式,平常我们用的前台鼠标模式
     * "windows": Windows模式,采取模拟windows消息方式 同按键自带后台插件.
     * "windows2": Windows2 模式,采取模拟windows消息方式(锁定鼠标位置) 此模式等同于BindWindowEx中的mouse为以下组合
     * "dx.mouse.position.lock.api|dx.mouse.position.lock.message|dx.mouse.state.message"注意此模式需要管理员权限
     * "windows3": Windows3模式，采取模拟windows消息方式,可以支持有多个子窗口的窗口后台.
     * "dx": dx模式,采用模拟dx后台鼠标模式,这种方式会锁定鼠标输入.有些窗口在此模式下绑定时，需要先激活窗口再绑定(或者绑定以后激活)，否则可能会出现绑定后鼠标无效的情况.此模式等同于BindWindowEx中的mouse为以下组合
     * "dx.public.active.api|dx.public.active.message|dx.mouse.position.lock.api|dx.mouse.position.lock.message|dx.mouse.state.api|dx.mouse.state.message|dx.mouse.api|dx.mouse.focus.input.api|dx.mouse.focus.input.message|dx.mouse.clip.lock.api|dx.mouse.input.lock.api|dx.mouse.cursor"注意此模式需要管理员权限
     * "dx2"：dx2模式,这种方式类似于dx模式,但是不会锁定外部鼠标输入.
     * 有些窗口在此模式下绑定时，需要先激活窗口再绑定(或者绑定以后手动激活)，否则可能会出现绑定后鼠标无效的情况. 此模式等同于BindWindowEx中的mouse为以下组合
     * "dx.public.active.api|dx.public.active.message|dx.mouse.position.lock.api|dx.mouse.state.api|dx.mouse.api|dx.mouse.focus.input.api|dx.mouse.focus.input.message|dx.mouse.clip.lock.api|dx.mouse.input.lock.api| dx.mouse.cursor"
     * 注意此模式需要管理员权限
     * @param keypad 键盘仿真模式 取值有以下几种
     * "normal" : 正常模式,平常我们用的前台键盘模式
     * "windows": Windows模式,采取模拟windows消息方式 同按键的后台插件.
     * "dx": dx模式,采用模拟dx后台键盘模式。有些窗口在此模式下绑定时，需要先激活窗口再绑定(或者绑定以后激活)，否则可能会出现绑定后键盘无效的情况. 此模式等同于BindWindowEx中的keypad为以下组合
     * "dx.public.active.api|dx.public.active.message| dx.keypad.state.api|dx.keypad.api|dx.keypad.input.lock.api"注意此模式需要管理员权限
     * @param mode 模式。 取值有以下两种
     * 0 : 推荐模式此模式比较通用，而且后台效果是最好的.
     * @return
     */
    public boolean bindWindow(int hwnd,String display,String mouse,String keypad,int mode) {
        ComThread.InitSTA();
        dm = new ActiveXComponent("dm.dmsoft");
        com = dm.getObject();
        Variant result = new Variant();
        result = Dispatch.call(com, "BindWindow", display, mouse, keypad, mode);
        // 0: 失败  1: 成功
        int resultInt = result.getInt();
        // 返回0，可以调用GetLastError来查看具体失败错误码,帮助分析问题
        if (Objects.equals(resultInt, 0)) {
            // TODO error log
        }
        // 释放资源
        com.safeRelease();
        dm.safeRelease();
        ComThread.Release();
        return Objects.equals(resultInt, 1);
    }
    /**
     * SendString:向指定句柄窗口发送文本.
     *
     * @author zhouYong
     * @param hwnd
     * @param str
     * @return
     */
    public boolean sendString(int hwnd, String str) {
        ComThread.InitSTA();
        int state = 0;
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        Variant result = new Variant();
        result = Dispatch.call(com, "SendString", hwnd, str);
        state = result.getInt();
        // 释放资源
        com.safeRelease();
        dm.safeRelease();
        ComThread.Release();
        return Objects.equals(state, 1);
    }

    /**
     *
     * getMousePointWindow:获取鼠标指向的句柄.
     *
     * @author zhouYong
     * @return
     */
    public WindowDo getMousePointWindow() {
        WindowDo windowDo = new WindowDo();
        ComThread.InitSTA();
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        Variant hwndR = Dispatch.call(com, "GetMousePointWindow");
        int hwnd = hwndR.getInt();
        // 释放资源
        com.safeRelease();
        dm.safeRelease();
        ComThread.Release();
        windowDo.setResult(!Objects.equals(hwnd,0));
        windowDo.setHwnd(hwnd);
        windowDo.setClassName(getWindowClassName(hwnd).getClassName());
        windowDo.setTitle(getWindowTitle(hwnd).getTitle());
        return windowDo;
    }

    /**
     * 获取窗口类名
     * @param hwnd
     * @return
     */
    public static WindowDo getWindowClassName(int hwnd) {
        WindowDo windowDo = new WindowDo();
        ComThread.InitSTA();
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        Variant hwndR = Dispatch.call(com, "GetWindowClass",hwnd);
        String className = hwndR.getString();
        // 释放资源
        com.safeRelease();
        dm.safeRelease();
        ComThread.Release();
        windowDo.setResult(!Objects.equals(className,null));
        windowDo.setHwnd(hwnd);
        windowDo.setClassName(className);
        return windowDo;
    }
    public static WindowDo getWindowTitle(int hwnd) {
        WindowDo windowDo = new WindowDo();
        ComThread.InitSTA();
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        Variant hwndR = Dispatch.call(com, "GetWindowTitle",hwnd);
        String title = hwndR.getString();
        // 释放资源
        com.safeRelease();
        dm.safeRelease();
        ComThread.Release();
        windowDo.setResult(!Objects.equals(title,null));
        windowDo.setHwnd(hwnd);
        windowDo.setTitle(title);
        return windowDo;
    }

    /**
     * 通过剪切板发送字符(前台物理按键模式)
     * @param hwnd
     * @param str
     */
    public boolean sendPasteByForeground(int hwnd,String str) {
        // 激活窗口
        boolean ret = setWindowState(hwnd,1);
        // 鼠标移动窗口点击
        WindowDo windowDo = getClientRect(hwnd);
        DmMouse dmMouse = new DmMouse();
        int[] posi = windowDo.getPosition();
        dmMouse.moveAndLClick(posi[0]+1,posi[1]+1);
        // 获取剪切板
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        // 将传入字符串封装下
        Transferable tText = new StringSelection(str);
        // 将字符串放入剪切板
        clip.setContents(tText, null);
        // 按下Ctrl+V实现粘贴文本
        DmKeyboard dmKeyboard = new DmKeyboard();
        dmKeyboard.keyDown(KeyConst.KEY_CTRL);
        Delay.start(50);
        dmKeyboard.keyDown(KeyConst.KEY_V);
        Delay.start(50);
        dmKeyboard.keyUp(KeyConst.KEY_CTRL);
        Delay.start(50);
        dmKeyboard.keyUp(KeyConst.KEY_V);
        return ret;
    }
    /**
     * 通过剪切板发送字符(前台物理按键模式)
     * @param hwnd
     * @param str
     */
    public boolean sendPasteByBackground(int hwnd,String str) {
        // 激活窗口
        setWindowState(hwnd,1);
        // 获取剪切板
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        // 将传入字符串封装下
        Transferable tText = new StringSelection(str);
        // 将字符串放入剪切板
        clip.setContents(tText, null);
        ComThread.InitSTA();
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        Variant hwndR = Dispatch.call(com, "SendPaste",hwnd);
        int state = hwndR.getInt();
        // 释放资源
        com.safeRelease();
        dm.safeRelease();
        ComThread.Release();
        return Objects.equals(state,1);
    }

    /**
     * 设置窗口状态
     * 0 : 关闭指定窗
     * 1 : 激活指定窗口
     * 2 : 最小化指定窗口,但不激活
     * 3 : 最小化指定窗口,并释放内存,但同时也会激活窗口.
     * 4 : 最大化指定窗口,同时激活窗口.
     * 5 : 恢复指定窗口 ,但不激活
     * 6 : 隐藏指定窗口
     * 7 : 显示指定窗口
     * 8 : 置顶指定窗口
     * 9 : 取消置顶指定窗口
     * 10 : 禁止指定窗口
     * 11 : 取消禁止指定窗口
     * 12 : 恢复并激活指定窗口
     * 13 : 强制结束窗口所在进程.
     * @param hwnd
     * @param state
     * @return
     */
    public boolean setWindowState(int hwnd,int state) {
        ComThread.InitSTA();
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        Variant hwndR = Dispatch.call(com, "SetWindowState",hwnd,state);
        int ret = hwndR.getInt();
        // 释放资源
        com.safeRelease();
        dm.safeRelease();
        ComThread.Release();
        return Objects.equals(ret,1);
    }

    /**
     * 获取客户区位置
     * @param hwnd
     * @return 返回四个点位置
     */
    public WindowDo getClientRect(int hwnd) {
        WindowDo windowDo = new WindowDo();
        windowDo.setHwnd(hwnd);
        // 获取窗口类名 标题
        if (Objects.isNull(getWindowClassName(hwnd))){
            return windowDo;
        }
        windowDo.setClassName(getWindowClassName(hwnd).getClassName());
        windowDo.setTitle(getWindowTitle(hwnd).getTitle());
        int[] posi = new int[4];
        ComThread.InitSTA();
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        int lx = 0, ly = 0, rx = 0, ry = 0;
        Variant lxVariant = new Variant(lx,true);
        Variant lyVariant = new Variant(ly,true);
        Variant rxVariant = new Variant(rx,true);
        Variant ryVariant = new Variant(ry,true);
        Variant ret = Dispatch.call(com, "GetClientRect", hwnd, lxVariant, lyVariant, rxVariant, ryVariant);
        posi[0] = lxVariant.getInt();
        posi[1] = lyVariant.getInt();
        posi[2] = rxVariant.getInt();
        posi[3] = ryVariant.getInt();
        int state = ret.getInt();
        windowDo.setResult(Objects.equals(state,1));
        windowDo.setPosition(posi);
        // 释放资源
        com.safeRelease();
        dm.safeRelease();
        ComThread.Release();
        return windowDo;
    }
    /**
     *
     * FindPic:查找指定区域内的图片,位图必须是24位色格式,支持透明色<br>
     * 当图像上下左右4个顶点的颜色一样时,则这个颜色将作为透明色处理..
     *
     * @author zhouYong
     * @param lx
     * @param ly
     * @param rx
     * @param ry
     * @param pathPic
     * @param sim
     *            相似度,取值范围0.1-1.0
     * @param dir
     *            :查找方向 0: 从左到右,从上到下 1: 从左到右,从下到上 2: 从右到左,从上到下 3: 从右到左, 从下到上
     * @return
     */
    public ElementPositionDo findPic(int lx, int ly, int rx, int ry, String pathPic, double sim, int dir){
        ComThread.InitSTA();
        int[] posi = new int[2];
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        int intX = 0, intY = 0;
        Variant vX = new Variant(intX,true);
        Variant vY = new Variant(intY,true);
        Dispatch.call(com, "FindPic", lx, ly, rx, ry, pathPic, "000000", sim, dir, vX, vY);
        posi[0] = vX.getInt();
        posi[1] = vY.getInt();
        if (posi[1] < 1) {
            vX = new Variant(intX);
            vY = new Variant(intY);
            System.out.println("find pic the second time...");
            Dispatch.call(com, "FindPic", lx, ly, rx, ry, pathPic, "404040", sim, dir, vX, vY);
            posi[0] = vX.getInt();
            posi[1] = vY.getInt();
        }
        if (posi[1] < 1) {
            vX = new Variant(intX);
            vY = new Variant(intY);
            System.out.println("find pic the third time...");
            Dispatch.call(com, "FindPic", lx, ly, rx, ry, pathPic, "4545", sim, dir, vX, vY);
            posi[0] = vX.getInt();
            posi[1] = vY.getInt();
        }
        ElementPositionDo elementPositionDo = null;

        // 释放资源
        com.safeRelease();
        dm.safeRelease();
        ComThread.Release();
        if (posi[1] > 1) {
            elementPositionDo = new ElementPositionDo();
            elementPositionDo.setX(posi[0]);
            elementPositionDo.setY(posi[1]);
        }else {
            throw new DmException(DmExceptionEnum.SCRIPT_ROBOT_PIC_NOT_FOUND.setMsg(DmExceptionEnum.SCRIPT_ROBOT_PIC_NOT_FOUND.getMsg()+"\t"+pathPic));
        }

        return elementPositionDo;
    }

}

