package com.dream.socket.entity;


import com.dream.socket.utils.ByteUtil;
import com.dream.socket.utils.Constants;
import com.dream.socket.utils.DateUtil;
import com.dream.socket.utils.Des;

import java.util.Date;

/**
 * @author 陶乐乐(wangyiqianyi@qq.com)
 * @ClassName: AuthModel.java
 * @Description:
 * @date 2017-12-26 上午11:10
 */
public class AuthModel {
	private byte[] S;//同步字  2个字节
	private byte[] T;//指令字  1个字节
	private byte[] L;//数据内容+校验位字节数 2个字节
	private byte[] V;//数据内容
	private byte[] C;//校验位 2个字节

	private boolean encrypt;
	private int length;

	public AuthModel(){
	}
	public AuthModel(byte[] t){
		this.S= new byte[]{(byte) 0xfa, (byte) 0xfb};
		this.T=t;
		if(getV()==null){
			this.L= ByteUtil.toHH((short)2);
			short cc=(short)~(getT()[0]+getL()[0]+getL()[1]);
			this.C=ByteUtil.toHH(cc);
		}else{
			short st=(short) (getV().length+2);
			this.L=ByteUtil.toHH(st);
			String cc=ByteUtil.bytesToHex(getT())
					+ByteUtil.bytesToHex(getL())
					+ByteUtil.bytesToHex(getV());
			cc=ByteUtil.makeChecksum(cc);
			int ccc=Integer.parseInt(cc,16);
			this.C=ByteUtil.toHH((short)~ccc);
		}
	}
	public AuthModel(byte[] t,byte[] v){
		this.S= new byte[]{(byte) 0xfa, (byte) 0xfb};
		this.T=t;
		this.V=v;
		short st=(short) (getV().length+2);
		this.L=ByteUtil.toHH(st);
		String cc=ByteUtil.bytesToHex(getT())
				+ByteUtil.bytesToHex(getL())
				+ByteUtil.bytesToHex(getV());
		cc=ByteUtil.makeChecksum(cc);
		int ccc=Integer.parseInt(cc,16);
		this.C=ByteUtil.toHH((short)~ccc);
	}
	public AuthModel(byte[] t,int[] arrayOfInt,String key){
		this.S= new byte[]{(byte) 0xfa, (byte) 0xfb};
		this.T=t;
		byte[] arrayOfByte = new byte[8 * (1 + (arrayOfInt.length - 1) / 8)];
		for (int j = 0; j < arrayOfInt.length; j++) {
			arrayOfByte[j] = (byte) arrayOfInt[j];
		}
		try {
			this.V= Des.encrypt(arrayOfByte,key.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		short st=(short) (getV().length+2);
		this.L=ByteUtil.toHH(st);
		String cc=ByteUtil.bytesToHex(getT())
				+ByteUtil.bytesToHex(getL())
				+ByteUtil.bytesToHex(getV());
		cc=ByteUtil.makeChecksum(cc);
		int ccc=Integer.parseInt(cc,16);
		this.C=ByteUtil.toHH((short)~ccc);
	}
	public AuthModel(byte[] t,byte[] arrayOfInt,String key){
		this.S= new byte[]{(byte) 0xfa, (byte) 0xfb};
		this.T=t;
		byte[] arrayOfByte = new byte[8 * (1 + (arrayOfInt.length - 1) / 8)];
		for (int j = 0; j < arrayOfInt.length; j++) {
			arrayOfByte[j] = arrayOfInt[j];
		}
		try {
			this.V= Des.encrypt(arrayOfByte,key.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		short st=(short) (getV().length+2);
		this.L=ByteUtil.toHH(st);
		String cc=ByteUtil.bytesToHex(getT())
				+ByteUtil.bytesToHex(getL())
				+ByteUtil.bytesToHex(getV());
		cc=ByteUtil.makeChecksum(cc);
		int ccc=Integer.parseInt(cc,16);
		this.C=ByteUtil.toHH((short)~ccc);
	}
	public byte[] getS() {
		return S;
	}

	public void setS(byte[] s) {
		S = s;
	}

	public byte[] getT() {
		return T;
	}

	public void setT(byte[] t) {
		T = t;
	}

	public byte[] getL() {
		return L;
	}

	public void setL(byte[] l) {
		L = l;
	}

	public byte[] getV() {
		return V;
	}

	public void setV(byte[] v) {
		V = v;
	}

	public byte[] getC() {
		return C;
	}

	public void setC(byte[] c) {
		C = c;
	}

	public boolean isEncrypt() {
		return encrypt;
	}

	public void setEncrypt(boolean encrypt) {
		this.encrypt = encrypt;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public String toString() {
		return ByteUtil.bytesToHex(getS())
				+ByteUtil.bytesToHex(getT())
				+ByteUtil.bytesToHex(getL())
				+ByteUtil.bytesToHex(getV())
				+ByteUtil.bytesToHex(getC());
	}
	public  static int [] toBingKeyData(int length,byte[] userCode){
		int[] arrayOfInt = new int[length];
		for (int i = 0; i < 4; i++) {
			arrayOfInt[i] =  userCode[i];
		}
		int i = 4;
		while (i < 12) {
			arrayOfInt[i] = Constants.LOCK_KEY.charAt(i - 4);
			i += 1;
		}
		arrayOfInt[12] = 0xff;
		arrayOfInt[13] = 0xff;
		return arrayOfInt;
	}
	public  static int [] toLockData(int length,String lockNum){
		int[] arrayOfInt = new int[length];
		byte[] arrayOfByte = Constants.OLD_KEY.getBytes();
		int i = 0;
		while (i < 8)
		{
			arrayOfInt[i] = arrayOfByte[i];
			i += 1;
		}
		arrayOfInt[8] = 1;
		arrayOfInt[9] = 1;
		arrayOfInt[10] = 1;
		arrayOfInt[11] = 1;
		arrayOfInt[12] = 1;
		arrayOfInt[13] = 1;
		arrayOfInt[14] = 1;
		arrayOfInt[15] = 1;
		i=0;
		//lockNum=ByteUtil.
		while (i < 16) {
			arrayOfInt[(i + 16)] =lockNum.charAt(i);
			i += 1;
		}
		return arrayOfInt;
	}
	public  static byte [] toLockDataByte(int length,String lockNum){
		lockNum=lockNum.replace("-","");
		if(lockNum.length()<length){
			int index=length-lockNum.length();
			while (index-->0){
				lockNum+="0";
			}
		}
		String data=Constants.OLD_KEY+"11111111";
		//System.out.println(ByteUtil.bytesToHex(data.getBytes())+lockNum);
		return ByteUtil.hexStrToByteArray(ByteUtil.bytesToHex(data.getBytes())+lockNum);
	}
	public  static int [] toData(int t,int length){
		int[] arrayOfInt = new int[length];
		if(t==12||t==9){//length=14
			String str= DateUtil.formatDateYmdhms(new Date());
			int i = 0;
			while (i < 14) {
				arrayOfInt[i] = str.charAt(i);
				i += 1;
			}
		}else if(t==1||t==8){//length=1
			arrayOfInt=new int[]{255};
		}else if(t==7){////length=14
			arrayOfInt[0] = 1;
			arrayOfInt[1] = 1;
			arrayOfInt[2] = 1;
			arrayOfInt[3] = 1;
			int i = 4;
			while (i < 12) {
				arrayOfInt[i] = Constants.LOCK_KEY.charAt(i - 4);
				i += 1;
			}
			arrayOfInt[12] = 0xff;
			arrayOfInt[13] = 0xff;
		}else if(t==10){////length=10
			byte[] arrayOfByte = "TTMJ_234".getBytes();
			int i = 0;
			while (i < 8)
			{
				arrayOfInt[i] = arrayOfByte[i];
				i += 1;
			}
		}else if(t==2){///length=32
			byte[] arrayOfByte = Constants.OLD_KEY.getBytes();
			int i = 0;
			while (i < 8)
			{
				arrayOfInt[i] = arrayOfByte[i];
				i += 1;
			}
			arrayOfInt[8] = 1;
			arrayOfInt[9] = 1;
			arrayOfInt[10] = 1;
			arrayOfInt[11] = 1;
			arrayOfInt[12] = 1;
			arrayOfInt[13] = 1;
			arrayOfInt[14] = 1;
			arrayOfInt[15] = 1;
			String startDate="00"+DateUtil.formatDateYmdhms(new Date());
			i=0;
			while (i < 16) {
				arrayOfInt[(i + 16)] =startDate.charAt(i);
				i += 1;
			}

		}
		return arrayOfInt;
	}
	public  static int [] toData(int t,int length,String string){
		int[] arrayOfInt = new int[length];
		if(t==12||t==9){
			int i = 0;
			while (i < 14) {
				arrayOfInt[i] = string.charAt(i);
				i += 1;
			}
		}else if(t==7){
			for (int i = 0; i < 4; i++) {
				arrayOfInt[i] = string.charAt(i);
			}
			int i = 4;
			while (i < 12) {
				arrayOfInt[i] = Constants.LOCK_KEY.charAt(i - 4);
				i += 1;
			}
		}else if(t==5){//length=64

		}
		return arrayOfInt;
	}



	/**
	 * @author       陶乐乐(wangyiqianyi@qq.com)
	 * @Description:  length=95
	 * @date         2018-01-30 10:10:10
	 * @params       userCode 用户编号
	 *               lockCode 锁识别号
	 *               macAddress MAC地址
	 * @return
	 * @throws
	 */
	public  static int [] AuthorizationKey(byte[] userCode,String lockNum,String macAddress,String startDate,String endDate,int status){
		byte[] lockCode=ByteUtil.hexStrToByteArray(ByteUtil.bytesToHex(lockNum.getBytes()));
		String db=lockNum.substring(12,16);
		int[] arrayOfInt = new int[64];
		for (int i = 0; i < 4; i++) {
			arrayOfInt[i] =  userCode[i];
		}
		int i = 4;
		while (i < 20) {
			arrayOfInt[i] =lockCode[i - 4];
			i += 1;
		}
		i = 0;
		while (i < 14) {
			arrayOfInt[(i + 20)] =startDate.charAt(i);
			i += 1;
		}
		i = 0;
		while (i < 14) {
			arrayOfInt[(i + 34)] =endDate.charAt(i);
			i += 1;
		}
		i = 0;
		while (i < 14) {
			arrayOfInt[(i + 48)] =  startDate.charAt(i);
			i += 1;
		}
		try {
			byte[] arrayOfByte = new byte[8 * (1 + (arrayOfInt.length - 1) / 8)];
			for (int j = 0; j < arrayOfInt.length; j++) {
				arrayOfByte[j] = (byte) arrayOfInt[j];
			}
			byte[] localObject= Des.encrypt(arrayOfByte, Constants.OLD_KEY.getBytes());//可能有问题

			arrayOfInt=new int[95];
			i = 0;
			while (i < 64) {
				arrayOfInt[i] =localObject[i];
				i++;
			}
			for (int j= 0; j < 4; j++) {
				arrayOfInt[j+64] = userCode[j];
			}
			arrayOfInt[68] = status;//操作类型 1:添加  0 清除
			 i = 69;
			while (i < 85)
			{
				arrayOfInt[i] = lockCode[i - 69];
				i += 1;
			}
			i = 0;
			String macString []=macAddress.split(":");
			while (i < 6)
			{
				arrayOfInt[(i + 85)] = Integer.parseInt(macString[i], 16);
				i += 1;
			}
			arrayOfInt[91] = Integer.parseInt(db.substring(0,2));//存储位置
			arrayOfInt[92] = Integer.parseInt(db.substring(2,4));//存储位置
			arrayOfInt[93] = 10;//
			arrayOfInt[94] = 10;//
			//arrayOfInt[95] = 0xff;//次数 待定
		} catch (Exception e) {
			e.printStackTrace();
			return new int[95];
		}

		return arrayOfInt;
	}
	/**
	 * @author       陶乐乐(wangyiqianyi@qq.com)
	 * @Description:  length=95
	 * @date         2018-01-30 10:10:10
	 * @params       userCode 用户编号
	 *               lockCode 锁识别号
	 *               macAddress MAC地址
	 * @return
	 * @throws
	 */
	public  static byte [] AuthorizationKeyToByte(String userCode,String lockNum,String macAddress,String startDate,String endDate,int status){
		//00410001001200210001FFFFFFFFFFFF
		lockNum=lockNum.replace("-","");
		String data=ByteUtil.bytesToHex(userCode.getBytes())+lockNum+ByteUtil.bytesToHex(startDate.getBytes())+ByteUtil.bytesToHex(endDate.getBytes())+ByteUtil.bytesToHex(startDate.getBytes());
		String db=lockNum.substring(14,16)+lockNum.substring(18,20);
		System.out.println(db+"XXX");
		byte[] arrayOfInt = ByteUtil.hexStrToByteArray(data);
		byte[] arrayOfByte = new byte[8 * (1 + (ByteUtil.hexStrToByteArray(data).length - 1) / 8)];
		System.out.println(arrayOfInt.length);
		for (int j = 0; j < arrayOfInt.length; j++) {
			arrayOfByte[j] = arrayOfInt[j];
		}
		try {
			byte[] localObject= Des.encrypt(arrayOfByte, Constants.OLD_KEY.getBytes());//可能有问题
			arrayOfInt=new byte[96];
			int i = 0;
			while (i < 64) {
				arrayOfInt[i] =localObject[i];
				i++;
			}
			byte userCodeByte []=ByteUtil.hexStrToByteArray(ByteUtil.bytesToHex(userCode.getBytes()));
			System.out.println(userCodeByte.length);
			for (int j= 0; j < 4; j++) {
				arrayOfInt[j+64] = userCodeByte[j];
			}
			byte [] statusBytes=ByteUtil.hexStrToByteArray(ByteUtil.bytesToHex(String.valueOf(status).getBytes()));
			System.out.println(statusBytes.length);
			for (int j= 0; j < 1; j++) {
				arrayOfInt[j+68] = 1;
			}
			byte[] lockNumByte=ByteUtil.hexStrToByteArray(lockNum);
			for (int j= 0; j < 16; j++) {
				arrayOfInt[j+69] = lockNumByte[j];
			}
			i = 0;
			String macString=macAddress.replace(":","");
			byte[] macByte=ByteUtil.hexStrToByteArray(macString);
			while (i < 6)
			{
				arrayOfInt[(i + 84)] = macByte[i];
				i += 1;
			}
			byte [] dbBytes=ByteUtil.hexStrToByteArray(ByteUtil.bytesToHex(String.valueOf(db).getBytes()));
			i=0;
			arrayOfInt[91] = 1;
			arrayOfInt[92] = 8;
			arrayOfInt[93] = 0;
			arrayOfInt[94] = 1;
			/*while (i < 4)
			{
				arrayOfInt[(i + 90)] = dbBytes[i];
				i += 1;
			}*/
			arrayOfInt[94] = 10;//
			arrayOfInt[95] = 10;//
		} catch (Exception e) {
			e.printStackTrace();
			return new byte[95];
		}
		return arrayOfInt;
	}
	/**
	 * @author       陶乐乐(wangyiqianyi@qq.com)
	 * @Description:  length=95
	 * @date         2018-01-30 10:10:10
	 * @params       userCode 用户编号
	 *               lockCode 锁识别号
	 *               macAddress MAC地址
	 * @return
	 * @throws
	 */
	public  static int [] AuthorizationKeyX(String userCode,String lockNum,String macAddress,String startDate,String endDate,int status,int index){
		lockNum=lockNum.replace("-","");
		byte []lockCode=ByteUtil.hexStrToByteArray(lockNum);//ByteUtil.hexStr2Str().getBytes();
		//byte [] userByteCode=ByteUtil.hexStrToByteArray(ByteUtil.bytesToHex(userCode.getBytes()));
		int[] arrayOfInt = new int[64];
		for (int i = 0; i < 4; i++) {
			arrayOfInt[i] =  userCode.charAt(i);
		}
		int i = 4;
		while (i < 20) {
			arrayOfInt[i] =lockCode[i - 4];
			i += 1;
		}
		i = 0;
		while (i < 14) {
			arrayOfInt[(i + 20)] =startDate.charAt(i);
			i += 1;
		}
		i = 0;
		while (i < 14) {
			arrayOfInt[(i + 34)] =endDate.charAt(i);
			i += 1;
		}
		i = 0;
		while (i < 14) {
			arrayOfInt[(i + 48)] =  startDate.charAt(i);
			i += 1;
		}
		try {
			byte[] arrayOfByte = new byte[8 * (1 + (arrayOfInt.length - 1) / 8)];
			for (int j = 0; j < arrayOfInt.length; j++) {
				arrayOfByte[j] = (byte) arrayOfInt[j];
			}
			byte[] localObject= Des.encrypt(arrayOfByte, Constants.OLD_KEY.getBytes());//可能有问题

			arrayOfInt=new int[95];
			i = 0;
			while (i < 64) {
				arrayOfInt[i] =localObject[i];
				i++;
			}
			for (int j= 0; j < 4; j++) {
				arrayOfInt[j+64] = userCode.charAt(j);
			}
			arrayOfInt[68] = status;//操作类型 1:添加  0 清除
			i = 69;
			while (i < 85)
			{
				arrayOfInt[i] = lockCode[i - 69];
				i += 1;
			}
			i = 0;
			String macString []=macAddress.split(":");
			while (i < 6)
			{
				arrayOfInt[(i + 85)] = Integer.parseInt(macString[i], 16);
				i += 1;
			}
			String str = String.format("%04d",index);
			byte [] logNumCode=ByteUtil.hexStr2Str(str).getBytes();
			i=0;
			while (i<2){
				arrayOfInt[i+91]=logNumCode[i];
				i +=1;
			}
			//arrayOfInt[91] =0x00; //Integer.parseInt(db.substring(0,2),16);//存储位置
			//arrayOfInt[92] =0x01; //Integer.parseInt(db.substring(2,4),16);//存储位置
			arrayOfInt[93] = 10;//
			arrayOfInt[94] = 10;//
			//arrayOfInt[95] = 0xff;//次数 待定
		} catch (Exception e) {
			e.printStackTrace();
			return new int[95];
		}

		return arrayOfInt;
	}
	public static void main(String[] args) throws Exception {
		/*AuthModel authModel=new AuthModel(new byte[]{6});
		System.out.println(authModel.toString());*/

	/*	String authModel2=new AuthModel(new byte[]{1}, new byte [] {(byte) 0xFF}).toString();
		System.out.println("加密前的命令是:"+authModel2);

		System.out.println("加密的密匙是:"+ByteUtil.bytesToHex("SDtt6789".getBytes()));

		System.out.println(8 * (1 + (1 - 1) / 8));
		byte[] arrayOfByte1 = new byte[8 * (1 + (1 - 1) / 8)];
		for (int j = 0; j < 1; j++) {
			int i9 = (byte)new int[] { 255 }[j];
			arrayOfByte1[j] = (byte) i9;
		}
		System.out.println(ByteUtil.bytesToHex(arrayOfByte1));
		//System.out.println("输入参数加密:"+ByteUtil.bytesToHex(Des.encrypt(new byte [] {(byte) 0xFF},"SDtt6789".getBytes())));
		String authModel3=new AuthModel(new byte[]{1}, Des.encrypt(arrayOfByte1,"SDtt6789".getBytes())).toString();

		System.out.println("解密"+ByteUtil.bytesToHex(Des.decrypt(ByteUtil.hexToBytes("8623a442413a844a"),"SDtt6789".getBytes())));

		System.out.println(authModel3);*/

		//给蓝牙校时

		/*String authModel2=new AuthModel(new byte[]{12}, DateUtil.formatDateYmdhms(new Date()).getBytes()).toString();
		System.out.println("加密前的命令是:"+authModel2);

		//sendCommand(int paramInt1, int[] paramArrayOfInt, int paramInt2, byte[] paramArrayOfByte)
		int paramInt1=12;
		int paramInt2=14;
		int[] arrayOfInt = new int[14];
		int i = 0;
		String str1=DateUtil.formatDateYmdhms(new Date());
		while (i < 14) {
			arrayOfInt[i] = str1.charAt(i);
			i += 1;
		}
		byte[] arrayOfByte1 = new byte[8 * (1 + (paramInt2 - 1) / 8)];
		for (int j = 0; j < 1; j++) {
			int i9 = (byte)arrayOfInt[j];
			System.out.println(i9);
			arrayOfByte1[j] = (byte) i9;
		}
		System.out.println(ByteUtil.bytesToHex(arrayOfByte1));


		System.out.println("加密的密匙是:"+ByteUtil.bytesToHex("TTMJ_345".getBytes()));
		//System.out.println( Des.encrypt(DateUtil.formatDateYmdhms(new Date())));
		String authModel3=new AuthModel(new byte[]{12}, DesUtil.encrypt(str1.getBytes(),"TTMJ_345".getBytes())).toString();
		System.out.println(authModel3);*/


     /* //指令编号1
		String authModel2=new AuthModel(new byte[]{1}, new byte [] {(byte) 0xFF}).toString();
		System.out.println("加密前的命令是:"+authModel2);

		System.out.println("加密的密匙是:"+ByteUtil.bytesToHex("TTMJ_345".getBytes()));

		//System.out.println("输入参数加密:"+ByteUtil.bytesToHex(Des.encrypt(new byte [] {(byte) 0xFF},"SDtt6789".getBytes())));
		String authModel3=new AuthModel(new byte[]{12},toData(12,14),"SDtt6789").toString();

		System.out.println(authModel3.toString());*/
        String userCode="1000";
		for (int i = 0; i < 4; i++) {
			System.out.println(userCode.charAt(i));
		}
		String xx="7B224461746154797065223A22446576696365222C22436F6C6C6563746F724964223A223030303030303032222C22436F6E74656E74223A6E756C6C2C22537461747573436F6465223A2231354234227D";
		System.out.println(ByteUtil.hexStr2Str(xx));
		String lockNum="00410001001200210001FFFFFFFFFFFF";
		String bankString = "";
		for(int i=0;i<lockNum.length();i++){
			if(i%4==0 && i>0){
				bankString +="-";
			}
			bankString += lockNum.charAt(i);
		}
		//lockNum=lockNum.replace("-","");
		System.out.println(bankString);
		System.out.println(lockNum.getBytes().length);
       byte a[]=toLockDataByte(32,"0041-0001-0011-0019-0001");
		System.out.println(a.length);

		//00410001001200210001FFFFFFFFFFFF
		String num="00410001001200210001FFFFFFFFFFFF";
		System.out.println(num);
		String db=lockNum.substring(14,16)+lockNum.substring(18,20);
		System.out.println(db);
		System.out.println(ByteUtil.bytesToHex(userCode.getBytes()));
		System.out.println(ByteUtil.bytesToHex("2101".getBytes()));

		String authModel=new AuthModel(new byte[]{5},AuthModel.AuthorizationKeyX("1071","0041-0001-0011-0019-0004-0000-0000-0000","EE:56:8A:87:D9:5F","20180411101525","20180412101527",1,0),Constants.LOCK_KEY).toString();


		//String authModel2=new AuthModel(new byte[]{5},AuthModel.AuthorizationKeyToByte("1071","0041-0001-0011-0018-0001-0000-0000-0000","EE:56:8A:87:D9:5F","20180411101525","20180412101527",1),Constants.LOCK_KEY).toString();

		System.out.println(authModel);
		String xxs="2710";
		System.out.println(xxs.substring(2,4)+"xx");
		System.out.println(xxs.substring(2,4)+"xx");
		System.out.println(Integer.parseInt(xxs.substring(0,2), 16));
		System.out.println(Integer.parseInt(xxs.substring(2,4), 16));

		//System.out.println(authModel2);
		//System.out.println(ByteUtil.bytesToHex(String.valueOf("2706").getBytes()));
		//lockNum="0041-0001-0001-0001-0009-0000-0000-0000".replace("-","");
		//lockNum=lockNum.substring(14,16)+lockNum.substring(18,20);
		//System.out.println(lockNum);
	}
	private static void toStringMethod(int[] arr) {
		// 自定义一个字符缓冲区，
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		//遍历int数组，并将int数组中的元素转换成字符串储存到字符缓冲区中去
		for(int i=0;i<arr.length;i++)
		{
			if(i!=arr.length-1)
				sb.append(arr[i]+" ,");
			else
				sb.append(arr[i]+" ]");
		}
		System.out.println(sb);
	}
}
