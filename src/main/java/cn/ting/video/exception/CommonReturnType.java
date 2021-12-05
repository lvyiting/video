package cn.ting.video.exception;

public class CommonReturnType {
    //表明对应请求的返回处理结果:success fail
    private String status;

    //当status为success时，则返回前端所需要的数据
    private Object data;

    private Integer error;

    //定义一个通用的创建方法
    public static CommonReturnType create(Object result) {
        return CommonReturnType.create(200,result,"success");
    }

    public static CommonReturnType create(int error,Object result, String status) {
        CommonReturnType type = new CommonReturnType();
        type.setData(result);
        type.setStatus(status);
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
