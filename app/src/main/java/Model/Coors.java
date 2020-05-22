package Model;

public class Coors {
    String fullName,phone,coorsName,date;

    public Coors() {
    }

    public Coors(String fullName, String phone, String coorsName, String date) {
        this.fullName = fullName;
        this.phone = phone;
        this.coorsName = coorsName;
        this.date = date;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCoorsName() {
        return coorsName;
    }

    public void setCoorsName(String coorsName) {
        this.coorsName = coorsName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
