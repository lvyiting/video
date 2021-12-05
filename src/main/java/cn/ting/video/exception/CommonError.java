package cn.ting.video.exception;

public interface CommonError {
    public int getErrorCode();

    public String getErrorMsg();

    public CommonError setErrorMsg(String ErrorMsg);
}