package com.zyong.ocr.gui.event;

import com.zyong.ocr.gui.common.AppConstant;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * @ClassName OpenCurrentOcrListener
 * @Description TODO
 * @Author zhouyong
 * @Date 2020/8/4 18:02
 * @Version V1.0
 */
public class OpenCurrentOcrListener implements ActionListener {
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
        // 加载此次结果
        try {
            File file = new File(AppConstant.CURRENT_SAVE_FILE);
            if (!file.exists()) return;;
            String content = FileUtils.readFileToString(file, "UTF-8");
            this.textArea.setText(content);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}
