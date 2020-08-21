package com.zyong.ocr.gui.window.demo.widnowsdemo;

/**
 * @author ZYONG
 * @date 2020/7/30 22:58
 */
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
public class JPanelDemo
{
    public static void main1(String[] agrs)
    {
        JFrame jf=new JFrame("Java第二个GUI程序");    //创建一个JFrame对象
        jf.setBounds(300, 100, 400, 200);    //设置窗口大小和位置
        JPanel jp=new JPanel();    //创建一个JPanel对象
        JLabel jl=new JLabel("这是放在JPanel上的标签");    //创建一个标签
        jp.setBackground(Color.blue);    //设置背景色
        jp.add(jl);    //将标签添加到面板
        jf.add(jp);    //将面板添加到窗口
        jf.setVisible(true);    //设置窗口可见
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}