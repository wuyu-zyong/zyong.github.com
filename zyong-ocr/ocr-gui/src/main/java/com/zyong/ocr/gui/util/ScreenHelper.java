package com.zyong.ocr.gui.util;

import com.zyong.ocr.gui.common.AppConstant;
import com.zyong.ocr.gui.service.OcrService;
import com.zyong.ocr.gui.window.CutScreenFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;

/**
 * @author ZYONG
 * @date 2020/8/1 22:12
 */
public class ScreenHelper extends JWindow {
    private boolean isSuccess = false;
    Dimension d = null;
    BufferedImage tempImage = null;
    BufferedImage image = null;
    CutScreenFrame tools = null;
    private BufferedImage saveImage = null;
    private String imgPath;
    private JFrame mainFrame;
    private JTextArea textArea;

    public void cut() throws AWTException {
        this.imgPath = imgPath;
        setVisible(true);
        //获取屏幕尺寸
        d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(0, 0, d.width, d.height);

        final int[] orgx = new int[1];
        final int[] orgy = new int[1];
        final int[] endx = new int[1];
        final int[] endy = new int[1];

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //鼠标松开时记录结束点坐标，并隐藏操作窗口
                orgx[0] = e.getX();
                orgy[0] = e.getY();

                // 设置窗口隐藏
                if (tools != null) {
                    tools.setVisible(false);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //鼠标松开时，显示操作窗口
                if (tools == null) {
                    tools = new CutScreenFrame(ScreenHelper.this, e.getX(), e
                            .getY());
                } else {
                    tools.setLocation(e.getX(), e.getY());
                }
                tools.setVisible(true);
                tools.toFront();
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //鼠标拖动时，记录坐标并重绘窗口
                endx[0] = e.getX();
                endy[0] = e.getY();
                //临时图像，用于缓冲屏幕区域放置屏幕闪烁
                Image tempImage2 = createImage(getWidth(), getHeight());
                Graphics g = tempImage2.getGraphics();

                g.drawImage(tempImage, 0, 0, null);
                int x = Math.min(orgx[0], endx[0]);
                int y = Math.min(orgy[0], endy[0]);
                int width = Math.abs(endx[0] - orgx[0]) + 1;
                int height = Math.abs(endy[0] - orgy[0]) + 1;

                // 加上1防止width或height0
                g.setColor(Color.BLUE);
                g.drawRect(x - 1, y - 1, width + 1, height + 1);
                //减1加1都了防止图片矩形框覆盖掉
                saveImage = image.getSubimage(x, y, width, height);
                g.drawImage(saveImage, x, y, null);
                getGraphics().drawImage(tempImage2, 0, 0, ScreenHelper.this);

            }
        });
    }

    @Override
    public void paint(Graphics g) {

        //截取屏幕
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        image = robot.createScreenCapture(new Rectangle(0, 0, d.width, d.height));
        RescaleOp ro = new RescaleOp(0.8f, 0, null);
        tempImage = ro.filter(image, null);
        g.drawImage(tempImage, 0, 0, this);
    }

    //保存图像到文件
    public void saveImage() throws IOException {
        File defaultFile = new File(this.imgPath);
        ImageIO.write(saveImage, AppConstant.IMAGE_SUFFIX, defaultFile);
    }

    public void closeConfirm() {
        setVisible(false);
        dispose();
        // 触发文字识别
        SpringContextUtils.getBean(OcrService.class).ocrSingle(this.imgPath, this.mainFrame, this.textArea);
    }
    public void closeCancel() {
        setVisible(false);
        dispose();
    }


    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }


    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }
}
