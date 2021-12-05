package cn.ting.video.exception;

import lombok.Data;

@Data
public class ApiResponse {

    private int code;
    private String msg;
    private Object data;

    public ApiResponse(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}

