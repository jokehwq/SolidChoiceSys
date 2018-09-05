package com.foreveross.webbase.weixin.sdk.vo.message;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class GroupMessage {
	
	private boolean is_to_all;
	
	private String groupid;
	
	private String mediaid;
	
	private String msgtype;
	
	private String Content;
	
	private List<String> touser;
	
	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	
	public boolean isIs_to_all() {
		return is_to_all;
	}

	public void setIs_to_all(boolean is_to_all) {
		this.is_to_all = is_to_all;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getMediaid() {
		return mediaid;
	}

	public void setMediaid(String mediaid) {
		this.mediaid = mediaid;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public List<String> getTouser() {
		return touser;
	}

	public void setTouser(List<String> touser) {
		this.touser = touser;
	}
	
	public String buildWeiXinJson(){
		  StringBuilder sb = new StringBuilder();
		  if(null!=touser && touser.size()>1){
			  sb.append("\"touser\":");
			  sb.append(JSONObject.toJSONString(touser));
	          sb.append(",");
		  }else{
		  sb.append("{");
		  sb.append("\"filter\":{");
		  sb.append("\"is_to_all\":");
		  sb.append(isIs_to_all());
		  sb.append(",");
		  sb.append("\"group_id\":");
		  sb.append(getGroupid());
		  sb.append("},");
		  }
		  if(getMsgtype().equals("text")){
		  sb.append("\"text\":{");
		  sb.append("\"content\":");
		  sb.append("\""+getContent()+"\"");
		  }else{
			  sb.append("\""+getMsgtype()+"\":{");
			  sb.append("\"media_id\":");
			  sb.append("\""+getMediaid()+"\"");
		  }
		  sb.append("},");
		  sb.append("\"msgtype\":");
		  sb.append("\""+getMsgtype()+"\"");
		  sb.append("}");
		  return sb.toString();
	}
}
