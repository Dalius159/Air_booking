import java.util.ArrayList;
import java.util.Date;

public class InternalFlight extends Flight{
    private double Commissions = 7;
    private int Bonus = 100000;

    //Constructer 
    public InternalFlight(String flightType, String from, String to, Date takeOffDate, String airline, String flightID, double airfares, ArrayList<Booker> bookerList) {
        super(flightType, from, to, takeOffDate, airline, flightID, airfares, bookerList);
    }

    //getter and setter
    public double getCommissions() {
        return Commissions;
    }
    public void setCommissions(double commissions) {
        Commissions = commissions;
    }
    public int getBonus() {
        return Bonus;
    }
    public void setBonus(int bonus) {
        Bonus = bonus;
    }

    //Cập nhật hoa hồng
    public Boolean UpdateCommission (double newCommission, int newBonus){
        setCommissions(newCommission);
        setBonus(newBonus);
        return true;
    }

    @Override
    //Tính tiền hoa hồng của chuyến bay
    public Double getBenefits() {
        return getAirfares() * getCommissions() / 100 + getBonus();
    } 
}
