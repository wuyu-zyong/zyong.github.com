package com.zyong.ocr.gui.event;

import com.zyong.ocr.gui.common.AppConstant;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * @ClassName OpenHistoryFileListener
 * @Description TODO
 * @Author zhouyong
 * @Date 2020/8/4 18:07
 * @Version V1.0
 */
public class OpenHistoryFileListener  implements ActionListener {
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

        // 打开目录
        File file= new File(AppConstant.CURRENT_SAVE_FILE);
        try {
            java.awt.Desktop.getDesktop().open(file.getParentFile());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
