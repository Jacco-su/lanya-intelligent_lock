/*
 *  Copyright (c) 2016-2020  陶乐乐,毛磊版权所有 
 *  Tao Lele, Pierre Morel. All Rights Reserved.
 */

package com.dream.brick.equipment.bean;

import com.dream.util.PropertyNote;

/**
 * @author 陶乐乐(wangyiqianyi@qq.com)
 * @ClassName: ExtendsCompany.java
 * @Description:
 * @date 2016-03-29 17:15
 */
public class ExtendsCompany {
    @PropertyNote(name = "产品名称")
    private String productName;
    @PropertyNote(name = "执行标准")
    private String zxbz;
    @PropertyNote(name = "确认证书编号")
    private String qrzsbh;
    @PropertyNote(name = "有效期")
    private String validBeginTime;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getZxbz() {
        return zxbz;
    }

    public void setZxbz(String zxbz) {
        this.zxbz = zxbz;
    }

    public String getQrzsbh() {
        return qrzsbh;
    }

    public void setQrzsbh(String qrzsbh) {
        this.qrzsbh = qrzsbh;
    }

    public String getValidBeginTime() {
        return validBeginTime;
    }

    public void setValidBeginTime(String validBeginTime) {
        this.validBeginTime = validBeginTime;
    }
}
