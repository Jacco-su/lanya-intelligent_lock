package com.dream.brick.equipment.action;

import com.alibaba.fastjson.JSON;
import com.dream.brick.equipment.bean.AuthLog;
import com.dream.brick.equipment.bean.Collector;
import com.dream.brick.equipment.bean.Collectore;
import com.dream.brick.equipment.dao.CollectorDao;
import com.dream.brick.equipment.dao.CollectoreDao;
import com.dream.brick.equipment.dao.IAuthLogDao;
import com.dream.brick.listener.SessionData;
import com.dream.framework.dao.Pager;
import com.dream.socket.entity.AuthModel;
import com.dream.socket.entity.DataProtocol;
import com.dream.socket.entity.JsonDataProtocol;
import com.dream.socket.utils.ByteUtil;
import com.dream.socket.utils.Constants;
import com.dream.util.AppMsg;
import com.dream.util.FormatDate;
import com.dream.util.RedisTemplateUtil;
import com.dream.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * 钥匙 操作类
 */


@Controller
@Scope("prototype")
@RequestMapping("/authlog")
public class AuthLogAction {
    @Resource
    private IAuthLogDao authLogDao;

    @Resource
    private CollectorDao collectorDao;

    @Resource
    private CollectoreDao collectoreDao;

    @Resource
    private RedisTemplate redisTemplate;
    private RedisTemplateUtil redisTemplateUtil = null;

    private boolean authStatus=false;
    private int openCount;//授权成功次数

    private int retryCount=3;

    @RequestMapping("/prList")
    public String prList()
            throws Exception {
        return "admin/authlog/list";
    }

    @RequestMapping("/keys")
    public String keys(String keyId)
            throws Exception {
        return "admin/authlog/keys";
    }

