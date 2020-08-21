package com.zyong.ocr.gui.window.demo.widnowsdemo;

/**
 * @author ZYONG
 * @date 2020/7/30 22:48
 */
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
public class JFrameWindow extends JFrame
{
    public JFrameWindow()
    {
        setTitle("文字识别工具");    //设置显示窗口标题
        setSize(400,400);    //设置窗口显示尺寸
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //置窗口是否可以关闭
        JLabel jl=new JLabel("这是使用JFrame类创建的窗口");    //创建一个标签
        Container c=getContentPane();    //获取当前窗口的内容窗格
        c.add(jl);    //将标签组件添加到内容窗格上
        setVisible(true);    //设置窗口是否可见
    }

    // 启动窗口
//    public static void main(String[] agrs)
//    {
//        new JFrameWindow();    //创建一个实例化对象
//    }
}