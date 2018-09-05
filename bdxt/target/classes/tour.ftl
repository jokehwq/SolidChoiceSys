<krpano version="1.19" title="${ph.name}">

	<include url="skin/vtourskin.xml" />


	<!-- customize skin settings: maps, gyro, thumbnails, tooltips, layout, design, ... -->
	<skin_settings maps="false"
	               maps_type="bing"
	               maps_bing_api_key=""
	               maps_zoombuttons="false"
	               gyro="true"
	               title="true"
	               thumbs="true"
	               thumbs_width="120" thumbs_height="80" thumbs_padding="10" thumbs_crop="0|40|240|160"
	               thumbs_opened="false"
	               thumbs_text="false"
	               thumbs_dragging="true"
	               thumbs_onhoverscrolling="false"
	               thumbs_scrollbuttons="false"
	               thumbs_scrollindicator="false"
	               thumbs_loop="false"
	               tooltips_thumbs="false"
	               tooltips_hotspots="false"
	               tooltips_mapspots="false"
	               loadscene_flags="MERGE"
	               loadscene_blend="BLEND(0.5)"
	               loadingtext="loading..."
	               layout_width="100%"
	               layout_maxwidth.normal="900"
	               layout_maxwidth.mobile=""
	               controlbar_width.normal="-44"
	               controlbar_width.mobile="100%"
	               controlbar_height.normal="38"
	               controlbar_height.mobile="34"
	               controlbar_offset.normal="22"
	               controlbar_offset.mobile="0"
	               controlbar_offset_closed="-40"
	               controlbar_overlap.normal="7"
	               controlbar_overlap.mobile="2"
	               design_skin_images="vtourskin.png"
	               design_bgcolor="0x000000"
	               design_bgalpha="0.5"
	               design_bgborder="0 0xFFFFFF 1.0"
	               design_bgroundedge.normal="9"
	               design_bgroundedge.mobile="1"
	               design_bgshadow="0 0 9 0xFFFFFF 0.5"
	               design_thumbborder_bgborder="4 0xFFFFFF 1.0"
	               design_thumbborder_padding="2"
	               design_thumbborder_bgroundedge="5"
	               design_text_css="color:#FFFFFF; font-family:Arial; font-weight:bold;"
	               design_text_shadow="1"
	               />

	<!--
	    For an alternative skin design either change the <skin_settings> values 
	    from above or optionally include one of the predefined designs from below.
	    Either by removing the 'xml-if-check' from the particular <include> element
	    or by adding e.g. initvar:{design:'flat_light'} to the embedpano() call in
	    the html file:
	-->
	<include url="skin/vtourskin_design_glass.xml"       if="design === 'glass'"       />
	<include url="skin/vtourskin_design_flat.xml"        if="design === 'flat'"        />
	<include url="skin/vtourskin_design_flat_light.xml"  if="design === 'flat_light'"  />
	<include url="skin/vtourskin_design_ultra_light.xml" if="design === 'ultra_light'" />
	<include url="skin/vtourskin_design_117.xml"         if="design === '117'"         />


	<!-- startup action - here the first pano/scene will be loaded -->
	<action name="startup" autorun="onstart">
		if(startscene === null, copy(startscene,scene[0].name));
		loadscene(get(startscene), null, MERGE);
	</action>



			<!-- 自定义地图开始， mapcontainer是一个半透明容器container，keep为true，确保了切换场景时地图不会被移除，mapcontainer是其它子layer的父亲，bgcolor以及bgalpha是颜色和透明度的设定，通过改动align以及xy坐标可以确定地图的位置，通过改变width和height确定该矩形的宽度和高度，-->
	<layer name="mapcontainer" keep="true" type="container" bgcolor="0x000000" bgalpha="0.5" align="lefttop" x="0" y="0" width="264" height="264">
	<!-- map的url属性可以改成我们自己的地图文件，align一定是lefttop，这是为了确定热点位置，其坐标系以左上角为0点，也是为了方便我们通过ps等方法来获取热点的值。-->
		<layer name="map" url="skin/b1map.png" align="top" x="4" y="4" width="prop" height="256" handcursor="false" scalechildren="true">
			<!-- 雷达遮罩部分，确保了雷达的扇形不会超出范围，这里也就是地图map的范围。注意这里也应该是对齐左上角，下面的各种layer通通都是对齐左上角，不然坐标系不统一的话，就很难确定热点的位置 -->
			<layer name="radarmask" type="container" align="lefttop" width="100%" height="100%" maskchildren="true">

				<!-- 雷达插件 zoder=1 在开始时为隐藏，只有激活activetespot这个action时才会显示 zorder为叠放次序 数字越大越靠前 -->
				<layer name="radar" visible="false"
				       url="%SWFPATH%/plugins/radar.swf" alturl="%SWFPATH%/plugins/radar.js"
				       align="lefttop" edge="center" zorder="1"
				       scale="0.3"
				       fillcolor="0xFFFFFF" fillalpha="0.8"
				       linecolor="0xFF0000" linewidth="0.5" linealpha="0.5"
				       headingoffset="0"
				       />

				<!-- 热点 zorder=2，用style来统一处理 所有layer都载入了一个名为spot的style，注意这里spot是由0开始，而不是由1开始的，因此在一般情况下，地图点与场景一一对应，而场景的index是从0开始的，所以我们可以利用这一点提高代码的可读性-->
				<layer name="spot0" style="spot" x="113" y="124"  />
				<layer name="spot1" style="spot" x="94" y="62"  />
				<layer name="spot2" style="spot" x="55"  y="34"   />
				<layer name="spot3" style="spot" x="13" y="58"  />

				<!-- 激活的热点 zorder=3 开始时候隐藏，在这里是一个绿色的地图点， 表示当前的场景-->
				<layer name="activespot" url="skin/vtourskin_mapspotactive.png" scale="0.5" oy="-17" align="lefttop" edge="center" zorder="3" visible="false"/>
			</layer>
		</layer>
	</layer>

