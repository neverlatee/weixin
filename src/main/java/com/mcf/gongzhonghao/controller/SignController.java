package com.mcf.gongzhonghao.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mcf.gongzhonghao.util.JsonUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcf.gongzhonghao.util.SignUtil;

/**
 * Created by mengchenfei on 2015/10/10.
 */
@Controller
public class SignController
{
	@RequestMapping("/welcome")
	@ResponseBody
	public String welcome(HttpServletRequest request)
	{
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		if(StringUtils.isBlank(signature)||StringUtils.isBlank(timestamp)||StringUtils.isBlank(nonce)){
			return "不能为空参数";
		}
		if (SignUtil.checkSignature(signature, timestamp, nonce))
		{
			return echostr;
		}
		return "";
	}
}
