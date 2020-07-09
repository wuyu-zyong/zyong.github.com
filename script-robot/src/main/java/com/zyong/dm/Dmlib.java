/**
 * Project Name:dmDLLProject File Name:DmlLibrary.java Package Name:dm.KU Date:2018年12月22日下午3:05:52 Copyright (c) 2018,
 * ZYONG All Rights Reserved.
 *
 */

package com.zyong.dm;

import java.util.Random;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * ClassName:DmlLibrary <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018年12月22日 下午3:05:52 <br/>
 * 
 * @author zhouYong
 * @version
 * @since JDK 1.8
 * @see
 */
public class Dmlib {
    private static final String IMG_PATH = "";
    private static ActiveXComponent dm = null;
    private static Dispatch com = null;

    protected static String Ocr(String color) {
        ComThread.InitSTA();
        int[] posi = new int[2];
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        // 设置字库文件
        Variant setResult = Dispatch.call(com, "SetDict", 0, "D:\\QM-workspace\\视频参考\\大漠插件\\ocr_zyong.txt");
        System.out.println("字库设置结果：" + setResult);
        Variant setResult2 = Dispatch.call(com, "Ocr", 73, 165, 100, 185, "ffffff-000000", 1);
        String content = setResult2.getString();
        // 释放资源
        // ComThread.Release();
        System.out.println("文字识别结果：" + content);
        return content;
    }

    /**
     * FindStr:(这里用一句话描述这个方法的作用).
     * 
     * @author zhouYong
     * @param findContent
     *            查找内容 ：中断
     * @param color
     *            颜色特征 ffffff-000000
     */
    protected static int[] FindStr(int[] arear, String findContent, String color) {
        ComThread.InitSTA();
        int[] posi = new int[2];
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        // 设置字库文件
        Variant setResult = Dispatch.call(com, "SetDict", 0, "D:\\QM-workspace\\视频参考\\大漠插件\\ocr_zyong.txt");
        System.out.println("字库设置结果：" + setResult);
        int intX = 0, intY = 0;
        Variant vX = new Variant(intX);
        Variant vY = new Variant(intY);
        Dispatch.call(com, "FindStr", arear[0], arear[1], arear[2], arear[3], findContent, color, 1, vX, vY);
        posi[0] = vX.getInt();
        posi[1] = vY.getInt();
        // 释放资源
        // ComThread.Release();
        System.out.println("文字查找结果：" + posi[0] + ":" + posi[1]);
        return posi;
    }

    /**
     * 
     * BindWindow:绑定窗口句柄.
     * 
     * @author zhouYong
     * @param hwnd
     * @return
     */
    protected static int BindWindow(int hwnd) {
        String displayIn = "normal";
        String mouseIn = "normal";
        String keypadIn = "normal";
        int modeIn = 0;
        ComThread.InitSTA();
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        Variant display = new Variant(displayIn);
        Variant mouse = new Variant(mouseIn);
        Variant keypad = new Variant(keypadIn);
        Variant mode = new Variant(modeIn);

        Variant result = new Variant();
        result = Dispatch.call(com, "BindWindow", hwnd, display, mouse, keypad, mode);
        int state = result.getInt();
        // 释放资源
        // ComThread.Release();
        return state;
    }

    /**
     * 
     * UnBindWindow:解除绑定窗口.
     * 
     * @author zhouYong
     * @return
     */
    protected static int UnBindWindow() {
        ComThread.InitSTA();
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();

        Variant result = new Variant();
        result = Dispatch.call(com, "UnBindWindow");
        int state = result.getInt();
        // 释放资源
        // ComThread.Release();
        return state;
    }

    /**
     * 
     * GetScreenWidth:获取真正屏幕宽度.
     * 
     * @author zhouYong
     * @return
     */
    protected static int GetScreenWidthRel() {
        ComThread.InitSTA();
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();

        Variant result = new Variant();
        result = Dispatch.call(com, "GetScreenWidth");
        int state = result.getInt();
        // 释放资源
        // ComThread.Release();
        return state;
    }

    /**
     * 
     * GetScreenHeight:获取真正屏幕的高度.
     * 
     * @author zhouYong
     * @return
     */
    protected static int GetScreenHeightRel() {
        ComThread.InitSTA();
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();

        Variant result = new Variant();
        result = Dispatch.call(com, "GetScreenHeight");
        int state = result.getInt();
        // 释放资源
        // ComThread.Release();
        return state;
    }

    /**
     * 
     * GetCursorPos:获取当前鼠标位置，如果绑定了窗口，<br>
     * 那么获取的坐标是相对于绑定窗口，否则是屏幕坐标. .
     * 
     * @author zhouYong
     * @return
     */
    protected static int[] GetCursorPos() {
        int[] posi = new int[2];
        ComThread.InitSTA();
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        int x = 0, y = 0;
        Variant xVariant = new Variant(x);
        Variant yVariant = new Variant(y);
        Dispatch.call(com, "GetCursorPos", xVariant, yVariant);
        posi[0] = xVariant.getInt();
        posi[1] = yVariant.getInt();
        // 释放资源
        // ComThread.Release();
        return posi;
    }

