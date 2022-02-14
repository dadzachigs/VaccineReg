package co.zw.trigonsolutes.vaccinereg.bookings;

public class BookingsModel {

    public String date;
    public String id;
    public String vaccCenter;


    public BookingsModel() {
    }

    public BookingsModel(String date, String id, String vaccCenter) {
        this.date = date;
        this.id = id;
        this.vaccCenter = vaccCenter;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVaccCenter() {
        return vaccCenter;
    }

    public void setVaccCenter(String vaccCenter) {
        this.vaccCenter = vaccCenter;
    }

}