    @RequestMapping("/list")
    @ResponseBody
    public String list(int page,
                       int rows,
                       String authName,
                       String authStartTime,
                       String authEndTime,
                       String userId,
                       Pager pager)
            throws Exception {
        pager.setCurrentPage(page);
        pager.setPageSize(rows);
        JSONObject datas = new JSONObject();
        List<AuthLog> list = authLogDao.findList(authName,authStartTime,authEndTime,userId,pager);
        datas.put("total", pager.getTotalRow());
        datas.put("rows", list);
        return datas.toString();
    }
    @RequestMapping("/prView")
    public String prView(String id, ModelMap model) {
        AuthLog authLog = authLogDao.find(AuthLog.class, id);
        model.addAttribute("authLog", authLog);
        return "admin/authlog/view";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String add(@ModelAttribute AuthLog authLog,HttpServletRequest request) {
        authStatus=true;
        SessionData.createSyslog(request,5, "开始授权");
        String message = "";
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        try {
            authLog.setId(uuid);
            authLog.setAuthStatus("0");
            authLog.setCreateTime(FormatDate.getYMdHHmmss());
            authLog.setAuthType(1);
            authLogDao.save(authLog);
            SessionData.createSyslog(request,1, "多个在线授权");
            message = StringUtil.jsonValue("1", "添加授权任务成功！");
        } catch (Exception e) {
            e.printStackTrace();
            message = StringUtil.jsonValue("0", AppMsg.ADD_ERROR);
        }
        String adminId= request.getSession().getAttribute("userUUID").toString();
        if(StringUtils.isNotEmpty(authLog.getCollectorId())){
            auth(authLog,adminId,uuid,authLog.getCollectorId());
        }else{
            auth2(authLog,adminId,uuid);
        }
        return message;
    }

    private void auth(AuthLog authLog,String adminId,String uuid,String collectorId){
        //读取控制器
        List<Collectore> collectoreList = collectoreDao.findCollectoreByCollector(collectorId);
        if (collectoreList != null && collectoreList.size() > 0) {
            for (int z = 0; z < collectoreList.size(); z++) {
                Collectore collectore = collectoreList.get(z);
                if(StringUtils.isNotEmpty(collectore.getCeMAC())){
                    String macAddess=collectore.getCeMAC().replace(":","");
                    macAddess="00000000000000000000"+macAddess;
                    if(StringUtils.isNotEmpty(authLog.getAuthLocksId())) {
                        String [] locks=authLog.getAuthLocksId().split(",");
                        for (int i = 0; i <locks.length ; i++) {
                            if(StringUtils.isNotEmpty(locks[i])){
                                // String authModel = new AuthModel(new byte[]{5}, AuthModel.AuthorizationKeyX(authLog.getUser().getId(), locks[i], authLog.getAuthKeysId(), FormatDate.dateParse(authLog.getAuthStartTime()), FormatDate.dateParse(authLog.getAuthEndTime()),1,i+1), Constants.LOCK_KEY).toString();//
                                String authModel = new AuthModel(new byte[]{5}, AuthModel.AuthorizationKeyX(authLog.getUser().getId(), locks[i], authLog.getAuthKeysId(), FormatDate.dateParse(authLog.getAuthStartTime()), FormatDate.dateParse(authLog.getAuthEndTime()),1,i+1), Constants.LOCK_KEY).toString();//
                                System.out.println("开始授权！"+FormatDate.getYMdHHmmss());
                               if(authStatus){
                                   String cleatAuthModel = new AuthModel(new byte[]{5}, AuthModel.AuthorizationKeyX(authLog.getUser().getId(), "ffffffffffffffffffffffffffffffff",authLog.getAuthKeysId(),"20160411101525","20990412101527",2,0), Constants.LOCK_KEY).toString();//
                                   auth(macAddess,collectore.getCollector().getCcode(), adminId, cleatAuthModel,uuid,0);//ByteUtil.hexStrToByteArray(ByteUtil.bytesToHex(keys[4].getBytes()))
                                   String  checkTimeAuthModel=new AuthModel(new byte[]{12},AuthModel.toData(12,14),Constants.LOCK_KEY).toString();//校时成功
                                   auth(macAddess,collectore.getCollector().getCcode(), adminId, checkTimeAuthModel,uuid,0);//ByteUtil.hexStrToByteArray(ByteUtil.bytesToHex(keys[4].getBytes()))
                                   authStatus=false;
                               }
                                auth(macAddess,collectore.getCollector().getCcode(), adminId, authModel,uuid,14000);//ByteUtil.hexStrToByteArray(ByteUtil.bytesToHex(keys[4].getBytes()))
                            }
                        }
                    }
                }
            }
        }
    }
private void auth2(AuthLog authLog,String adminId,String uuid){
       //读取采集器
       List<Collector> collectorList=collectorDao.findCollectorByQgdisid(authLog.getDisId());
       if(collectorList!=null&&collectorList.size()>0) {
           for (int j = 0; j < collectorList.size(); j++) {
               Collector collector = collectorList.get(j);
               auth(authLog,adminId,uuid,collector.getId());
           }
       }
   }
    private void  auth(String macAddess,
                       String collectorId,
                       String adminId,
                       String authModel,
                       String uuid,
                       int time){
        redisTemplateUtil = new RedisTemplateUtil(redisTemplate);
        DataProtocol dataProtocol=new DataProtocol(new byte[]{00,01}, ByteUtil.hexToBytes(macAddess),ByteUtil.hexToBytes(authModel));
        JsonDataProtocol jsonDataProtocol=new JsonDataProtocol();
        jsonDataProtocol.setCollectorId(collectorId);
        jsonDataProtocol.setContent(dataProtocol.toString());
        jsonDataProtocol.setDataType("client");
        String authKey= JSON.toJSONString(jsonDataProtocol)+";"+adminId;
        redisTemplateUtil.setList("lanya-lite", authKey);
        Object o =null;
        int value=time;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(value>0){
            value=value-500;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            o = redisTemplateUtil.get(authKey);
            if(o!=null){
                retryCount=0;
                if (o.toString().indexOf("授权成功") > -1) {
                    openCount++;
                    System.out.println("第二次授权开始");
                    AuthLog authLog = authLogDao.find(AuthLog.class, uuid);
                    authLog.setAuthStatus("1");
                    authLog.setAuthIndex(openCount);
                    authLogDao.update(authLog);
                }
                value=0;
            }
        }
        if(o==null){
            retryCount--;
            if(retryCount>0)
            auth(macAddess, collectorId, adminId, authModel,uuid,time);
        }
    }
}