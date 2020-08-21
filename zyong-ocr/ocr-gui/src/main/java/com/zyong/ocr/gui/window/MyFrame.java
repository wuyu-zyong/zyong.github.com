package com.zyong.ocr.gui.window;

import com.zyong.ocr.gui.common.AppConstant;
import com.zyong.ocr.gui.event.ChooseFileListener;
import com.zyong.ocr.gui.event.ClearSingleOcrListener;
import com.zyong.ocr.gui.event.OpenCurrentOcrListener;
import com.zyong.ocr.gui.event.OpenHistoryFileListener;
import com.zyong.ocr.gui.util.ScreenHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;

/**
 * @author ZYONG
 * @date 2020/7/31 9:05
 */
public class MyFrame {

    public MyFrame() throws FileNotFoundException {
        JFrame frame=new JFrame("文字识别工具");
        GridBagLayout gbaglayout=new GridBagLayout();    //创建GridBagLayout布局管理器
        GridBagConstraints constraints=new GridBagConstraints();


        //该方法是为了设置如果组件所在的区域比组件本身要大时的显示情况
        //NONE：不调整组件大小。
        //HORIZONTAL：加宽组件，使它在水平方向上填满其显示区域，但是不改变高度。
        //VERTICAL：加高组件，使它在垂直方向上填满其显示区域，但是不改变宽度。
        //BOTH：使组件完全填满其显示区域。
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 0;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后L一个
        constraints.gridheight = 2;
        constraints.weightx = 1;//该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        constraints.weighty = 0;//该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间

        // 这里先把显示内容的组件创建出来
        JTextArea textArea = new JTextArea("");
        // 第一行截图按钮
        makeCutButton("点击截图", "D:\\idea-workspace\\my project\\zyong-ocrSingle\\ocrSingle-gui\\src\\main\\java\\com\\zyong\\ocrSingle\\gui\\window\\icon\\screenshot.png", frame, gbaglayout, constraints, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePrefix = AppConstant.APPP_PATH + File.separator + AppConstant.IMAGE_CACHE_PATH ;
                if (!new File(filePrefix).exists()){
                    new File(filePrefix).mkdirs();
                }
                String fileName = filePrefix + File.separator + AppConstant.DATA_FORMAT_FILE.format(new Date()) + "." + AppConstant.IMAGE_SUFFIX;

                // 调用截图插件
                ScreenHelper screenHelper = new ScreenHelper();
                screenHelper.setImgPath(fileName);
                screenHelper.setMainFrame(frame);
                screenHelper.setTextArea(textArea);
                try {
                    screenHelper.cut();
                } catch (AWTException e1) {
                    e1.printStackTrace();
                }
            }
        });
        // 第二行
        constraints.gridwidth = 1;// 该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
        constraints.gridheight = 1;
        constraints.weightx = 1;// 该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        constraints.weighty = 0;// 该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        ChooseFileListener chooseFileListener = new ChooseFileListener();
        chooseFileListener.setMainFrame(frame);
        chooseFileListener.setTextArea(textArea);
        makeButton("选择图片文件",frame,gbaglayout,constraints, chooseFileListener);

        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.weighty = 0;
        OpenCurrentOcrListener openCurrentOcrListener = new OpenCurrentOcrListener();
        openCurrentOcrListener.setMainFrame(frame);
        openCurrentOcrListener.setTextArea(textArea);
        makeButton("识别记录",frame,gbaglayout,constraints, openCurrentOcrListener);
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.weighty = 0;
        ClearSingleOcrListener clearSingleOcrListener = new ClearSingleOcrListener();
        clearSingleOcrListener.setMainFrame(frame);
        clearSingleOcrListener.setTextArea(textArea);
        makeButton("清  除",frame,gbaglayout,constraints,clearSingleOcrListener);
        constraints.gridwidth = 0;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.weighty = 0;
        OpenHistoryFileListener openHistoryFileListener = new OpenHistoryFileListener();
        openHistoryFileListener.setMainFrame(frame);
        openHistoryFileListener.setTextArea(textArea);
        makeButton("历史记录",frame,gbaglayout,constraints, openHistoryFileListener);

        constraints.gridwidth = 4;
        constraints.gridheight = 4;
        constraints.weightx = 1;
        constraints.weighty = 1;
        // --第三行文字显示区域

        textArea.append("");
        // 设置自动换行
        textArea.setLineWrap(true);
        // 设置背景色
        textArea.setBackground(Color.WHITE);
        // 设置组件的背景色
        textArea.setFont(new Font("Default", Font.PLAIN,16));
        // 设置滚动条
        JScrollPane jScrollPane = new JScrollPane(textArea);

        gbaglayout.setConstraints(jScrollPane, constraints);
        frame.add(jScrollPane);

        frame.setLayout(gbaglayout);    //使用GridBagLayout布局管理器
        frame.setBounds(1360,100,500,600);    //设置容器大小
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    //向JFrame中添加JButton按钮
    public static void makeButton(String title,JFrame frame,GridBagLayout gridBagLayout,GridBagConstraints constraints, ActionListener actionListener)
    {
        JButton button=new JButton(title);    //创建Button对象
        button.setPreferredSize(new Dimension(30, 40));// 设置按钮大小
        // 去掉虚线框
        button.setFocusPainted(false);
        gridBagLayout.setConstraints(button,constraints);
        frame.add(button);
        if (null != actionListener) button.addActionListener(actionListener);
    }
    public static void makeCutButton(String title, String iconPath, JFrame frame, GridBagLayout gridBagLayout, GridBagConstraints constraints, ActionListener actionListener)
    {

        JButton button = new JButton(title);    //创建Button对象
        Icon icon = new ImageIcon(iconPath);
        Icon icon2 = new ImageIcon(((ImageIcon) icon).getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
        button.setIcon(icon2);
        button.setPreferredSize(new Dimension(80, 50));// 设置按钮大小
        button.setContentAreaFilled(false);// 设置按钮透明
        button.setFont(new Font("粗体", Font.PLAIN, 35));// 按钮文本样式
        // 去掉虚线框
        button.setFocusPainted(false);
        button.setMargin(new Insets(0, 0, 0, 0));// 按钮内容与边框距离
        if (null != actionListener) button.addActionListener(actionListener);

        gridBagLayout.setConstraints(button,constraints);
        frame.add(button);
    }
}
