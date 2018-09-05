var app = angular.module("myApp", ['ui.bootstrap','bw.paging']); 
app.controller('WxImageAddCtrl', ['$rootScope', '$scope','$modal','$http', function ($rootScope, $scope,$modal,$http) {
	/*  $scope.material = [];*/
    var media_id; 
    $scope.url = document.location.origin + '/webbase5/a/';
    $scope.currentimgurl = "";
    $scope.currentimgmediaid = "";
    $scope.vm = {};
    $scope.options = {};
    $scope.id="";
    $scope.renderData = function(){
    	var localHref = document.location.href;
    	$scope.accoutid = localHref.substring(localHref.lastIndexOf('=')+1,localHref.length);
    	 if(localHref.indexOf('?id=') > 0){
    	   $scope.id = localHref.substring(localHref.indexOf('?id=')+4,localHref.indexOf('&'));
    	   var data={
    		    	  'accountid':$scope.accoutid,
    		    	  'id':$scope.id
    		    	};
    		   	   $http({
    		           method: "get",
    		           params: data,
    		           url: $scope.url + "wxnewsmsg/wxNewsMsg/getlocal",         
    		       }).success(function(data,header,config,status){
    		      	//响应成功
    		    	   $scope.material = data;
    		    	   $scope.currentIndex = 0;
    		    	   $scope.currentMate = $scope.material[0];
    		       }).error(function(data,header,config,status){
    		           //处理响应失败  		    	  
    		       });	
    	 }else{
    		 $scope.material = [{
    			"author": "",
	            "content": "",
	            "thumbMediaUrl": "",
	            "title": "",
	            "mediaid":"",
	            "contentSourceUrl": "",
		        "showCoverPic": 0,
		        "digest":"",
		        "url":"",
    	      }];
    		 $scope.currentIndex = 0;
    		 $scope.currentMate = $scope.material[0];
    	 }
    	   	 	 
    };
    $scope.renderData();
//	$scope.editor = CKEDITOR.replace( 'editor1',
//		     {
//		      skin : 'kama',
//		      language : 'zh-cn',
//		      toolbarCanCollapse :'false',
//		      resize_enabled:'true',
//		     });
/*       $scope.editor = UE.getEditor('editor1', {
        initialFrameWidth: '100%',
        //设置编辑器宽度
        initialFrameHeight: 200,
        //设置编辑器高度
        scaleEnabled: true,
        wordCount:false, //是否开启字数统计
        // maximumWords:1000, //允许的最大字符数 
        // UE.getEditor('editor').getContent()
        });           
       
	  $scope.editor.on('contentChange', function() {
			  var contentData = $scope.editor.getContent();//内容
			  $scope.currentMate.content = contentData;

		  });*/
	  $scope.addMaterial = function () {
        var temp = {
            "author": "",
            "content": "",
            "thumbMediaUrl": "",
            "title": "",
            "mediaid":"",
            "contentSourceUrl": "",
	        "showCoverPic": 0,
	        "digest":"",
        };
        if ($scope.material.length < 8) {
            $scope.material.push(temp);
        } else {
        	top.$.jBox.tip('最多添加8条', 'info');
        }
    };
   
    $scope.removeMaterial = function (index) {
        $scope.material.splice(index, 1);
    };
    $scope.upMove= function (index) {
        var preitem = {};
        preitem = $scope.material[index-1]
        $scope.material[index-1] = $scope.material[index];
        $scope.material[index] = preitem;
    };
    $scope.downMove= function (index) {
    	 var nextitem = {};
    	 nextitem = $scope.material[index+1]
         $scope.material[index+1] = $scope.material[index];
         $scope.material[index] = nextitem;     
    };
    $scope.selectMaterial = function (index) {
  /*      if ($scope.editor && $scope.editor.getContent() && $scope.currentMate) {
            $scope.currentMate.content = $scope.editor.getContent();
        }*/
        $scope.currentIndex = index;
        $scope.currentMate = $scope.material[index];

    };
   /* $scope.$on("$destroy", function () {
    	$scope.editor.destroy();
    });*/
 /*   $scope.$watchCollection('currentMate', function (newVal, oldVal) {
        if (newVal) {
            if ($scope.editor && $scope.editor.body) {
            	$scope.editor.setContent($scope.currentMate.content);
            } else {
                	setTimeout(function () {
                		$scope.editor.setContent($scope.currentMate.content);
                    }, 1000)
            }

        }
    });*/
   
    $scope.wxpicSave = function(){
    	
    	//最后一个文章的内容
   /* 	if ($scope.editor && $scope.editor.getContent) {
            $scope.currentMate.content = $scope.editor.getContent();
        }*/
    	//验证是否为空
    	if($scope.id){
    	var able_to_add = true;
        angular.forEach($scope.material, function (item, key) {
            var title = $.trim(item.title);
            var thumbMediaUrl = $.trim(item.thumbMediaUrl);
            var Url = item.url;
            if (!title) {
                able_to_add = false;
                top.$.jBox.tip('第' + (key + 1) + '条图文标题为空', 'info');               
            }
            if (!thumbMediaUrl) {
                able_to_add = false;
                top.$.jBox.tip('第' + (key + 1) + '条封面图片空', 'info');              
            }
            if (!isURL(Url)) {
                able_to_add = false;
                top.$.jBox.tip('第' + (key + 1) + '条请填写正确的跳转链接', 'info');              
            }
        });

        if (!able_to_add) {
            return
        }
    	}
    	console.log($scope.material);
    	$scope.saving = true;
		var data={
    	  'accountId':$scope.accoutid,
    	  'msgid':$scope.id,
    	  'material':$scope.material
    	};
   	   $http({
           method: "post",
           data: data,
           url: $scope.url + "wxnewsmsg/wxNewsMsg/savelocal",         
       }).success(function(data,header,config,status){
      	//响应成功
    	   $scope.saving = false;
    	   top.$.jBox.tip('保存成功！', 'info');
    	   document.location.href = $scope.url + 'wxnewsmsg/wxNewsMsg/locallist?accountid='+$scope.accoutid;
       }).error(function(data,header,config,status){
           //处理响应失败
    	   $scope.saving = false;
    	   top.$.jBox.tip('网络出错,请稍候再试...', 'info');
       });	
    };
    $scope.refreshList= function(param){
    	loading('请稍等...');
     	$scope.imgPageNo = param.page;
     	var data={
          	  'accountId':$scope.accoutid,
          	  'pageNo':$scope.imgPageNo
          	};
     	 $http({
             method: "get",
             params: data,//Form Data = {"id":1,"value":"hello"}
             url: $scope.url + "weixin/wxMaterialNews/queryImages",
             
         }).success(function(data,header,config,status){
        	//响应成功
        	 closeLoading();
        	 $scope.vm.imgListData = data;

         }).error(function(data,header,config,status){
             //处理响应失败
         });
          };
        
    $scope.openPhotoModal = function(){
    	$scope.currentimgurl = "";
 	    $scope.currentimgmediaid = "";
    	$('.msgPop').show();
    	$('html').css('overflow','hidden');
    	$scope.vm.imgListData.pageNo = 0;
    	$scope.refreshList({page:1});
    };
//   $scope.refreshList({page:1});
 
   $scope.slectImg = function(i){
		$(".wx-photo ul li").eq(i).addClass("active").siblings().removeClass("active");
		$scope.currentimgurl = $scope.vm.imgListData.images[i].thumbMediaUrl;
		$scope.currentimgmediaid = $scope.vm.imgListData.images[i].mediaId;
   };
   $scope.picSubmit = function(){
	   $scope.currentMate.thumbMediaUrl = $scope.currentimgurl;
	   $scope.currentMate.mediaid = $scope.currentimgmediaid;
	   $scope.close();
   };
    $scope.close = function(){
    	$('.msgPop').hide();
    	$('html').css('overflow','auto');
	};
	
}]);
app.controller('PhotoModalInstanceCtrl', ['$rootScope', '$scope','$modalInstance', function ($rootScope, $scope,$modalInstance) {
	$scope.close = function(){
		$modalInstance.close();
	};
}]);

function isURL(str_url) {// 验证url
	var strRegex = "^((https|http|ftp|rtsp|mms)?://)"
	+ "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" // ftp的user@
	+ "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
	+ "|" // 允许IP和DOMAIN（域名）
	+ "([0-9a-z_!~*'()-]+\.)*" // 域名- www.
	+ "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名
	+ "[a-z]{2,6})" // first level domain- .com or .museum
	+ "(:[0-9]{1,4})?" // 端口- :80
	+ "((/?)|" // a slash isn't required if there is no file name
	+ "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
	var re = new RegExp(strRegex);
	return re.test(str_url);
	}
