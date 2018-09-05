package com.foreveross.webbase.common.enums;

/**
 * Mtqq业务类型枚举
 */
public enum MqttbusiTypeEnum {

	 MENU_SKU_PUSH("0","门店菜品库存推送"),
     ORDER_CTRATE("1","创建订单"),
     ORDER_QUERY("2","订单查询"),
     QRCODE_QUERY("3","二维码查询"),
     ARRIVAL_STORE("4", "到店通知"),
	 ORDER_STATUS("5", "自助点餐机订单状态"),
	 ORDER_TAKEFOODCODE("6", "自助点餐机取餐码"),
	 SYNC_STATISTICSTASKINFO("7", "同步统计任务信息"),
	 SYNC_STATISTICSDEVICEINFO("8", "同步统计设备信息");
	 
    private String state; //状态
    private String describe; //描述



    MqttbusiTypeEnum(String state, String describe) {
        this.state = state;
        this.describe = describe;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    /**
     * 根据状态获取描述
     * @param state
     * @return
     */
    public static String getDesc(String state){
        for(MqttbusiTypeEnum outstockStateEnum : MqttbusiTypeEnum.values()){
            if(outstockStateEnum.getState().equals(state)){
                return outstockStateEnum.describe;
            }
        }
        return "";
    }

}