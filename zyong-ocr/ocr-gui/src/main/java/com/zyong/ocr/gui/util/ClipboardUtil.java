package com.zyong.ocr.gui.util;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

/**
 * 剪切板
 *
 * @author ZYONG
 * @date 2020/8/3 17:18
 */
public class ClipboardUtil {

    /**
     * 获取剪切板中的内容
     *
     * @param clip
     * @return
     * @throws Exception
     */
    public static String getClipboardText(Clipboard clip) throws Exception {
        // 获取剪切板中的内容
        Transferable clipT = clip.getContents(null);
        if (clipT != null) {
            // 检查内容是否是文本类型
            if (clipT.isDataFlavorSupported(DataFlavor.stringFlavor))
                return (String) clipT.getTransferData(DataFlavor.stringFlavor);
        }
        return null;
    }

    /**
     * 往剪切板写文本数据
     *
     * @param writeMe
     */
    public static void setClipboardText( String writeMe) {
        Transferable tText = new StringSelection(writeMe);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(tText, null);
    }

    /**
     * 从剪切板读取图像
     *
     * @return
     * @throws Exception
     */
    public static Image getImageFromClipboard() throws Exception {
        Clipboard sysc = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable cc = sysc.getContents(null);
        if (cc == null)
            return null;
        else if (cc.isDataFlavorSupported(DataFlavor.imageFlavor))
            return (Image) cc.getTransferData(DataFlavor.imageFlavor);
        return null;
    }

    /**
     * 写图像到剪切板
     *
     * @param image
     */
    public static void setClipboardImage2(final Image image) {
        Transferable trans = new Transferable() {
            public DataFlavor[] getTransferDataFlavors() {
                return new DataFlavor[]{DataFlavor.imageFlavor};
            }

            public boolean isDataFlavorSupported(DataFlavor flavor) {
                return DataFlavor.imageFlavor.equals(flavor);
            }

            public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
                if (isDataFlavorSupported(flavor))
                    return image;
                throw new UnsupportedFlavorException(flavor);
            }
        };
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(trans, null);

    }
}