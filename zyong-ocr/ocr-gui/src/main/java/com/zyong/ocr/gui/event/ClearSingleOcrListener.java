package com.zyong.ocr.gui.event;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ClassName ClearSingleOcrListener
 * @Description TODO
 * @Author zhouyong
 * @Date 2020/8/4 18:06
 * @Version V1.0
 */
public class ClearSingleOcrListener implements ActionListener {
    private JFrame mainFrame;
    private JTextArea textArea;

    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        textArea.setText("");
    }
}
