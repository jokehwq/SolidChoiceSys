package com.foreveross.webbase.common.enums;

/**
 * 套餐完成进度枚举
 * (0,排队中、1,配餐中、2,烹饪中、３,烹饪完成、４,待取餐、５,已取餐)
 */
public enum PackageProgressEnum {

    QUEUE("0","排队中"),
    PREPARE("1","配餐中"),
    COOKINGSTART("2","烹饪中"),
    COOKINGEND("3","烹饪完成"),
    PRETAKEFOOD("4","待取餐"),
    TAKEFOODCOMPLETE("5","已取餐");

    private String state; //状态
    private String describe; //描述



    PackageProgressEnum(String state, String describe) {
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
        for(PackageProgressEnum outstockStateEnum : PackageProgressEnum.values()){
            if(outstockStateEnum.getState().equals(state)){
                return outstockStateEnum.describe;
            }
        }
        return "";
    }
}