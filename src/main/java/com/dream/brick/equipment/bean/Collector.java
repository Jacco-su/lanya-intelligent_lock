package com.dream.brick.equipment.bean;

import javax.persistence.*;
import java.util.Date;

/**
 * 采集器 数据库防问实体 类
 *
 * @author
 * @date 2017-12-05
 */
@Entity
@Table(name = "t_collector")
public class Collector implements java.io.Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 4725135016331954412L;
    private String id;
    private String cip;// 采集器IP地址
    private String disid;// 所在配电房
    //	private String dname; // 所在配电房名称
    private Date cdate;// 日期

    private int sortorder;// 配电房显示顺序

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //	public String getDname() {
//		return dname;
//	}
//	public void setDname(String dname) {
//		this.dname = dname;
//	}
    public String getDisid() {
        return disid;
    }

    public void setDisid(String disid) {
        this.disid = disid;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }


    public int getSortorder() {
        return sortorder;
    }

    public void setSortorder(int sortorder) {
        this.sortorder = sortorder;
    }

    public String getCip() {
        return cip;
    }

    public void setCip(String cip) {
        this.cip = cip;
    }
}
