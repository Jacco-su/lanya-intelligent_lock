package com.dream.util;


import com.dream.socket.utils.ByteUtil;
import com.dream.socket.utils.Constants;
import com.dream.socket.utils.Des;

/**
 * @author 陶乐乐(wangyiqianyi@qq.com)
 * @ClassName: ResponseSocketUtil.java
 * @Description: 协议解析
 * @create 2018-03-30 12:17
 */
public class ResponseSocketUtil {
    //解析指令
    public static int T(String data) {
        String tStr = data.substring(4, 6);
        return Integer.parseInt(tStr, 16);
    }

    //解析长度
    public static int L(String data) {
        String tStr = data.substring(6, 10);
        return Integer.parseInt(tStr, 16);
    }

    //解析内容
    public static String V(String data) {
       // System.out.println(data + "XXXXX");
        if("".equals(data))return "";
        //if(L(data)*2!=data.length())return "";
        String str = "";
        int t = T(data);//指令
        if (t == 6) {//获取电量
            String dl = data.substring(10, 12);//电量
            str += "剩余电量" + Integer.parseInt(dl, 16) + "%,";
            dl = data.substring(14, 18);//版本号
            str += "版本号" + Integer.parseInt(dl, 16);
            dl = data.substring(20, 48);
            str += "当前时间" + ByteUtil.hexStr2Str(dl);
        } else if (t == 13) {//读取钥匙或者控制器的唯一ID
            String tStr = data.substring(10, 22);
            str += "钥匙地址->" + ByteUtil.getMac(tStr);
        } else if (t == 7 || t == 12) {
            String tStr = data.substring(10, 26);
            int i = 0;
            try {
                if (t == 12) {
                    i = ByteUtil.byte2int(Des.decrypt(ByteUtil.hexToBytes(tStr), Constants.LOCK_KEY.getBytes()));
                } else {
                    i = ByteUtil.byte2int(Des.decrypt(ByteUtil.hexToBytes(tStr), Constants.KEY.getBytes()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (i == 1) {
                if (t == 12) {
                    str += "************蓝牙钥匙校时成功";
                } else {
                    str += "*******蓝牙钥匙绑定成功!";
                }
            } else {
                if (t == 12) {
                    str += "************蓝牙钥匙校时失败!";
                } else {
                    str += "*******蓝牙钥匙绑定失败!";
                }
            }
        } else if (t == 1) {
            String tStr = data.substring(10, data.length() - 4);
            try {
                /*byte [] bytes = Des.decrypt(ByteUtil.hexToBytes(tStr), Constants.KEY.getBytes());
                System.out.println(tStr.length());
                byte lockNum [] = new byte[32];
                int i=0;
                while (i<32){
                    lockNum[i]=bytes[i];
                    i += 1;
                }*/
                tStr = ByteUtil.bytesToHex(Des.decrypt(ByteUtil.hexToBytes(tStr), Constants.KEY.getBytes()));
                String lockNum=tStr.substring(0, 32);
                String bankString = "";
                for(int i=0;i<lockNum.length();i++){
                    if(i%4==0 && i>0){
                        bankString += "-";
                    }
                    bankString += lockNum.charAt(i);
                }
                str += "*门锁识别号;" + bankString;
                //int msStatus=Integer.parseInt(tStr.substring(32,34),16);
                str += ";锁舌状态;未知";
                str += ";门磁状态;未知";
                int status = Integer.parseInt(tStr.substring(34, 36), 16);
                if (status == 0) {
                    str += ";安装状态;未安装";
                } else if (status == 1) {
                    str += ";安装状态;已安装";
                }
                String dl = tStr.substring(36, 64);
                str += ";当前时间" + ByteUtil.hexStr2Str(dl);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (t == 5) {
            String tStr = data.substring(10, data.length() - 4);
            int i = 0;
            try {
                i = ByteUtil.byte2int(Des.decrypt(ByteUtil.hexToBytes(tStr), Constants.LOCK_KEY.getBytes()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (i == 1) {
                str += "*****授权成功!";
            } else {
                str += "*****授权失败!";
            }
        } else if (t == 2) {
            String tStr = data.substring(10, data.length() - 4);
            int i = 0;
            try {
                i = ByteUtil.byte2int(Des.decrypt(ByteUtil.hexToBytes(tStr), Constants.KEY.getBytes()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (i == 1) {
                str += "**初始化成功!";
            } else if (i == 2) {
                str += "**已安装!";
            } else {
                str += "**初始化失败!";
            }
        } else if (t == 4) {
            String tStr = data.substring(10, data.length() - 4);
            try {
                tStr = ByteUtil.bytesToHex(Des.decrypt(ByteUtil.hexToBytes(tStr), Constants.LOCK_KEY.getBytes()));
                System.out.println(tStr.substring(0, 8));
                str += "****;日志编号;" + tStr.substring(0, 8);
                String dl = tStr.substring(8, 36);
                str += ";当前时间;" + ByteUtil.hexStr2Str(dl);
                System.out.println(dl);
                System.out.println(tStr);
                int status = Integer.parseInt(tStr.substring(36, 38), 16);
                if (status == 1) {
                    str += ";蓝牙钥匙开门;";
                } else if (status == 7) {
                    str += ";手机开门;";
                } else if (status == 5) {
                    str += ";FSU开门;";
                }
                int statusResult = Integer.parseInt(tStr.substring(38, 40), 16);
                if (statusResult == 1) {
                    str += ";操作成功!;";
                } else {
                    str += ";操作失败!;";
                }
                str += ";用户编号;" + tStr.substring(40, 48);
                System.out.println(tStr);
                str += ";门锁识别号;" + tStr.substring(48, 80);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    public static void main(String[] args) {

        System.out.println(V("FAFB010022974DF2F35180008E51828A85CA4076264ED8E0D5852C8417CD0E13D01B978883F08A"));
    }

    public static String hexString2String(String src) {
        String temp = "";
        for (int i = 0; i < src.length() / 2; i++) {
            temp = temp
                    + (char) Integer.valueOf(src.substring(i * 2, i * 2 + 2),
                    16).byteValue();
        }
        return temp;
    }
}