package com.zyong.ocr.gui.event;

import com.zyong.ocr.gui.common.AppConstant;
import com.zyong.ocr.gui.filter.ImgFileFilter;
import com.zyong.ocr.gui.service.OcrService;
import com.zyong.ocr.gui.util.SpringContextUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @ClassName ChooseFileListener
 * @Description 文件选择监听器
 * @Author zhouyong
 * @Date 2020/8/4 16:42
 * @Version V1.0
 */
public class ChooseFileListener implements ActionListener {
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
        JFileChooser chooser = new JFileChooser(AppConstant.APPP_PATH);
        // 多选
        chooser.setMultiSelectionEnabled(true);
        // 选择格式
        chooser.addChoosableFileFilter(new ImgFileFilter());
        // 文件打开对话框
        int status = chooser.showOpenDialog(null);

        // 选择文件
        if (status == JFileChooser.APPROVE_OPTION){
            // 获取文件列表，并且打开识别
            File[] files = chooser.getSelectedFiles();
            SpringContextUtils.getBean(OcrService.class).ocrBatch(files,this.mainFrame, this.textArea);
            System.out.println("选择了以下文件：");
        } else {
            System.out.println("未选择文件！");
        }
    }
}