<!-- 地图点中相同的代码，都写在了style里面，修改的时候只需要修改style里面的代码，提高了效率。在onclick里，先是用subtxt得出spot后面的数字，也就是index，然后检查是否点击的热点就是当前场景，因为没有必要点击当前场景的热点又载入一遍，如果是点击其他的热点，则载入其他场景，载入场景的loadscene中利用了之前得到的spotid，这样就不用每次都写场景的名字了。-->
     <style name="spot" url="skin/vtourskin_mapspot.png" scale="0.5" oy="-17" align="lefttop" edge="center" zorder="2" onclick="subtxt(spotid,get(name),4,2);if(spotid != scene[get(xml.scene)].index,  
  loadscene(get(scene[get(spotid)].name),null,MERGE,BLEND(1)); );" />  

	<!-- 激活热点 - %1 = 当前雷达的方向值heading -->
	<action name="activatespot">  
    <!-- 因此绿色地图点会在每次激活时替换蓝色普通地图点，因此每次激活之前，首先保证所有蓝色地图点是可见的，如果没有下面这个代码，则会使得上一个场景的蓝色地图点消失，这里用的是一个循环语句-->  
    for(set(i,0),i LT scene.count,inc(i),  
    txtadd(spotname,'spot',get(i));  
    set(layer[get(spotname)].visible, true);  
    );  
    <!-- 将当前热点的坐标复制到雷达和激活点的坐标，直接利用当前场景的index，反过来得到layer的名字，这样就使得这个activetespot的参数只要一个就可以了-->  
    txtadd(spotidnow,'spot',get(scene[get(xml.scene)].index));  
    copy(layer[radar].x, layer[get(spotidnow)].x);  
    copy(layer[radar].y, layer[get(spotidnow)].y);  
    copy(layer[activespot].x, layer[get(spotidnow)].x);  
    copy(layer[activespot].y, layer[get(spotidnow)].y);  
  
    <!-- 将第二个参数赋值到雷达的heading -->  
    set(layer[radar].heading, %1);  
  
        <!-- 显示雷达和绿色激活热点，以及隐藏当前场景的地图点 -->  
    set(layer[radar].visible, true);  
    set(layer[activespot].visible, true);  
    set(layer[get(spotidnow)].visible, false);  
    </action>  


	<#list ph.spaces as space>
		<scene name="scene_${space_index}" title="${space.name}" onstart="activatespot(90)" thumburl="panos/${space_index}.tiles/thumb.jpg" lat="" lng="" heading="">
	
			<view hlookat="0" vlookat="0" fovtype="MFOV" fov="120" maxpixelzoom="2.0" fovmin="70" fovmax="140" limitview="auto" />
	
			<preview url="panos/2kt.tiles/preview.jpg" />
	
			<image>
				<cube url="panos/2kt.tiles/pano_%s.jpg" />
				<mobile>
					<cube url="panos/2kt.tiles/mobile_%s.jpg" />
				</mobile>
			</image>
	
			<!-- place your scene hotspots here -->
	
		</scene>
	</#list>

</krpano>
