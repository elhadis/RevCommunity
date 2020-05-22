package Model;

public class Houses {
    String place,description,price,houseimage,date,time;

    public Houses() {
    }

    public Houses(String place, String description, String price, String houseimage, String date, String time) {
        this.place = place;
        this.description = description;
        this.price = price;
        this.houseimage = houseimage;
        this.date = date;
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getHouseimage() {
        return houseimage;
    }

    public void setHouseimage(String houseimage) {
        this.houseimage = houseimage;
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
