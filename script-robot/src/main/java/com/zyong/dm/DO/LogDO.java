/**
 * Project Name:mouse-robot
 * File Name:LogDO.java
 * Package Name:com.zyong.dm.DO
 * Date:2019年12月4日下午5:25:46
 * Copyright (c) 2019, LINEWELL All Rights Reserved. 
 *
*/

package com.zyong.dm.DO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * ClassName:LogDO <br/>
 * Function: 大漠消息类日志类 <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2019年12月4日 下午5:25:46 <br/>
 * 
 * @author zhouYong
 * @version
 * @since JDK 1.8
 * @see
 */
@Getter
@Setter
@ToString
public class LogDO {
    private int state;
    private String info;
}
