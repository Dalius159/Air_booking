import java.util.Date;

public class Booker {
    private String Name;
    private String CitizenID;
    private Date BookingDate;

    //Constructor
    public Booker(String name, String citizenID, Date bookingDate) {
        Name = name;
        CitizenID = citizenID;
        BookingDate = bookingDate;
    }

    //Getter and setter
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCitizenID() {
        return CitizenID;
    }

    public void setCitizenID(String citizenID) {
        CitizenID = citizenID;
    }

    public Date getBookingDate() {
        return BookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        BookingDate = bookingDate;
    }
}
