  		var localObj = window.location;
		var contextPath = localObj.pathname.split("/")[1];
		var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;
		var imagePath = basePath +"/static/images/photo_icon.png"
       //图片上传预览    IE是用了滤镜。
        function previewImage(file,width,height)
        {
          var div = document.getElementById('preview');
          if (file.files && file.files[0])
          { 
        	  if($('#filepath').val()!=''){
        			 div.innerHTML ='<img id=imghead src='+basePath+"/a/system/file/sysFile/download?id="+$('#filepath').val()+' onclick=$("#previewImg").click() "width=300px" />';
        	  }else{
                     div.innerHTML ='<img id=imghead src='+imagePath+' onclick=$("#previewImg").click() />';
        	  }        	  
        	  var img = document.getElementById('imghead');
              img.onload = function(){
                img.style.marginLeft = '20px';
              }
              var reader = new FileReader();
              reader.onload = function(evt){
            	  if(!validateImage(file,evt.target.result)){
                  	$(img).width(300);
                    $(img).height(300);
            		  return; 
            	  }else
                  	    $(img).width(width);
                        $(img).height(height);
            	        img.src = evt.target.result;
            	  }
              reader.readAsDataURL(file.files[0]);
          }
/*          else //兼容IE
          {
            var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
            file.select();
            var src = document.selection.createRange().text;
            div.innerHTML = '<img id=imghead>';
            var img = document.getElementById('imghead');
            img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
            div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
          }*/
        }
        
        function clacImgZoomParam( maxWidth, maxHeight, width, height ){
            var param = {top:0, left:0, width:width, height:height};
            if( width>maxWidth || height>maxHeight ){
                rateWidth = width / maxWidth;
                rateHeight = height / maxHeight;
                
                if( rateWidth > rateHeight ){
                    param.width =  maxWidth;
                    param.height = Math.round(height / rateWidth);
                }else{
                    param.width = Math.round(width / rateHeight);
                    param.height = maxHeight;
                }
            }
            param.left = Math.round((maxWidth - param.width) / 2);
            param.top = Math.round((maxHeight - param.height) / 2);
            return param;
        } 
        
        function validateImage(file,src){
        	  src = src.substr(0,src.indexOf(";"));
        	  if(src.indexOf("data:image")==-1){
            	  var image = $(file); 
            	  image.after(image.clone().val(""));      
            	  image.remove();
            	  alertx("不合法的图片格式");
            	  return false;
             }
        	  return true;
        }
     