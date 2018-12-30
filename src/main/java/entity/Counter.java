package entity;

public class Counter {
    private int userNum;
    private int visitorNum;

    public void setUserNum(int userNum){
        this.userNum = userNum;
    }

    public int getUserNum(){
        return userNum;
    }

    public void setVisitorNum(int visitorNum){
        this.visitorNum = visitorNum;
    }

    public int getVisitorNum(){
        return visitorNum;
    }
}
