package com.dream.api;

import com.dream.brick.admin.bean.User;
import com.dream.brick.equipment.bean.Keyss;
import com.dream.brick.equipment.dao.IKeyssDao;
import com.dream.util.FormatDate;
import com.dream.util.StringUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 陶乐乐(wangyiqianyi@qq.com)
 * @ClassName: ApiAction.java
 * @Description: 对外接口
 * @date 2018-02-12 下午4:55
 */
@Controller
@Scope("prototype")

@RequestMapping("/api")
public class ApiAction {
	@Resource
	private IKeyssDao ikeyssDao;
	@RequestMapping("keys")
	@ResponseBody
	public String addKeyss(@ModelAttribute Keyss keyss){
		/*Keyss keyss=new Keyss();
		keyss.setKeyssMAC(formParams.get("keyssCode"));
		keyss.setKeyssDate(FormatDate.getYMdHHmmss());
		User user=new User();
		user.setId(formParams.get("userId"));
		keyss.setUser(user);*/
		try {
			keyss.setKeyssDate(FormatDate.getYMdHHmmss());
			ikeyssDao.save(keyss);
			return  StringUtil.jsonValue("1", "添加成功！");
		}catch (Exception e){
			return  StringUtil.jsonValue("0", "添加失败！");

		}
	}
}