    /**
     * 
     * GetKeyState:获取按键状态.
     * 
     * @author zhouYong
     * @param vkCode
     * @return 0弹起 1按下
     */
    protected static int GetKeyState(int vkCode) {
        ComThread.InitSTA();
        int state = 0;
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        Variant result = new Variant();
        result = Dispatch.call(com, "GetKeyState", vkCode);
        state = result.getInt();
        // 释放资源
        // ComThread.Release();
        return state;
    }


    /**
     * 
     * MoveTo:(这里用一句话描述这个方法的作用).
     * 
     * @author zhouYong
     * @param x
     * @param y
     * @return
     */
    private static int MoveTo(int x, int y) {
        ComThread.InitSTA();
        int state = 0;
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        Variant result = new Variant();
        result = Dispatch.call(com, "MoveTo", x, y);
        state = result.getInt();
        // 释放资源
        // ComThread.Release();
        return state;
    }

    /**
     * SendString:向指定句柄窗口发送文本.
     * 
     * @author zhouYong
     * @param hwnd
     * @param str
     * @return
     */
    protected static int SendString(int hwnd, String str) {
        ComThread.InitSTA();
        int state = 0;
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        Variant result = new Variant();
        result = Dispatch.call(com, "SendString", hwnd, str);
        state = result.getInt();
        // 释放资源
        // ComThread.Release();
        return state;
    }

    /**
     * 
     * KeyDown:按下某个按键.
     * 
     * @author zhouYong
     * @param vkCode
     * @return
     */
    protected static int KeyDown(byte vkCode) {
        ComThread.InitSTA();
        int state = 0;
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        Variant result = new Variant();
        result = Dispatch.call(com, "KeyDown", (int)vkCode);
        state = result.getInt();
        // 释放资源
        // ComThread.Release();
        return state;
    }

    /**
     * 
     * KeyUp:抬起某个按键.
     * 
     * @author zhouYong
     * @param vkCode
     * @return
     */
    protected static int KeyUp(byte vkCode) {
        ComThread.InitSTA();
        int state = 0;
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        Variant result = new Variant();
        result = Dispatch.call(com, "KeyUp", (int)vkCode);
        state = result.getInt();
        // 释放资源
        // ComThread.Release();
        return state;
    }

    protected static int KeyPress(byte vkCode) {
        ComThread.InitSTA();
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        Dispatch.call(com, "KeyDown", (int)vkCode);
        start(getRandom(20, 50));
        Variant result2 = Dispatch.call(com, "KeyUp", (int)vkCode);
        int state2 = result2.getInt();
        // 释放资源
        // //ComThread.Release();
       start(getRandom(20, 40));
        return state2;
    }

    protected static int LeftClick() {
        ComThread.InitSTA();
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        Dispatch.call(com, "LeftDown");
       start(getRandom(30, 60));
        Variant result2 = Dispatch.call(com, "LeftUp");
       start(getRandom(80, 120));
        int state2 = result2.getInt();
        // 释放资源
        // //ComThread.Release();
        return state2;
    }

    protected static int RightClick() {
        ComThread.InitSTA();
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        Dispatch.call(com, "RightDown");
       start(getRandom(30, 60));
        Variant result2 = Dispatch.call(com, "RightUp");
       start(getRandom(80, 120));
        int state2 = result2.getInt();
        // 释放资源
        // //ComThread.Release();
        return state2;
    }

    protected static int LeftDoubleClick() {
        ComThread.InitSTA();
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        Variant result2 = null;
        int state2;
        for (int i = 0; i < 2; i++) {
            Dispatch.call(com, "LeftDown");
           start(getRandom(60, 80));
            result2 = Dispatch.call(com, "LeftUp");
           start(getRandom(60, 80));
        }

        state2 = result2.getInt();
        // 释放资源
        // ComThread.Release();
        return state2;
    }

    /**
     * 
     * GetClientRect:获取VM镜像系统桌面位置.
     * 
     * @author zhouYong
     * @param hwnd
     * @return 0左X 1左Y 2右X 3右Y
     */
    protected static int[] GetClientRect(int hwnd) {
        int[] posi = new int[4];
        ComThread.InitSTA();
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        int lx = 0, ly = 0, rx = 0, ry = 0;
        Variant lxVariant = new Variant(lx);
        Variant lyVariant = new Variant(ly);
        Variant rxVariant = new Variant(rx);
        Variant ryVariant = new Variant(ry);
        Dispatch.call(com, "GetClientRect", hwnd, lxVariant, lyVariant, rxVariant, ryVariant);
        posi[0] = lxVariant.getInt();
        posi[1] = lyVariant.getInt();
        posi[2] = rxVariant.getInt();
        posi[3] = ryVariant.getInt();
        // 释放资源
        // ComThread.Release();
        return posi;
    }

