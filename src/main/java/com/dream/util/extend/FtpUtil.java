package com.dream.util.extend;


import com.dream.socket.utils.DateUtil;
import com.dream.util.Const;
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
    //public final static String path = "/410100000502";//aaa路径

    private   static   int file_count=0;

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
            ftpClient.connect(Const.FTP_IP, Const.FTP_PORT);//ip地址 ,//端口号
            //ftp登陆
            ftpClient.login(Const.FTP_USER_NAME, Const.FTP_PASSWORD);//用户名,//密码
            //设置文件传输类型
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            //检查延时
            reply = ftpClient.getReplyCode();
            //如果延时不在200到300之间，就关闭连接
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return flag;
            }
            ftpClient.changeWorkingDirectory("/");
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
    public static boolean startDown(String localBaseDir, String remoteBaseDir,String openTime) throws Exception {
        if (FtpUtil.connectFtp()) {
            try {
                FTPFile[] files = null;
                boolean changedir = ftpClient.changeWorkingDirectory(remoteBaseDir);
                if (changedir) {
                    ftpClient.setControlEncoding("GBK");
                    files = ftpClient.listFiles();
                    for (FTPFile file:files) {
                        if (file.isDirectory()) {
                            startDown(localBaseDir, remoteBaseDir+"/"+file.getName(),openTime);
                        }else{
                           if(file_count<9)
                            downloadFile(file,localBaseDir,remoteBaseDir+"/"+file.getName(),openTime);
                        }
                    }
                    /*for (int i = 0; i < files.length; i++) {
                        try {
                            downloadFile(files[i], localBaseDir, remoteBaseDir);
                        } catch (Exception e) {
                            logger.error(e);
                            logger.error("<" + files[i].getName() + ">下载失败");
                        }
                    }*/
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
    private static void downloadFile(FTPFile ftpFile, String relativeLocalPath, String relativeRemotePath,String openTime) {
        OutputStream outputStream = null;
        try {
            if(relativeRemotePath.indexOf(openTime)<0){
                return;
            }
            File entryDir = new File(relativeLocalPath);
            //如果文件夹路径不存在，则创建文件夹
            if (!entryDir.exists() || !entryDir.isDirectory()) {
                entryDir.mkdirs();
            }
            System.out.println(ftpFile.getName());
            relativeLocalPath= relativeLocalPath + ftpFile.getName();
            File locaFile = new File(relativeLocalPath);
            //判断文件是否存在，存在则返回
            if (locaFile.exists()) {
                return;
            } else {
               //file_count++;
                outputStream = new FileOutputStream(relativeLocalPath);
                ftpClient.retrieveFile(relativeRemotePath, outputStream);
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

    /**
     * 先配置下载链接，在下载文件。 调用了startDown和downloadFile写的方法
     *
     * @param savepath
     * @return
     * @throws Exception
     */
    public static boolean downfile(String savepath,String deviceNum,String openTime) throws Exception {
        try {

            //File file = new File("F:/test/com/test/Testng.java");
            //FtpUtil.upload(file);//把文件上传在ftp上
            return FtpUtil.startDown(savepath, deviceNum,openTime);//下载ftp文件测试

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }


        public static void main(String[] args) {
            try {
               FtpUtil.downfile("/Users/taller/Documents/tomcat-8.5.24/webapps/ROOT/uploads/hlxx/00000005/20180329/","/00000005","20180329");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

}
