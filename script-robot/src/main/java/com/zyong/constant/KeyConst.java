/**
 * Project Name:game-preset File Name:KeyConst.java Package Name:com.zyong.game.common Date:2018年12月4日下午1:53:11
 * Copyright (c) 2018, ZYONG All Rights Reserved.
 *
 */

package com.zyong.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:KeyConst <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018年12月4日 下午1:53:11 <br/>
 * 
 * @authorzhouYong
 * @version
 * @sinceJDK 1.8
 * @see
 */
public class KeyConst {

    public static final byte KEY_1 = 49;

    public static final byte KEY_2 = 50;

    public static final byte KEY_3 = 51;

    public static final byte KEY_4 = 52;

    public static final byte KEY_5 = 53;

    public static final byte KEY_6 = 54;

    public static final byte KEY_7 = 55;

    public static final byte KEY_8 = 56;

    public static final byte KEY_9 = 57;

    public static final byte KEY_0 = 48;

    public static final byte KEY_BACK = 8;
    /**
     * 反斜杠
     */
    public static final byte KEY_BACKSLASH = (byte)220;
    // 190
    public static final byte KEY_POINT = (byte)190;

    public static final byte KEY_A = 65;

    public static final byte KEY_B = 66;

    public static final byte KEY_C = 67;

    public static final byte KEY_D = 68;

    public static final byte KEY_E = 69;

    public static final byte KEY_F = 70;

    public static final byte KEY_G = 71;

    public static final byte KEY_H = 72;

    public static final byte KEY_I = 73;

    public static final byte KEY_J = 74;

    public static final byte KEY_K = 75;

    public static final byte KEY_L = 76;

    public static final byte KEY_M = 77;

    public static final byte KEY_N = 78;

    public static final byte KEY_O = 79;

    public static final byte KEY_P = 80;

    public static final byte KEY_Q = 81;

    public static final byte KEY_R = 82;

    public static final byte KEY_S = 83;

    public static final byte KEY_T = 84;

    public static final byte KEY_U = 85;

    public static final byte KEY_V = 86;

    public static final byte KEY_W = 87;

    public static final byte KEY_X = 88;

    public static final byte KEY_Y = 89;

    public static final byte KEY_Z = 90;

    public static final byte KEY_CTRL = 17;

    public static final byte KEY_ALT = 18;

    public static final byte KEY_SHIFT = 16;

    public static final byte KEY_WIN = 91;

    public static final byte KEY_SPACEBAR = 32;

    public static final byte KEY_CAPS = 20;

    public static final byte KEY_TAB = 9;

    public static final byte KEY_ESC = 27;

    public static final byte KEY_ENTER = 13;

    public static final byte KEY_UP_ARROW = 38;

    public static final byte KEY_DOWN_ARROW = 40;

    public static final byte KEY_LEFT_ARROW = 37;

    public static final byte KEY_RIGHT_ARROW = 39;

    public static final byte KEY_OPTION = 93;

    public static final byte KEY_PRINT = 44;

    public static final byte KEY_DELETE = 46;

    public static final byte KEY_HOME = 36;

    public static final byte KEY_END = 35;

    public static final byte KEY_PGUP = 33;

    public static final byte KEY_PGDN = 34;

    public static final byte KEY_F1 = 112;

    public static final byte KEY_F2 = 113;

    public static final byte KEY_F3 = 114;

    public static final byte KEY_F4 = 115;

    public static final byte KEY_F5 = 116;

    public static final byte KEY_F6 = 117;

    public static final byte KEY_F7 = 118;

    public static final byte KEY_F8 = 119;

    public static final byte KEY_F9 = 120;

    public static final byte KEY_F10 = 121;

    public static final byte KEY_F11 = 122;

    public static final byte KEY_F12 = 123;
    /**
     * 分号
     */
    public static final byte KEY_FENHAO = (byte)186;

    private static byte getKey(byte key) {
        Map<Byte, Byte> map = new HashMap<>();
        map.put((byte)'1', (byte)49);
        map.put((byte)'2', (byte)50);
        map.put((byte)'3', (byte)51);
        map.put((byte)'4', (byte)52);
        map.put((byte)'5', (byte)53);
        map.put((byte)'6', (byte)54);
        map.put((byte)'7', (byte)55);
        map.put((byte)'8', (byte)56);
        map.put((byte)'9', (byte)57);
        map.put((byte)'0', (byte)48);
        map.put((byte)'a', (byte)65);
        map.put((byte)'b', (byte)66);
        map.put((byte)'c', (byte)67);
        map.put((byte)'d', (byte)68);
        map.put((byte)'e', (byte)69);
        map.put((byte)'f', (byte)70);
        map.put((byte)'g', (byte)71);
        map.put((byte)'h', (byte)72);
        map.put((byte)'i', (byte)73);
        map.put((byte)'j', (byte)74);
        map.put((byte)'k', (byte)75);
        map.put((byte)'l', (byte)76);
        map.put((byte)'m', (byte)77);
        map.put((byte)'n', (byte)78);
        map.put((byte)'o', (byte)79);
        map.put((byte)'p', (byte)80);
        map.put((byte)'q', (byte)81);
        map.put((byte)'r', (byte)82);
        map.put((byte)'s', (byte)83);
        map.put((byte)'t', (byte)84);
        map.put((byte)'u', (byte)85);
        map.put((byte)'v', (byte)86);
        map.put((byte)'w', (byte)87);
        map.put((byte)'x', (byte)88);
        map.put((byte)'y', (byte)89);
        map.put((byte)'z', (byte)90);
        // 空格
        map.put((byte)' ', (byte)32);
        // \ 反斜杠
        map.put((byte)'-', (byte)220);
        // /斜杠
        map.put((byte)'~', (byte)191);
        // 点
        map.put((byte)'.', (byte)190);
        return map.get(key);
    }
}
