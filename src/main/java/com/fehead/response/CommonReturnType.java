package com.fehead.response;

public class CommonReturnType {
    // 返回请求处理结果
    private String status;
    // 返回页数
    private Long page;
    // 返回数据
    private Object data;


    public static CommonReturnType creat(Object result) {
        return CommonReturnType.creat(result, "success");
    }

    public static CommonReturnType creat(Object result, String status) {
        CommonReturnType type = new CommonReturnType();
        type.setData(result);
        type.setStatus(status);

        return type;
    }

    public static CommonReturnType creat(Object result,Long page){
        CommonReturnType type = new CommonReturnType();
        type.setData(result);
        type.setPage(page);
        type.setStatus("success");
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

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }
}
