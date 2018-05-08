package com.amoxu.util;

public final class StaticEnum {

    public final static String PREFIX_ICON = "https://s.gravatar.com/avatar/";

    /*
     * 操作返回值
     * */
    public final static String OPT_SUCCESS = "操作成功";
    public final static String OPT_ERROR = "操作失败";
    public final static String OPT_UNLOGIN = "用户未登录";
    /*用户状态
     * 1	已激活
     * 2	未激活
     * 3	已冻结
     * */
    public final static int STATE_ACTIVATED = 1;
    public final static int STATE_INACTIVATE = 2;
    public final static int STATE_FREZZED = 3;
    /*注册状态
     * 0	注册成功
     * 1	账号已存在
     * 2	邮箱已存在
     * */
    public final static int REG_SUCCESS = 0;
    public final static int REG_ACCOUNT_ERROR = 1;
    public final static int REG_MAIL_ERROR = 2;
    public final static int REG_ERROR = 3;
    /*
     * 用户当前ID
     * */
    public final static int SELF_ID = 0;
    /**
     * 邮件标题
     */
    public final static String MAIL_ACTIVE = "激活邮箱";
    public final static String MAIL_FIND_PASSWORD = "找回密码";
    public final static String MAIL_REGISTER = "注册用户";
    public final static String MAIL_TIMEOFF = "激活超时";
    public final static String MAIL_ACTIVE_SUC = "激活超时";
    public final static String MAIL_VER_ERROR = "验证失败";

    /**
     * 字数长度限制
     */
    public final static String WORD_TOPIC_LENGTH = "字数不少于2个并且不超过32个";


    public static final String EMPTY_WORD = "输入内容不能为空";
}
