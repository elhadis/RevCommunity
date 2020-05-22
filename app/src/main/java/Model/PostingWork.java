package Model;

import androidx.recyclerview.widget.RecyclerView;

public class PostingWork {
    private String workType,workCity,workEmployerNum ,date,time;

    public PostingWork() {
    }

    public PostingWork(String workType, String workCity, String workEmployerNum, String date, String time) {
        this.workType = workType;
        this.workCity = workCity;
        this.workEmployerNum = workEmployerNum;
        this.date = date;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWorkCity() {
        return workCity;
    }

    public void setWorkCity(String workCity) {
        this.workCity = workCity;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getWorkEmployerNum() {
        return workEmployerNum;
    }

    public void setWorkEmployerNum(String workEmployerNum) {
        this.workEmployerNum = workEmployerNum;
    }
}
