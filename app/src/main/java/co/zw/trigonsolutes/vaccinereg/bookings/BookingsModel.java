package co.zw.trigonsolutes.vaccinereg.bookings;

public class BookingsModel {

    private String regDate;
    private  String UId;
    private String regCenter;

    public BookingsModel() {
    }

    public BookingsModel(String regDate, String uId, String regCenter) {
        this.regDate = regDate;
        this.UId = uId;
        this.regCenter = regCenter;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getUId() {
        return UId;
    }

    public void setUId(String UId) {
        this.UId = UId;
    }

    public String getRegCenter() {
        return regCenter;
    }

    public void setRegCenter(String regCenter) {
        this.regCenter = regCenter;
    }
}
