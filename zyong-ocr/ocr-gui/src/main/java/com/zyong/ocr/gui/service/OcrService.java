package com.zyong.ocr.gui.service;

import com.zyong.ocr.core.factory.Ocr;
import com.zyong.ocr.core.factory.OcrFactory;
import com.zyong.ocr.gui.common.AppConstant;
import com.zyong.ocr.gui.util.ClipboardUtil;
import com.zyong.ocr.gui.util.ScreenHelper;
import com.zyong.ocr.gui.window.MyFrame;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * 文字识别服务类
 *
 * @author ZYONG
 * @date 2020/8/1 15:38
 */
@Service
public class OcrService {
    @Value("${ocr.factory}")
    private String ocrableClass;

    // 获取ocr实例
    private Ocr ocr;

    @PostConstruct
    private void init() {
        ocr = OcrFactory.getInstance(ocrableClass);
    }

    /**
     * 单个文件识别
     *
     * @param imgPath
     * @param mainFrame
     * @param textArea
     * @return
     * @throws
     * @author zhouyong
     * @date 2020/8/4 17:50
     */
    public void ocrSingle(String imgPath, JFrame mainFrame, JTextArea textArea) {
        // 提示识别中
        textArea.append(AppConstant.WAIT_HINT);
        String txt = ocr.charRecognition(imgPath, "\n");
        if (StringUtils.isEmpty(txt)){
            JOptionPane.showMessageDialog(mainFrame,"无法识别");
            return;
        }
        // 写到窗口,写之前先清理窗口原来数据
        textArea.setText("");
        showOcrResult(textArea, txt);
    }

    /**
     * 多个文件识别
     *
     * @param files
     * @param mainFrame
     * @param textArea
     * @return
     * @throws
     * @author zhouyong
     * @date 2020/8/4 17:50
     */
    public void ocrBatch(File[] files, JFrame mainFrame, JTextArea textArea) {
        // 提示识别中
        textArea.append(AppConstant.WAIT_HINT);
        List<String> strings = ocr.charRecognition(files, "\n");

        if (null == strings || strings.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame,"无法识别!");
            return;
        }
        // 写到窗口,写之前先清理窗口原来数据
        textArea.setText("");
        showOcrResult(textArea, String.join("\n", strings));
    }

    private void showOcrResult(JTextArea textArea, String txt) {
        textArea.append("\n" + txt);
        // 写到剪切板
        ClipboardUtil.setClipboardText(txt);

        // 追加写到文件中
        if (!StringUtils.isEmpty(txt)) {
            File file = new File(AppConstant.CURRENT_SAVE_FILE);
            if (!file.exists()) {
                new File(file.getParent()).mkdirs();
            }
            try {
                FileUtils.writeStringToFile(file, txt, "UTF-8", true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
