package com.zyong.ocr.gui.window;

import com.zyong.ocr.gui.util.ScreenHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @author ZYONG
 * @date 2020/8/1 22:33
 */
public class CutScreenFrame extends JWindow {
    private ScreenHelper parent;

    public CutScreenFrame(ScreenHelper parent, int x, int y) {
        this.parent = parent;
        this.init();
        this.setLocation(x, y);
        this.pack();
        this.setVisible(true);
    }
    private void init() {

        this.setLayout(new BorderLayout());
        JToolBar toolBar = new JToolBar("Java 截图");

        //保存按钮
        //JButton saveButton = new JButton(new ImageIcon("images/save.gif"));
        JButton saveButton = new JButton(" 确定 ");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    parent.saveImage();
                    // 设置截图结果
                    parent.setSuccess(true);
                    // 关闭遮挡窗口
                    parent.closeConfirm();
                    // 关闭操作窗口
                    setVisible(false);
                    dispose();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        toolBar.add(saveButton);

        //关闭按钮
        JButton closeButton = new JButton(" 取消 ");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 设置截图结果
                parent.setSuccess(false);
                // 关闭遮挡窗口
                parent.closeCancel();
                // 关闭操作窗口
                setVisible(false);
                dispose();
            }
        });
        toolBar.add(closeButton);
        this.add(toolBar, BorderLayout.NORTH);
    }

}
