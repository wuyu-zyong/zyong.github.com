package com.zyong.ocr.gui.window.demo.widnowsdemo;

/**
 * @author ZYONG
 * @date 2020/7/30 23:20
 */
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;
public class GridLayoutDemo
{
    public static void main1(String[] args)
    {
        JFrame frame=new JFrame("GridLayou布局计算器");
        JPanel panel=new JPanel();    //创建面板
        //指定面板的布局为GridLayout，4行4列，间隙为5
        panel.setLayout(new GridLayout(4,4,5,5));
        panel.add(new JButton("7"));    //添加按钮
        panel.add(new JButton("8"));
        panel.add(new JButton("9"));
        panel.add(new JButton("/"));
        panel.add(new JButton("4"));
        panel.add(new JButton("5"));
        panel.add(new JButton("6"));
        panel.add(new JButton("*"));
        panel.add(new JButton("1"));
        panel.add(new JButton("2"));
        panel.add(new JButton("3"));
        panel.add(new JButton("-"));
        panel.add(new JButton("0"));
//        panel.add(new JButton("."));
//        panel.add(new JButton("="));
//        panel.add(new JButton("+"));
        frame.add(panel);    //添加面板到容器
        frame.setBounds(300,200,200,150);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}