    /**
     * 
     * GetClientSize:获取客户区大小.
     * 
     * @author zhouYong
     * @param hwnd
     * @return
     */
    protected static int[] GetClientSize(int hwnd) {
        int[] posi = new int[2];
        ComThread.InitSTA();
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        int width = 0, height = 0;
        Variant widthR = new Variant(width);
        Variant heightR = new Variant(height);
        Dispatch.call(com, "GetClientSize", hwnd, widthR, heightR);
        posi[0] = widthR.getInt();
        posi[1] = heightR.getInt();
        // 释放资源
        // ComThread.Release();
        return posi;
    }

    /**
     * TODO 无效 FindWindow:类名或者标题查找窗口.
     * 
     * @author zhouYong
     * @param className
     * @param title
     * @return 窗口句柄
     */
    protected static int findWindow(String title) {
        ComThread.InitSTA();
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        Variant titleV = new Variant(title);
        Variant classV = new Variant(null);
        Variant hwndR = new Variant();
        hwndR = Dispatch.call(com, "FindWindow", "MKSEmbedded", "MKSWindow#0");
        int hwnd = hwndR.getInt();
        // 释放资源
        // ComThread.Release();
        return hwnd;
    }

    /**
     * 
     * GetMousePointWindow:获取鼠标指向的句柄.
     * 
     * @author zhouYong
     * @return
     */
    protected static int GetMousePointWindow() {
        ComThread.InitSTA();
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        Variant hwndR = Dispatch.call(com, "GetMousePointWindow");
        int hwnd = hwndR.getInt();
        // 释放资源
        // ComThread.Release();
        return hwnd;
    }

    /**
     * 
     * GetPointWindow:获取给定坐标的窗口句柄
     * 
     * @author zhouYong
     * @param x
     * @param y
     * @return
     */
    protected static int GetPointWindow(int x, int y) {
        ComThread.InitSTA();
        int hadnw = 0;
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        Variant result = new Variant();

        result = Dispatch.call(com, "GetPointWindow", x, y);
        hadnw = result.getInt();
        // 释放资源
        // ComThread.Release();
        return hadnw;
    }

    protected static String GetWindowTitle(int hwnd) {
        ComThread.InitSTA();
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        Variant titleR = new Variant();
        titleR = Dispatch.call(com, "GetWindowTitle", hwnd);
        String title = titleR.getString();
        // 释放资源
        // ComThread.Release();
        return title;
    }

    /**
     * 
     * MoveWindow:移动指定窗口到指定位置.
     * 
     * @author zhouYong
     * @param hwnd
     * @param x
     * @param y
     * @return
     */
    protected static int MoveWindow(int hwnd, int x, int y) {
        ComThread.InitSTA();
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        Variant result = new Variant();

        result = Dispatch.call(com, "MoveWindow", hwnd, x, y);
        int state = result.getInt();
        // 释放资源
        // ComThread.Release();
        return state;
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
     * @param pic_name
     * @param sim
     *            相似度,取值范围0.1-1.0
     * @param dir
     *            :查找方向 0: 从左到右,从上到下 1: 从左到右,从下到上 2: 从右到左,从上到下 3: 从右到左, 从下到上
     * @return
     */
    protected static int[] FindPic(int lx, int ly, int rx, int ry, String pic_name, double sim, int dir) {
        ComThread.InitSTA();
        int[] posi = new int[2];
        dm = new ActiveXComponent("dm.dmsoft");//
        com = dm.getObject();
        int intX = 0, intY = 0;
        Variant vX = new Variant(intX);
        Variant vY = new Variant(intY);
        Dispatch.call(com, "FindPic", lx, ly, rx, ry, IMG_PATH + pic_name, "000000", sim, dir, vX, vY);
        posi[0] = vX.getInt();
        posi[1] = vY.getInt();
        if (posi[1] < 50) {
            vX = new Variant(intX);
            vY = new Variant(intY);
            System.out.println("二次找图");
            Dispatch.call(com, "FindPic", lx, ly, rx, ry, IMG_PATH + pic_name, "404040", sim, dir, vX, vY);
            posi[0] = vX.getInt();
            posi[1] = vY.getInt();
        }
        if (posi[1] < 50) {
            vX = new Variant(intX);
            vY = new Variant(intY);
            System.out.println("三次找图");
            Dispatch.call(com, "FindPic", lx, ly, rx, ry, IMG_PATH + pic_name, "4545", sim, dir, vX, vY);
            posi[0] = vX.getInt();
            posi[1] = vY.getInt();
        }
        // 释放资源
        // ComThread.Release();
        return posi;
    }
    
    static void start(int time) {
        // 检测到暂停命令 -->进入方法
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private static int getRandom(int min,int max){
        return    new Random().nextInt(max-min+1)+min; 
       }
}
