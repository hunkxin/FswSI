package com.yike.utility;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonOp {
	public static String byjson(String para){
		JSONObject result = new JSONObject();
		int status = 1;
		int errCode = 0;
		String errMsg = "";
		try {
			if (para.trim().length() > 0){
				JSONObject r = JSONObject.parseObject(para);
				//System.out.println(r.get("cmdtype"));
				JSONArray g = r.getJSONArray("cmdtype");
				JSONArray res = new JSONArray();
				for(int i =0;i<g.size();i++){
					JSONObject sres = new JSONObject();
					String cmd = g.getJSONObject(i).getString("cmd");
					sres.put("cmd", cmd);
					sres.put("ctt", runcmd(cmd.indexOf("_fs_")>0,cmd));
					res.add(sres);
				}
				result.put("res", res);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			status = 0;
			errCode = -1;
			errMsg = e.getLocalizedMessage();
		}
		result.put("st", status);
		result.put("code", errCode);
		result.put("err", errMsg);
		
		return result.toJSONString();
	}
	
	protected static JSONArray runcmd(boolean isfsw, String cmd) throws Exception {
		JSONArray g = new JSONArray();
		ArrayList<String> ctt = LinuxCMD.runCommand(isfsw, cmd);
		for(int i=0;i<ctt.size();i++){
			JSONObject sres = new JSONObject();
			sres.put("index", i);
			sres.put("ctt", ctt.get(i));
			g.add(sres);
		}
		return g;
	}
}
