package com.dream.serial;

import com.dream.socket.utils.ByteUtil;
import com.dream.util.ResponseSocketUtil;
import gnu.io.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TooManyListenersException;

/**
 * ${DESCRIPTION}
 *
 * @author Admin
 * @create 2018-03-30 11:24
 **/
public class ReadSerialPortData implements Runnable, SerialPortEventListener {
    private String appName = "控制器测试";
    private int timeout = 5000;//open 端口时的等待时间
    private int threadTime = 0;
    private String sport;
    private CommPortIdentifier commPort;
    private SerialPort serialPort;

    private InputStream inputStream;
    private OutputStream outputStream;

    private String resultString="";

    private String responseString;//解析后数据
    public ReadSerialPortData(){}

    /**
     38      * @方法名称 :listPort
     39      * @功能描述 :列出所有可用的串口
     40      * @返回值类型 :void
     41      */
    @SuppressWarnings("rawtypes")
    public void listPort(){
        CommPortIdentifier cpid;
        Enumeration en = CommPortIdentifier.getPortIdentifiers();

        System.out.println("now to list all Port of this PC：" +en);

        while(en.hasMoreElements()){
            cpid = (CommPortIdentifier)en.nextElement();
            if(cpid.getPortType() == CommPortIdentifier.PORT_SERIAL){
                System.out.println(cpid.getName() + ", " + cpid.getCurrentOwner());
            }
        }
    }
    /**
     * 查找所有可用端口
     *
     * @return 可用端口名称列表
     */
    @SuppressWarnings("unchecked")
    public static final List<String> findPort() {
        // 获得当前所有可用串口
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier
                .getPortIdentifiers();
        List<String> portNameList = new ArrayList<>();
        // 将可用串口名添加到List并返回该List
        while (portList.hasMoreElements()) {
            String portName = portList.nextElement().getName();
            portNameList.add(portName);
        }
        return portNameList;
    }
    /**
     63      * @方法名称 :selectPort
     64      * @功能描述 :选择一个端口，比如：COM1
     65      * @返回值类型 :void
     66      *    @param portName
     67      */
    @SuppressWarnings("rawtypes")
    public void selectPort(String portName){

        this.commPort = null;
        CommPortIdentifier cpid;
        Enumeration en = CommPortIdentifier.getPortIdentifiers();

        while(en.hasMoreElements()){
            cpid = (CommPortIdentifier)en.nextElement();
            if(cpid.getPortType() == CommPortIdentifier.PORT_SERIAL
                    && cpid.getName().equals(portName)){
                this.commPort = cpid;
                break;
            }
        }

        openPort();
    }

