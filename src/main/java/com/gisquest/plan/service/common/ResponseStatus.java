package com.gisquest.plan.service.common;

/**
 * @创建人 lull
 * @创建日期 ResponseStatus
 * @描述
 */
public enum ResponseStatus {
    COMMON_OK(0, "成功"),
    COMMON_ARGS_WRONG(1, "参数错误"),
    COMMON_FAIL(3, "失败"),
    COMMON_TITLE_ERR(7, "重名不可用"),
    COMMON_CATE_NOT_NULL(2, "目录非空"),
    COMMON_TBL_DEL_ERR(5, "库表删除失败"),
    COMMON_ALG_REG_ERR(6, "资源不包含指定算法，注册失败"),
    COMMON_ALG_REG_TYPE_ERR(8, "资源类型错误"),
    COMMON_HDFS_ERR(9, "hdfs连接失败"),
    COMMON_MOUDLE_LOCKED(10, "模型处于锁定状态，无法使用"),
    COMMON_REMOTE_ERR(11, "调用参数名称和类型与模型不一致"),
    COMMON_REMOTE_WRONG(12, "远程调用失败"),
    COMMON_SSH_WRONG(4, "远程调用shell错误"),
    COMMON_MODLE_DELETE(13, "模型删除失败，模型已提交或者运行中，不允许删除"),
    COMMON_MODLE_BYTE_ERROR(14, "参数转化异常"),
    COMMON_AZKABANSCHEDULEERROR(15, "azkaban调度失败"),
    COMMON_AZKABANCRONERROR(16, "cron表达式错误，调度日期内容异常"),
    COMMON_AZKABANSCHEDULCRONEERROR(17, "调度数据异常"),
    COMMON_FLOWKILLERROR(18, "azkaban流程已经结束"),
    COMMON_FLOWLOSEERROR(19, "azkaban流程已经过期"),
    COMMON_AZKABANLOGINERROR(20, "azkaban登陆异常"),
    COMMON_AZKABANERROR(21, "azkaban接口异常"),
    COMMON_AZKABANUNZIPERROR(22, "azkaban上传失败"),
    COMMON_AZKABANCONERROR(23, "azkaban连接数据库失败");
    private final int value;
    private final String reasonPhrase;

    private ResponseStatus(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int value() {
        return this.value;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }
}
