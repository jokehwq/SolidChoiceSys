package com.foreveross.webbase.common.enums;

/**
 * 出库状态枚举
 */
public enum OutstockStateEnum {

     DEDUCTED("0","已扣除"),
     PREDEDUCT("1","预扣除"),
     CANCELED("2","已取消");

    private String state; //状态
    private String describe; //描述



    OutstockStateEnum(String state, String describe) {
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
        for(OutstockStateEnum outstockStateEnum : OutstockStateEnum.values()){
            if(outstockStateEnum.getState().equals(state)){
                return outstockStateEnum.describe;
            }
        }
        return "";
    }
}