 package com.zyong.robot;

 import com.zyong.constant.KeyConst;
 import com.zyong.dm.DO.ElementPositionDo;
 import com.zyong.dm.DO.WindowDo;
 import com.zyong.dm.events.DmBasics;
 import com.zyong.dm.events.DmKeyboard;
 import com.zyong.dm.events.DmWindows;
 import com.zyong.dm.events.Dmlib;
 import com.zyong.utils.Delay;

 public class TestMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub0
        DmWindows dm = new DmWindows();
        WindowDo windowDo = dm.findWindow("Chrome_WidgetWin_1", null);
        ElementPositionDo elementPositionDo = dm.findPic(windowDo.getPosition()[0],windowDo.getPosition()[1],windowDo.getPosition()[2],windowDo.getPosition()[3],
                "D:\\eclipse-workspace\\myProject\\crawler\\crawler\\zyong-crawler\\rpc-crawler-web-client\\src\\main\\resources\\static\\publish\\penguinPlatform\\tagIco.bmp",1.0,0);
        System.out.println(elementPositionDo);
    }

}
