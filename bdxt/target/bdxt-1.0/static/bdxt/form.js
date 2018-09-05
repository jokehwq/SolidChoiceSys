$(function(){
	$.validator.addMethod("compareDate",function(value,element,params){
	    var starttime = value.replace(/-/g,"/");
	    var endtime =$("input[name=endtime]").val();
        if(endtime.trim()==''){
        	return true;
        }else{
        	var endtime =$("input[name=endtime]").val().replace(/-/g,"/");
        	starttime = new Date(starttime);
        	endtime =  new Date(endtime);
        	if(starttime.getTime()>endtime.getTime()){
        		return false;
        	}
        }
        return true;
},"<font>开始日期不能大于结束日期</font>");
	
	$.validator.addMethod("ismobile",function(value,element,params){
		var length = value.length;
		var mobile = /^0?1[3|4|5|8][0-9]\d{8}$/;
		if(mobile.test(value)|| value==''){
		   return true;	
		}else{
			var phone = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
			if(phone.test(value)){
				return true;
			}
			return false;
		}
	},"<font>请输入正确的联系电话</font>");
	
	// 只能输入英文
	$.validator.addMethod("english", function(value, element) {
		
		 if(value!=''){
		    var re = new RegExp("[a-zA-Z]");
		    len1=re.test(value);
		    re = new RegExp("((?=[\x21-\x7e]+)[^A-Za-z0-9])");
		    len2=re.test(value);
		    if(len1 || len2){
		        return true;
		    }else{
		    	return false;
		    }
		 }
		 return true;
/*	    var chrnum = /^([a-zA-Z]+)$/;
	    return this.optional(element) || (chrnum.test(value));*/
	}, "<font>只能输入英文</font>");
	
	//不能含有汉字
	$.validator.addMethod("noChinese", function(value, element) {
	   var reg = /^[u4E00-u9FA5]+$/;
	      if(reg.test(value)){
	       return true;
	      }
	      return false;
	},"<font>不能含有汉字</font>");
	
})