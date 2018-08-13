package io.intodream.base.constant;


/**
 * @description 自定义操作返回码
 * @date 2018-04-24,下午11:00
 * @author Jwenk
 * @copyright intoDream.io 筑梦科技
 * @email xmsjgzs@163.com
 */
public enum ResultEnums {

    /**
     * 未知错误
     */
    UNKNOWN_ERROR(0,"未知错误"),

    /**
     * 操作成功
     */
    SUCCESS(200,"操作成功"),

    /**
     * 系统异常
     */
    SYSTEM_EXCEPTION(500,"系统异常"),

    /**
     * 上传文件失败
     */
    UPLOAD_FILE_ERROR(510, "上传文件失败"),

    /**
     * 404异常
     */
    RESOURCE_NOT_EXIST(404,"请求资源不存在"),

    /**
     * 文件为空
     */
    FILE_IS_EMPTY(440,"文件为空"),

    /**
     * 参数错误
     */
    PARAM_ERROR(450, "参数错误"),

    /**
     * 查询结果为空
     */
    RESULT_EMPTY(204, "暂未查询到数据"),

    /**
     * 操作失败
     */
    OPERATION_FAILURE(550, "操作失败"),

    ;

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String message;

    ResultEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
