package Model;

public class Families {
    String mealName,femaleName,kids,address,phone,date,time;

    public Families() {
    }

    public Families(String mealName, String femaleName, String kids, String address, String phone, String date, String time) {
        this.mealName = mealName;
        this.femaleName = femaleName;
        this.kids = kids;
        this.address = address;
        this.phone = phone;
        this.date = date;
        this.time = time;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getFemaleName() {
        return femaleName;
    }

    public void setFemaleName(String femaleName) {
        this.femaleName = femaleName;
    }

    public String getKids() {
        return kids;
    }

    public void setKids(String kids) {
        this.kids = kids;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}
