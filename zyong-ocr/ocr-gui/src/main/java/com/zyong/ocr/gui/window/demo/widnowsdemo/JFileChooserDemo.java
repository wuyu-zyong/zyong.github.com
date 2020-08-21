package com.zyong.ocr.gui.window.demo.widnowsdemo;

/**
 * @ClassName JFileChooserDemo
 * @Description TODO
 * @Author zhouyong
 * @Date 2020/8/4 16:35
 * @Version V1.0
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class JFileChooserDemo
{
    private JLabel label=new JLabel("所选文件路径：");
    private JTextField jtf=new JTextField(25);
    private JButton button=new JButton("浏览");
    public JFileChooserDemo()
    {
        JFrame jf=new JFrame("文件选择器");
        JPanel panel=new JPanel();
        panel.add(label);
        panel.add(jtf);
        panel.add(button);
        jf.add(panel);
        jf.pack();    //自动调整大小
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button.addActionListener(new MyActionListener());    //监听按钮事件
    }
    //Action事件处理
    class MyActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            JFileChooser fc=new JFileChooser("C:\\");
            // 多选
            fc.setMultiSelectionEnabled(true);
            int val=fc.showOpenDialog(null);    //文件打开对话框
            if(val==fc.APPROVE_OPTION)
            {
                //正常选择文件
                jtf.setText(fc.getSelectedFile().toString());
            }
            else
            {
                //未正常选择文件，如选择取消按钮
                jtf.setText("未选择文件");
            }
        }
    }
    public static void main(String[] args)
    {
        new JFileChooserDemo();
    }
}