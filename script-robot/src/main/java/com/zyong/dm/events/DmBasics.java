/**
 * Project Name:mouse-robot
 * File Name:DmBasics.java
 * Package Name:com.zyong.dm.events
 * Date:2019年12月4日下午5:47:41
 * Copyright (c) 2019, LINEWELL All Rights Reserved.
 */

package com.zyong.dm.events;

import java.util.Objects;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.zyong.dm.DO.LogDO;
import com.zyong.dm.Dm;

/**
 * ClassName:DmBasics <br/>
 * Function: 大漠基础功能 <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019年12月4日 下午5:47:41 <br/>
 * @author zhouYong
 * @version
 * @since JDK 1.8
 * @see
 */
public class DmBasics extends Dm {
    /**
     * SetDisplayInput:定图色的获取方式，默认是显示器或者后台窗口(具体参考BindWindow).
     * @author zhouYong
     * @param mode 图色输入模式取值有以下几种
     * 1.     "screen" 这个是默认的模式，表示使用显示器或者后台窗口
     * 2.     "pic:file" 指定输入模式为指定的图片,如果使用了这个模式，则所有和图色相关的函数
     * 均视为对此图片进行处理，比如文字识别查找图片 颜色 等等一切图色函数.
     * 需要注意的是，设定以后，此图片就已经加入了缓冲，如果更改了源图片内容，那么需要释放此缓冲，重新设置
     * @return
     */
    public boolean setDisplayInput(String mode) {
        ComThread.InitSTA();
        dm = new ActiveXComponent("dm.dmsoft");
        com = dm.getObject();
        Variant result = new Variant();
        result = Dispatch.call(com, "SetDisplayInput", mode);
        // 0: 失败  1: 成功
        int resultInt = result.getInt();
        // 返回0，可以调用GetLastError来查看具体失败错误码,帮助分析问题
        // 释放资源
        com.safeRelease();
        dm.safeRelease();
        ComThread.Release();
        return Objects.equals(resultInt, 1);
    }

    /**
     * GetLastError:获取插件命令的最后错误.
     * @author  zhouYong
     * @return 返回值表示错误值。 0表示无错误
     */
    public LogDO getLastError() {
        ComThread.InitSTA();
        dm = new ActiveXComponent("dm.dmsoft");
        com = dm.getObject();
        Variant result = new Variant();
        result = Dispatch.call(com, "GetLastError");
        // 0: 标识无错误
        LogDO logDO = new LogDO();
        int state = result.getInt();
        if (state == 0) {
            logDO.setState(state);
            logDO.setInfo(null);
        }
        if (state == -1) {
            logDO.setState(state);
            logDO.setInfo(" 表示你使用了绑定里的收费功能，但是没注册，无法使用.");
        }
        if (state == -2) {
            logDO.setState(state);
            logDO.setInfo(
                    " 使用模式0 2 4 6时出现，因为目标窗口有保护，或者目标窗口没有以管理员权限打开. 常见于win7以上系统.或者有安全软件拦截插件.解决办法关闭所有安全软件，并且关闭系统UAC,然后再重新尝试. 如果还不行就可以肯定是目标窗口有特殊保护. ");
        }
        if (state == -3) {
            logDO.setState(state);
            logDO.setInfo(" 使用模式0 2 4 6时出现，可能目标窗口有保护，也可能是异常错误.");
        }
        if (state == -4) {
            logDO.setState(state);
            logDO.setInfo(" 使用模式1 3 5 7 101 103时出现，这是异常错误.");
        }
        if (state == -5) {
            logDO.setState(state);
            logDO.setInfo(" 使用模式1 3 5 7 101 103时出现, 这个错误的解决办法就是关闭目标窗口，重新打开再绑定即可. 也可能是运行脚本的进程没有管理员权限. ");
        }
        if (state == -6 || state == -7 || state == -9) {
            logDO.setState(state);
            logDO.setInfo(" 使用模式1 3 5 7 101 103时出现,异常错误. 还有可能是安全软件的问题，比如360等。尝试卸载360.");
        }
        if (state == -8 || state == -10) {
            logDO.setState(state);
            logDO.setInfo(" 使用模式1 3 5 7 101 103时出现, 目标进程可能有保护,也可能是插件版本过老，试试新的或许可以解决.");
        }
        if (state == -11) {
            logDO.setState(state);
            logDO.setInfo(" 使用模式1 3 5 7 101 103时出现, 目标进程有保护. 告诉我解决。");
        }
        if (state == -12) {
            logDO.setState(state);
            logDO.setInfo(" 使用模式1 3 5 7 101 103时出现, 目标进程有保护. 告诉我解决。");
        }
        if (state == -13) {
            logDO.setState(state);
            logDO.setInfo(" 使用模式1 3 5 7 101 103时出现, 目标进程有保护. 或者是因为上次的绑定没有解绑导致。 尝试在绑定前调用ForceUnBindWindow.");
        }
        if (state == -14) {
            logDO.setState(state);
            logDO.setInfo(" 使用模式0 1 4 5时出现, 有可能目标机器兼容性不太好. 可以尝试其他模式. 比如2 3 6 7");
        }
        if (state == -16) {
            logDO.setState(state);
            logDO.setInfo(
                    " 可能使用了绑定模式 0 1 2 3 和 101，然后可能指定了一个子窗口.导致不支持.可以换模式4 5 6 7或者103来尝试. 另外也可以考虑使用父窗口或者顶级窗口来避免这个错误。还有可能是目标窗口没有正常解绑 然后再次绑定的时候.");
        }
        if (state == -17) {
            logDO.setState(state);
            logDO.setInfo(" 模式1 3 5 7 101 103时出现. 这个是异常错误. 告诉我解决.");
        }
        if (state == -18) {
            logDO.setState(state);
            logDO.setInfo(" 句柄无效.");
        }
        if (state == -19) {
            logDO.setState(state);
            logDO.setInfo(" 使用模式0 1 2 3 101时出现,说明你的系统不支持这几个模式. 可以尝试其他模式.");
        }

        // 释放资源
        com.safeRelease();
        dm.safeRelease();
        ComThread.Release();
        return logDO;
    }
}

