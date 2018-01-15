package com.dream.util.extend;


import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import java.io.*;

/**
 * Ftp工具类 需要导入commons-net-3.4.jar这个包
 */
public class FtpUtil {

    private static Logger logger = Logger.getLogger(com.dream.util.extend.FtpUtil.class);

    private static FTPClient ftpClient;
    public final static String path = "/zdzjpg/";//aaa路径

    /**
     * 获取ftp连接
     *
     * @return
     * @throws Exception
     */
    public static boolean connectFtp() throws Exception {
        ftpClient = new FTPClient();
        boolean flag = false;
        int reply;

        try {


            ftpClient.connect("218.28.166.165", 19999);//ip地址 ,//端口号
            //ftp登陆
            ftpClient.login("sdzdz", "hlxx@2017");//用户名,//密码
            //设置文件传输类型
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            //检查延时
            reply = ftpClient.getReplyCode();
            //如果延时不在200到300之间，就关闭连接
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return flag;
            }
            ftpClient.changeWorkingDirectory(path);
            ftpClient.enterLocalPassiveMode();
            flag = true;
            return flag;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 关闭ftpClient连接
     */
    public static void closeFtp() {
        if (ftpClient != null && ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ftp上传文件
     *
     * @param f
     * @throws Exception
     */
    public static void upload(File f) throws Exception {
        if (f.isDirectory()) {
            ftpClient.makeDirectory(f.getName());
            ftpClient.changeWorkingDirectory(f.getName());
            //返回目录名和文件名组成的字符串数组
            String[] files = f.list();
            for (String fstr : files) {
                File file1 = new File(f.getPath() + "/" + fstr);
                if (file1.isDirectory()) {
                    upload(file1);
                    ftpClient.changeToParentDirectory();
                } else {
                    File file2 = new File(f.getPath() + "/" + fstr);
                    FileInputStream input = new FileInputStream(file2);
                    ftpClient.storeFile(file2.getName(), input);
                    input.close();
                }
            }
        } else {
            File file2 = new File(f.getPath());
            FileInputStream input = new FileInputStream(file2);
            ftpClient.storeFile(file2.getName(), input);
            input.close();
        }
    }

    /**
     * 下载链接配置
     *
     * @param localBaseDir  本地目录
     * @param remoteBaseDir 远程目录
     * @throws Exception
     */
    public static boolean startDown(String localBaseDir, String remoteBaseDir) throws Exception {
        if (FtpUtil.connectFtp()) {
            try {
                FTPFile[] files = null;
                boolean changedir = ftpClient.changeWorkingDirectory(remoteBaseDir);
                if (changedir) {
                    ftpClient.setControlEncoding("GBK");
                    files = ftpClient.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        try {
//                                if(filename.equals(files[i].getName())){
                            downloadFile(files[i], localBaseDir, remoteBaseDir);
//                                    return true;
//                                }
                        } catch (Exception e) {
                            logger.error(e);
                            logger.error("<" + files[i].getName() + ">下载失败");
                        }
                    }
                }
            } catch (Exception e) {
                logger.error(e);
                logger.error("下载过程中出现异常");
            }
        } else {
            logger.error("链接失败！");
        }
        return false;
    }


    /**
     * 下载FTP文件
     * 当你需要下载FTP文件的时候，调用此方法
     * 根据<b>获取的文件名，本地地址，远程地址</b>进行下载
     *
     * @param ftpFile
     * @param relativeLocalPath
     * @param relativeRemotePath
     */
    private static void downloadFile(FTPFile ftpFile, String relativeLocalPath, String relativeRemotePath) {
        if (ftpFile.isFile()) {
            if (ftpFile.getName().indexOf("?") == -1) {
                OutputStream outputStream = null;
                try {
                    File entryDir = new File(relativeLocalPath);
                    //如果文件夹路径不存在，则创建文件夹
                    if (!entryDir.exists() || !entryDir.isDirectory()) {
                        entryDir.mkdirs();
                    }
                    File locaFile = new File(relativeLocalPath + ftpFile.getName());
                    //判断文件是否存在，存在则返回
                    if (locaFile.exists()) {
                        return;
                    } else {
                        outputStream = new FileOutputStream(relativeLocalPath + ftpFile.getName());
                        ftpClient.retrieveFile(ftpFile.getName(), outputStream);
                        outputStream.flush();
                        outputStream.close();
                    }
                } catch (Exception e) {
                    logger.error(e);
                } finally {
                    try {
                        if (outputStream != null) {
                            outputStream.close();
                        }
                    } catch (IOException e) {
                        logger.error("输出文件流异常");
                    }
                }
            }
        } else {
            String newlocalRelatePath = relativeLocalPath + ftpFile.getName();
            String newRemote = new String(relativeRemotePath + ftpFile.getName().toString());
            File fl = new File(newlocalRelatePath);
            if (!fl.exists()) {
                fl.mkdirs();
            }
            try {
                newlocalRelatePath = newlocalRelatePath + '/';
                newRemote = newRemote + "/";
                String currentWorkDir = ftpFile.getName().toString();
                boolean changedir = ftpClient.changeWorkingDirectory(currentWorkDir);
                if (changedir) {
                    FTPFile[] files = null;
                    files = ftpClient.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        downloadFile(files[i], newlocalRelatePath, newRemote);
                    }
                }
                if (changedir) {
                    ftpClient.changeToParentDirectory();
                }
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }

    /**
     * 先配置下载链接，在下载文件。 调用了startDown和downloadFile写的方法
     *
     * @param savepath
     * @return
     * @throws Exception
     */
    public static boolean downfile(String savepath) throws Exception {
        try {

            //File file = new File("F:/test/com/test/Testng.java");
            //FtpUtil.upload(file);//把文件上传在ftp上
            return FtpUtil.startDown(savepath, path);//下载ftp文件测试

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

//        public static void main(String[] args) {
//            try {
//               FtpUtil.downfile("E:/");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

}
