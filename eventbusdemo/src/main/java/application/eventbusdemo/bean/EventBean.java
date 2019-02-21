package application.eventbusdemo.bean;

public class EventBean {
    private String msg;

    public EventBean(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "EventBean{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
