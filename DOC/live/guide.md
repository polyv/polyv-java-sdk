this is guide page



## 标题1

````java
  private Object data;
    
    public PolyvLiveCommonResult(int code, String status, String message, Object data) {
        this.setData(data);
        super.code = code;
        super.status = status;
        super.setMessage(message);
    }
    
    public Object getData() {
        return this.data;
    }
    
    public void setData(Object data) {
        this.data = "".equals(data) ? "null" : data;
    }
    
    @Override
    public String toString() {
        return "PLLiveCommonResult{" + "data=" + data + ", code=" + code + ", status='" + status + '\'' +
                ", message='" + message + '\'' + '}';
    }
````

