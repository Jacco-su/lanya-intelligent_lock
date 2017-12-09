//package com.dream.brick.equipment.bean;
//
//import javax.persistence.*;
//import java.util.Date;
//
///**
// * 授权记录实体类
// */
//
//@Entity
//@Table(name = "t_authorization")
//public class Authorization implements java.io.Serializable {
//
//    private static final long serialVersionUID = 4725135016331954412L;
//    private String id;
//    private String starttime;   //起始时间
//    private Date endtime;       //失效时间
//    private String keyssId; //授权钥匙id
//    private String locksId;   //开锁Id
//    private String type;        //授权类型
//    private String uId;    // 授权于
//    private String aName;   //操作员
//    private String aDate;       //授权时间
//    private String workticket; //工作票
//
//
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getStarttime() {
//        return starttime;
//    }
//
//    public void setStarttime(String starttime) {
//        this.starttime = starttime;
//    }
//
//    public Date getEndtime() {
//        return endtime;
//    }
//
//    public void setEndtime(Date endtime) {
//        this.endtime = endtime;
//    }
//
//    public String getKeyssId() {
//        return keyssId;
//    }
//
//    public void setKeyssId(String keyssId) {
//        this.keyssId = keyssId;
//    }
//
//    public String getLocksId() {
//        return locksId;
//    }
//
//    public void setLocksId(String locksId) {
//        this.locksId = locksId;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getUId() {
//        return uId;
//    }
//
//    public void setUid(String uid) {
//        this.uId = uid;
//    }
//
//    public String getaName() {
//        return aName;
//    }
//
//    public void setaName(String aName) {
//        this.aName = aName;
//    }
//
//    public String getaDate() {
//        return aDate;
//    }
//
//    public void setaDate(String aDate) {
//        this.aDate = aDate;
//    }
//
//    public String getWorkticket() {
//        return workticket;
//    }
//
//    public void setWorkticket(String workticket) {
//        this.workticket = workticket;
//    }
//}
