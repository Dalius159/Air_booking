import java.util.Date;
import java.util.ArrayList;

public class Flight{
    private String FlightType;
    private String From;
    private String To;
    private Date TakeOffDate;
    private String Airline;
    private String FlightID;
    private double Airfares;
    private ArrayList<Booker> BookerList;
    
    //Constructer
    public Flight(String flightType, String from, String to, Date takeOffDate, String airline, String flightID, double airfares, ArrayList<Booker> bookerList) {
        FlightType = flightType;
        From = from;
        To = to;
        TakeOffDate = takeOffDate;
        Airline = airline;
        FlightID = flightID;
        Airfares = airfares;
        BookerList = bookerList;
    }

    //getter and setter
    public String getFlightType() {
        return FlightType;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public String getFlightID() {
        return FlightID;
    }

    public void setFlightID(String fligthID) {
        FlightID = fligthID;
    }

    public String getAirline() {
        return Airline;
    }

    public void setAirline(String airline) {
        Airline = airline;
    }

    public Date getTakeOffDate() {
        return TakeOffDate;
    }

    public void setTakeOffDate(Date date) {
        TakeOffDate = date;
    }

    public Double getAirfares() {
        return Airfares;
    }

    public void setAirfares(double airfares) {
        Airfares = airfares;
    }

    public ArrayList<Booker> getBookerList() {
        return BookerList;
    }

    public void setBookerList(ArrayList<Booker> bookerList) {
        BookerList = bookerList;
    }

    //Cập nhật hoa hồng
    public Boolean UpdateCommission (double newCommission){
        return true;
    }

    //Thêm người đặt chuyến bay
    public void AddBooker(Booker booker){
        ArrayList<Booker> list = getBookerList();
        list.add(booker);
        setBookerList(list);
    }

    //Tìm người đặt chuyến bay trong danh sách bằng số căn cước công dân
    public Booker FindBooker(String ID){
        for(Booker booker: getBookerList()){
            if(booker.getCitizenID().equalsIgnoreCase(ID)){
                booker.setCitizenID(ID);
                return booker;
            }
        }
        return null;
    }

    //Thay thế một người đặt chuyến bay trong danh sách
    public void ReplaceBooker(String ID,Booker newBooker){
        getBookerList().set(getBookerList().indexOf(FindBooker(ID)), newBooker);
    }

    //xóa một người trong danh sách đặt vé
    public void RemoveBooker(Booker booker){
        ArrayList<Booker> list = getBookerList();
        list.remove(booker);
        setBookerList(list);
    }
    
    //Tính tiền hoa hồng của chuyến bay
    public Double getBenefits(){
        return 0D;
    }
}
