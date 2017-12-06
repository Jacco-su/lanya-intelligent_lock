//package com.dream.util;
//
////读取串口 com 外部设备
////        原创 2012年08月21日 16:43:10 标签：java /string /exception /templates /thread /buffer 16649
////        [java] view plain copy'
/////*
//// * To change this template, choose Tools | Templates
//// * and open the template in the editor.
//// */
//       // package CalcWgtPro;
//
///**
// *
// * @author luolai
// */
//        import java.io.BufferedReader;
//        import java.io.IOException;
//        import java.io.InputStream;
//        import java.io.InputStreamReader;
//        import java.util.Iterator;
//        import java.util.List;
//        import java.util.TooManyListenersException;
//
//        import javax.comm.CommPortIdentifier;
//        import javax.comm.PortInUseException;
//        import javax.comm.SerialPort;
//        import javax.comm.SerialPortEvent;
//        import javax.comm.SerialPortEventListener;
//        import javax.comm.UnsupportedCommOperationException;
//
//public class Readst {
//
//    static String retValue = "000000";
//
//    public void init() {
//        try {
//            CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier("COM1");
//            // 直接取得COM3端口
//            System.out.println(portId.getName() + ":开启");
//            LogInfo.appendLog(portId.getName() + ":开启");
//            @SuppressWarnings("unused")
//            Read reader = new Read(portId);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            LogInfo.appendLog(ex.getMessage());
//        }
//    }
//
//    class Read implements Runnable, SerialPortEventListener {
//
//        InputStream inputStream;
//        SerialPort serialPort;
//        Thread readThread;
//
//        public Read(CommPortIdentifier portId) throws InterruptedException {
//            try {
//                serialPort = (SerialPort) portId.open("MyReader", 2000);
//                //portId.open("串口所有者名称", 超时等待时间);
//            } catch (PortInUseException e) {
//                //如果端口被占用就抛出这个异常
//                e.printStackTrace();
//                LogInfo.appendLog(e.getMessage());
//            }
//
//            try {
//                inputStream = serialPort.getInputStream();
//                //从COM3获取数据
//            } catch (IOException e) {
//                LogInfo.appendLog(e.getMessage());
//            }
//
//            try {
//                serialPort.addEventListener(this);
//                //添加监听器
//            } catch (TooManyListenersException e) {
//            }
//
//            serialPort.notifyOnDataAvailable(true);
//            /*
//             * 侦听到串口有数据,触发串口事件
//             */
//            try {
//                serialPort.setSerialPortParams(2400,//波特率
//                        SerialPort.DATABITS_8,//数据位数
//                        SerialPort.STOPBITS_1,//停止位
//                        SerialPort.PARITY_NONE);//校验
//            } catch (UnsupportedCommOperationException e) {
//            }
//            readThread = new Thread(this);
//            readThread.start();
//            //启动线程
//        }
//
//        public void run() {
//            try {
//                Thread.sleep(2000);
//                serialPort.close();
//                System.out.println("COM1:关闭");
//                //设定30秒后端口关闭，程序随之结束
//            } catch (InterruptedException e) {
//                LogInfo.appendLog(e.getMessage());
//            }
//        }
//
//        /**
//         * BI -通讯中断. CD -载波检测. CTS -清除发送. DATA_AVAILABLE -有数据到达. DSR -数据设备准备好.
//         * FE -帧错误. OE -溢位错误. OUTPUT_BUFFER_EMPTY -输出缓冲区已清空. PE -奇偶校验错. RI -
//         * 振铃指示. 一般最常用的就是DATA_AVAILABLE--串口有数据到达事件。
//         */
//        public void serialEvent(SerialPortEvent event) {
//
//            switch (event.getEventType()) {
//                case SerialPortEvent.BI:
//                case SerialPortEvent.OE:
//                case SerialPortEvent.FE:
//                case SerialPortEvent.PE:
//                case SerialPortEvent.CD:
//                case SerialPortEvent.CTS:
//                case SerialPortEvent.DSR:
//                case SerialPortEvent.RI:
//                case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
//                    break;
//                case SerialPortEvent.DATA_AVAILABLE:
//                    byte[] readBuffer = new byte[20];
//
//                    try {
//                        while (inputStream.available() > 0) {
//                            //int numBytes = inputStream.read(readBuffer);
//                            //System.out.println("numBytes" + numBytes);
//                            //retValue = new String(readBuffer).trim();
//                            //System.out.println(retValue);
//                            BufferedReader comReader = new BufferedReader(new InputStreamReader(inputStream));
//                            String temp = comReader.readLine().trim();
//                            System.out.println(comReader.read() + ":" + temp);
//                            if (temp.indexOf("#0") >= 0 || temp.indexOf("#8") >= 0) {
//                                int p = temp.indexOf("#");
//                                temp = temp.substring(p + 3, temp.length()).replace(" ", "").trim();
//                                try {
//                                    Integer.parseInt(temp);
//                                } catch (Exception e) {
//                                    System.out.println("忽略次数据:" + temp);
//                                    LogInfo.appendLog("忽略次数据:" + temp);
//                                    retValue = "0";
//                                    return;
//                                }
//                                System.out.println(temp);
//                                retValue = String.valueOf(Integer.parseInt(temp.substring(1, 5))) + "." + temp.substring(5, 6);
//                                System.out.println(retValue);
//                            }
//                        }
//                        //strWGT = new String(readBuffer).trim();
//                        //System.out.println(strWGT);
//                        //输出读入的字符
//                    } catch (IOException e) {
//                        LogInfo.appendLog(e.getMessage());
//                    }
//                    break;
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        Readst reader = new Readst();
//        reader.init();
//        reader.retValue = "0";
//        //(reader.retValue);
//    }
//}
////此程序只用于监听COM3端口，当程序执行时开启COM3端口，等待30秒后，端口COM3关闭，程序随之关闭
////Read 为内部类，这样可以共用读取出来的字符串信息
////在Readstr 中定义一个静态变量static String str = "000000";
////然后在读出数据后str = new String(readBuffer).trim();
////这样可以在一个事件触发后，将str中的值赋给相应的值(比如说，让这个值显示在输入框中)