    /**
     88      * @方法名称 :openPort
     89      * @功能描述 :打开SerialPort
     90      * @返回值类型 :void
     91      */
    private void openPort(){
        if(commPort == null)
            log(String.format("无法找到名字为'%1$s'的串口！", commPort.getName()));
        else{
            log("端口选择成功，当前端口："+commPort.getName()+",现在实例化 SerialPort:");

            try{
                serialPort = (SerialPort)commPort.open(appName, timeout);
                // 设置串口的波特率等参数
                    serialPort.setSerialPortParams(9600,
                        SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
                        SerialPort.PARITY_NONE);
                log("实例 SerialPort 成功！");
            }catch(PortInUseException e) {
                throw new RuntimeException(String.format("端口'%1$s'正在使用中！",
                        commPort.getName()));
            } catch (UnsupportedCommOperationException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     109      * @方法名称 :checkPort
     110      * @功能描述 :检查端口是否正确连接
     111      * @返回值类型 :void
     112      */
    private void checkPort(){
        if(commPort == null)
            throw new RuntimeException("没有选择端口，请使用 " +
                    "selectPort(String portName) 方法选择端口");

        if(serialPort == null){
            throw new RuntimeException("SerialPort 对象无效！");
        }
    }
    /**
     124      * @方法名称 :write
     125      * @功能描述 :向端口发送数据，请在调用此方法前 先选择端口，并确定SerialPort正常打开！
     126      * @返回值类型 :void
     127      *    @param message
     128      */
    public void write(String message) {
        checkPort();

        try{
            outputStream = new BufferedOutputStream(serialPort.getOutputStream());
        }catch(IOException e){
            throw new RuntimeException("获取端口的OutputStream出错："+e.getMessage());
        }

        try{
            outputStream.write(ByteUtil.hexStrToByteArray(message));
            log("信息发送成功！");
        }catch(IOException e){
            throw new RuntimeException("向端口发送信息时出错："+e.getMessage());
        }finally{
            try{
                outputStream.close();
            }catch(Exception e){
            }
        }
    }
    /**
     152      * @方法名称 :startRead
     153      * @功能描述 :开始监听从端口中接收的数据
     154      * @返回值类型 :void
     155      *    @param time  监听程序的存活时间，单位为秒，0 则是一直监听
     156      */
    public void startRead(int time){
        checkPort();

        try{
            inputStream = new BufferedInputStream(serialPort.getInputStream());
        }catch(IOException e){
            throw new RuntimeException("获取端口的InputStream出错："+e.getMessage());
        }

        try{
            serialPort.addEventListener(this);
        }catch(TooManyListenersException e){
            throw new RuntimeException(e.getMessage());
        }

        serialPort.notifyOnDataAvailable(true);

        log(String.format("开始监听来自'%1$s'的数据--------------", commPort.getName()));
        if(time > 0){
            this.threadTime = time*1000;
            Thread t = new Thread(this);
            t.start();
            log(String.format("监听程序将在%1$d毫秒后关闭。。。。", threadTime));
        }
    }

    public void startRead(){
        checkPort();

        try{
            inputStream = new BufferedInputStream(serialPort.getInputStream());
        }catch(IOException e){
            throw new RuntimeException("获取端口的InputStream出错："+e.getMessage());
        }

        try{
            serialPort.addEventListener(this);
        }catch(TooManyListenersException e){
            throw new RuntimeException(e.getMessage());
        }

        serialPort.notifyOnDataAvailable(true);

        log(String.format("开始监听来自'%1$s'的数据--------------", commPort.getName()));

    }

    /**
     204      * @方法名称 :close
     205      * @功能描述 :关闭 SerialPort
     206      * @返回值类型 :void
     207      */
    public void close(){
        serialPort.close();
        serialPort = null;
        commPort = null;
    }


    public void log(String msg){
        System.out.println(appName+" --> "+msg);
    }


    /**
     221      * 数据接收的监听处理函数
     222      */
    @Override
    public void serialEvent(SerialPortEvent arg0) {

        switch(arg0.getEventType()){
            case SerialPortEvent.BI:/*Break interrupt,通讯中断*/
            case SerialPortEvent.OE:/*Overrun error，溢位错误*/
            case SerialPortEvent.FE:/*Framing error，传帧错误*/
            case SerialPortEvent.PE:/*Parity error，校验错误*/
            case SerialPortEvent.CD:/*Carrier detect，载波检测*/
            case SerialPortEvent.CTS:/*Clear to send，清除发送*/
            case SerialPortEvent.DSR:/*Data set ready，数据设备就绪*/
            case SerialPortEvent.RI:/*Ring indicator，响铃指示*/
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:/*Output buffer is empty，输出缓冲区清空*/
                break;
            case SerialPortEvent.DATA_AVAILABLE:/*Data available at the serial port，端口有可用数据。读到缓冲数组，输出到终端*/
                byte[] bytes =  new byte[1024];
                try {
                    int bufflenth = inputStream.available();
                    while (bufflenth != 0) {
                        //inputStream.read(readBuffer);
                        bytes = new byte[bufflenth];
                        inputStream.read(bytes);
                        bufflenth = inputStream.available();
                       // readStr += new String(readBuffer).trim();
                    }
                    String comResult=ByteUtil.bytesToHex(bytes);
                    resultString+=comResult;
                    System.out.println("返回值："+comResult);
                    //System.out.println(new String(bytes));
                   // s2 = new String(readBuffer).trim();resultString

                    //log(s2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
       // System.out.println(resultString);\
        responseString= resultString;
    }


    @Override
    public void run() {
        try{
            Thread.sleep(10000);
            serialPort.close();
            log(String.format("端口''监听关闭了！", commPort.getName()));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getResponseString() {
        return responseString;
    }

    public void setResponseString(String responseString) {
        this.responseString = responseString;
    }
}
