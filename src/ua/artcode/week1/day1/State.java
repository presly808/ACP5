package ua.artcode.week1.day1;

/**
 *
 */
public enum State {

    NEW(100, "new task"),
    IN_PROGRESS(200, "in progress"),
    DONE(300, "task was finished");

    private int code;
    private String desc;

    State(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static State getByCode(int code){
        for (State s : values()){
            if(s.code == code){
                return s;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString() +
                "State{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                '}';
    }
}
