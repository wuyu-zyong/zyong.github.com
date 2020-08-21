package com.zyong.ocr.gui.filter;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * @ClassName ImgFileFilter
 * @Description 文件筛选
 * @Author zhouyong
 * @Date 2020/8/4 17:13
 * @Version V1.0
 */
public class ImgFileFilter extends FileFilter {
    private static final String IMG_FILE_FORMAT = "*.jpg;*.png;*.jpeg;";

    /**
     * Whether the given file is accepted by this filter.
     *
     * @param f
     */
    @Override
    public boolean accept(File f) {
        boolean isChoose = false;
        String[] fs = f.getName().split("\\.");
        return IMG_FILE_FORMAT.contains(fs[fs.length - 1]) || f.isDirectory();
    }

    /**
     * The description of this filter. For example: "JPG and GIF Images"
     *
     * @see FileView#getName
     */
    @Override
    public String getDescription() {
        return IMG_FILE_FORMAT;
    }
}
