package com.foreveross.webbase.bdxt.web.app.entity.response;

public enum ConstantsEnum {

	/*系统编码----------------------------------------------------------------*/
    /**
     * 系统错误,请稍后重试或者联系管理员
     */
    SYSTEM_ERROR("100000", "系统错误，请稍后重试或者联系管理员"),
    /**
     * 操作成功
     */
    STATUS_SUCCESS("200", "操作成功"),
    /**
     * 操作失败
     */
    STATUS_NO_SUCCESS("500", "操作失败"),
	
	/*登录错误码----------------------------------------------------------------*/
    /**
     * 验证码不正确
     */
    LOGIN_ERROR_CODE("100001", "图形验证码错误，请重新输入"),
    /**
     * 验证码不正确
     */
    LOGIN_ERROR_KAPTCHA_FAILURE_CODE("1000014", "图形验证码已失效，请重新输入"),
    /**
     * 用户名不能为空
     */
    LOGIN_ERROR_USERNAME("100002", "用户名不能为空"),
    /**
     * 密码不能为空
     */
    LOGIN_ERROR_PASSWORD("100003", "密码不能为空"),
    /**
     * 账号或密码错误
     */
    LOGIN_ERROR_UNKOWNACCOUNT("100004", "账号或密码错误"),
    /**
     * 账号未启用
     */
    LOGIN_ERROR_DISABLEACCOUNT("100005", "账号未启用"),
    /**
     * 密码错误
     */
    LOGIN_ERROR_INCORRECTCREDENTIAL("100006", "旧密码错误"),
    /**
     *
     */
     LOGIN_ERROR_LOCKEDACCOUNT("100007","账号已被锁定，请联系管理员"),
    /**
     * 登陆验证uuid异常
     */
    LOGIN_ERROR_UUID_CODE("100008", "登陆验证uuid异常"),
    /**
     * 账号信息异常，请输入正确的信息
     */
    LOGIN_ERROR_PARAM_CODE("100009", "账号信息异常，请输入正确的信息"),
    /**
     * 账号不存在
     */
    LOGIN_ERROR_ACCOUNT_NO_EXIST("100010", "账号不存在"),
    /**
     * 手机号已注册
     */
    LOGIN_ERROR_ACCOUNT_EXIST("100011", "手机号已注册"),
    /**
     * 短信码同号码限制调用
     */
    LOGIN_ERROR_SMS_EXIST("100012", "短信验证码60秒内仅可调用一次"),
    /**
     * 短信码同号码限制调用
     */
    LOGIN_ERROR_SMS_WRONG("100013", "短信验证码错误"),
    
	
	/*用户错误码----------------------------------------------------------------*/
    /**
     * 短信验证码发送成功
     */
    USER_SUCCESS_SMS_CODE("101000","发送短信验证码成功"),
    /**
     * 短信验证码发送失败
     */
    USER_NO_SUCCESS_SMS_CODE("101001","发送短信验证码失败"),
    /**
     * 登录用户名已存在
     */
    USER_ERROR_DUPLICATED_ACCOUNT("101002", "登录用户名已存在，请返回登录"),
    /**
     * 密码不一样
     */
    USER_ERROR_INCORRECT_DIFFER("101003", "两次密码不一致，请重新填写"),
    /**
     * 没有权限
     */
    USER_ERROR_NOPERMISSION("101004", "没有权限"),
    /**
     * 登录用户名不存在
     */
    USER_ERROR_NO_DUPLICATED_ACCOUNT("101005", "该用户不存在"),
    /**
     * 验证码无效
     */
    USER_ERROR_SMS_CODE("101006","验证码无效，请重新获取"),
    /**
     * 验证码不一致
     */
    USER_ERROR_NO_DUPLICATED_SMS_CODE("101007","短信验证码错误"),

    USER_ERROR_BARGIN_RESER_TOTAL("101008","议价报价条数超过最大限制5条"),

    USER_ERROR_QUOTE_RACKET_TOTAL("101009","网拍报价条数超过最大限制3条"),
    //标签----------------------------------------------------------------
    TAG_ERROR_DUPLICATED_NAME("11000","该标签已存在，请重新填写"),
    //活动----------------------------------------------------------------
    ACTIVITY_ERROR_USER_QUOTE_TOTAL("12000","您已申请该活动，不可重复申请"),
    //校验----------------------------------------------------------------
    /**
     * 校验：字段不能为空
     */
    VALIDATOR_BLANK("102001", "数据信息异常"),
    /**
     * 校验：对象校验失败
     */
    VALIDATOR_CLASS("102002", "对象校验失败"),
    /**
     * 校验：非法字符
     */
    VALIDATOR_STRING("102003", "非法字符"),

    STATUS_INPUT_ERROR("102004", "必填参数缺失或不合法"),

    PARAMETER_ERROR("102005", "ParameterException exception异常"),

    UPLOAD_IMG_ERROR("102006", "图片大小不能超过10M"),

    NULL_ERROR("102007", "NullPointerException异常"),
	
	
	/*上传----------------------------------------------------------------*/
    /**
     * 上传失败
     */
    FILE_UPLOAD_ERROR("103001", "上传失败"),

    NON_EXIST_VALUE("102007", "查无数据"),

    UPDATE_INFO_SUCCESS("102009", "更新信息成功"),
    ;


    private String code;
    private String msg;
    ConstantsEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
