/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.system.sys.utils;

import com.foreveross.webbase.bdxt.dao.BdxtAdMaterialDao;
import com.foreveross.webbase.bdxt.dao.BdxtDictDao;
import com.foreveross.webbase.bdxt.dao.BdxtDistrictDao;
import com.foreveross.webbase.bdxt.entity.BdxtAdMaterial;
import com.foreveross.webbase.bdxt.entity.BdxtDict;
import com.foreveross.webbase.bdxt.entity.BdxtDistrict;
import com.foreveross.webbase.common.mapper.JsonMapper;
import com.foreveross.webbase.common.utils.CacheUtils;
import com.foreveross.webbase.common.utils.SpringUtil;
import com.foreveross.webbase.system.sys.dao.DictDao;
import com.foreveross.webbase.system.sys.entity.Dict;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * 字典工具类
 * @author ThinkGem
 * @version 2013-5-29
 */
public class DictUtils {
	
	public static final String CACHE_DICT_MAP = "dictMap";
	public static final String CACHE_BDXT_DICT_MAP = "bdxtdictMap";
	public static final String CACHE_BDXT_DISTRICT_MAP = "bdxtdistrictMap";
	private static DictDao dictDao = SpringUtil.getBean(DictDao.class);
	private static BdxtDictDao bdxtDictDao = SpringUtil.getBean(BdxtDictDao.class);
	private static BdxtAdMaterialDao bdxtAdMaterialDao = SpringUtil.getBean(BdxtAdMaterialDao.class);
	private static BdxtDistrictDao bdxtDistrictDao = SpringUtil.getBean(BdxtDistrictDao.class);

	public static String getDictLabel(String value, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)){
			for (Dict dict : getDictList(type)){
				if (type.equals(dict.getType()) && value.equals(dict.getValue())){
					return dict.getLabel();
				}
			}
		}
		return defaultValue;
	}
	
	public static String getDictLabels(String values, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(values)){
			List<String> valueList = Lists.newArrayList();
			for (String value : StringUtils.split(values, ",")){
				valueList.add(getDictLabel(value, type, defaultValue));
			}
			return StringUtils.join(valueList, ",");
		}
		return defaultValue;
	}

	public static String getDictValue(String label, String type, String defaultLabel){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)){
			for (Dict dict : getDictList(type)){
				if (type.equals(dict.getType()) && label.equals(dict.getLabel())){
					return dict.getValue();
				}
			}
		}
		return defaultLabel;
	}

	//查询父级字典信息
	public static List<BdxtDict> getParentList(){
       return bdxtDictDao.queryParentInfo();
	}


    //根据code与name查询字典信息
	public static List<BdxtDict> getBdxtDictList(String code,String name){
		@SuppressWarnings("unchecked")
		Map<String, List<BdxtDict>> dictMap = (Map<String, List<BdxtDict>>)CacheUtils.get(CACHE_BDXT_DICT_MAP+code);
		if (dictMap==null){
			dictMap = Maps.newHashMap();
			dictMap.put(code, bdxtDictDao.queryDictListByCondition(code, name));
			CacheUtils.put(CACHE_BDXT_DICT_MAP+code, dictMap);
		}
		List<BdxtDict> dictList = dictMap.get(code);
		if (dictList == null){
			dictList = Lists.newArrayList();
		}
		return dictList;
	}

	//根据code与name查询区域信息
	public static List<BdxtDistrict> getDistrictList(String code,String name){

		@SuppressWarnings("unchecked")
		List<BdxtDistrict> districtList  = Lists.newArrayList();
		//添加一个中国地区
		/*BdxtDistrict china = new BdxtDistrict();
		china.setId("0");
		china.setDistrictcode("3000336");
		china.setLevel(0);
		china.setDistrictname("中国");
		districtList.add(china);*/

		Map<String, List<BdxtDistrict>> districtMap = (Map<String, List<BdxtDistrict>>)CacheUtils.get(CACHE_BDXT_DISTRICT_MAP);
		if (districtMap==null){
			districtMap = Maps.newHashMap();
			districtMap.put(code, bdxtDistrictDao.queryDistrictListByCondition(code, name));
			CacheUtils.put(CACHE_BDXT_DISTRICT_MAP, districtMap);
		}
		List<BdxtDistrict> temp = districtMap.get(code);
		districtList.addAll(temp);
		return districtList;
	}
	//查询所有广告素材信息
	public static List<BdxtAdMaterial> getAdList(){

		BdxtAdMaterial bdxtAdMaterial = new BdxtAdMaterial();
		bdxtAdMaterial.setStatus(1);
		List<BdxtAdMaterial> allList = bdxtAdMaterialDao.findList(bdxtAdMaterial);
		return allList;
	}

	public static List<Dict> getDictList(String type){
		@SuppressWarnings("unchecked")
		Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>)CacheUtils.get(CACHE_DICT_MAP);
		if (dictMap==null){
			dictMap = Maps.newHashMap();
			Dict d = new Dict();
			//查询资讯的 需要添加属性  value=1 是可见的
			if("bdxt_news".equals(type)){
				d.setValue("1");
			}
			//查询素材类型  value=1 是可见的
			if("bdxt_ad_material".equals(type)){
				d.setValue("1");
			}
			//查广告位置
			if("bdxt_ad_location".equals(type)){
				d.setValue("1");
			}
			//商品分类
			if("bdxt_pro_type".equals(type)){
				d.setValue("1");
			}
			for (Dict dict : dictDao.findAllList(d)){
				List<Dict> dictList = dictMap.get(dict.getType());
				if (dictList != null){
					dictList.add(dict);
				}else{
					dictMap.put(dict.getType(), Lists.newArrayList(dict));
				}
			}
			CacheUtils.put(CACHE_DICT_MAP, dictMap);
		}
		List<Dict> dictList = dictMap.get(type);
		if (dictList == null){
			dictList = Lists.newArrayList();
		}
		return dictList;
	}
	
	/**
	 * 返回字典列表（JSON）
	 * @param type
	 * @return
	 */
	public static String getDictListJson(String type){
		return JsonMapper.toJsonString(getDictList(type));
	}







	
}
