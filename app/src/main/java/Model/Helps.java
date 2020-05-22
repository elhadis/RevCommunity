package Model;

public class Helps {
    private String fullName,phone,visa ,date;

    public Helps() {
    }

    public Helps(String fullName, String phone, String visa, String date) {
        this.fullName = fullName;
        this.phone = phone;
        this.visa = visa;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public String getVisa() {
        return visa;
    }

    public void setVisa(String visa) {
        this.visa = visa;
    }
}
