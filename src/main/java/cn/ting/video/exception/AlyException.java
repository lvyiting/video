package cn.ting.video.exception;

public class AlyException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    //错误码
    private Integer num;

    public AlyException(Integer num, String message) {
        super(message);
        this.num = num;
    }

    public Integer getI() {
        return num;
    }

    public void setI(Integer num) {
        this.num = num;
    }
}
