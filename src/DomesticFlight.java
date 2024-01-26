import java.util.ArrayList;
import java.util.Date;

public class DomesticFlight extends Flight{
    private double Commissions = 5;
    
    //Constructer 
    public DomesticFlight(String flightType, String from, String to, Date takeOffDate, String airline, String flightID, double airfares, ArrayList<Booker> bookerList) {
        super(flightType, from, to, takeOffDate, airline, flightID, airfares, bookerList);
    }

    //getter and setter
    public double getCommissions() {
        return Commissions;
    }

    public void setCommissions (double commissions) {
        Commissions = commissions;
    }

    //Cập nhật hoa hồng
    public Boolean UpdateCommission (double newCommission){
        setCommissions(newCommission);
        return true;
    }

    @Override
    //Tính tiền hoa hồng của chuyến bay
    public Double getBenefits() { 
        return getAirfares() * getCommissions() / 100;
    } 
}
