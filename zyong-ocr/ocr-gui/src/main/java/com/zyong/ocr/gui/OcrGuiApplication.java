package com.zyong.ocr.gui;

import com.zyong.ocr.gui.window.MyFrame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.io.FileNotFoundException;

/**
 * @author ZYONG
 * @date 2020/7/30 22:34
 */
@SpringBootApplication
public class OcrGuiApplication {
    public static void main(String[] args) {

        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(OcrGuiApplication.class);
        springApplicationBuilder.headless(false).run(args);
        // 使用下面的方法默认拦截所有GUI
        // SpringApplication.run(OcrGuiApplication.class, args);
        // TODO 启动窗口
        try {
            new MyFrame();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
