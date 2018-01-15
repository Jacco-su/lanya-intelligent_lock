package com.dream.util.extend;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileUtil {
    public static List<Imaging> readFile(String path) {
        List<Imaging> fileList = new ArrayList<Imaging>();
        File file = new File(path);
        if (file.isDirectory()) {
            File[] tempList = file.listFiles();
            if (tempList != null && tempList.length > 0) {
                for (int i = 0; i < tempList.length; i++) {
                    Imaging mf = new Imaging();
                    if (tempList[i].isFile()) {
                        mf.setFname(tempList[i].getName());
                        mf.setFpath(tempList[i].toString());
                        /**
                         * 获取文件创建时间
                         */
                        // ================================================
                        DateFormat format = new SimpleDateFormat(
                                "yyyy-MM-dd HH:mm:ss");
                        // 毫秒数
                        long modifiedTime = tempList[i].lastModified();
                        // 通过毫秒数构造日期 即可将毫秒数转换为日期
                        Date d = new Date(modifiedTime);
                        mf.setCreateTime(format.format(d));
                        // ===============================================
                        /**
                         * 获取文件大小
                         */
                        // ===============================================
                        try {
                            mf.setFszie(getFileSizes(tempList[i]));
                            mf.setFormatSize(formetFileSize(mf.getFszie()));
                        } catch (Exception e) {
                            System.out.println("计算文件大小出错");
                            e.printStackTrace();
                        }
                        // ===============================================
                        fileList.add(mf);
                        System.out.println("文件绝对路径：" + tempList[i]);

                    }
                    if (tempList[i].isDirectory()) {
                        System.out.println("文件夹：" + tempList[i]);
                    }
                }
            }
        }
        return fileList;
    }

    public static long getFileSizes(File f) throws Exception {// 取得文件大小
        long s = 0;
        if (f.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(f);
            s = fis.available();
        } else {
            //f.createNewFile();
            System.out.println("文件不存在");
        }
        return s;
    }

    // 递归
    public long getFileSize(File f) throws Exception// 取得文件夹大小
    {
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getFileSize(flist[i]);
            } else {
                size = size + flist[i].length();
            }
        }
        return size;
    }

    public static String formetFileSize(long fileS) {// 转换文件大小
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }
}
