package com.amoxu.util;

public final class StaticEnum {
    public final static String OPT_SUCCESS = "操作成功";
    public final static String OPT_ERROR = "操作失败";
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

}
