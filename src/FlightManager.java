import java.util.ArrayList;
import java.util.Date;


public class FlightManager {
    private ArrayList<Flight> flights = new ArrayList<>();

    //Constructer
    public FlightManager(ArrayList<Flight> flights) {
        this.flights = flights;
    }
    
    //getter and setter
    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
    }

    //Tạo một chuyến bay mới
    public Boolean createFlight(Flight newFlight){
        flights.add(newFlight);
        return true;
    }
    
    //Tìm chuyến bay theo số hiệu chuyến bay, ngày bay, nơi đi nơi đến
    public Flight getFlight(String flightID, String from, String to, Date date){
        Flight fl = null;
        for(Flight flight: flights){
            if(flight.getFlightID().equalsIgnoreCase(flightID) && flight.getFrom().equalsIgnoreCase(from) && flight.getTo().equalsIgnoreCase(to) && flight.getTakeOffDate().compareTo(date) == 0){
                fl = flight;
                break;
            }
        }
        return fl;
    }
    
    //Tìm các chuyến bay theo thời gian đi, nơi đi, nơi đến
    public ArrayList<Flight> getFlight2(String from, String to, Date date){
        ArrayList<Flight> fl = new ArrayList<>();
        for(Flight flight: flights){
            if(flight.getFrom().equalsIgnoreCase(from) && flight.getTo().equalsIgnoreCase(to) && flight.getTakeOffDate().compareTo(date) == 0){
                fl.add(flight);
                return fl;
            }
        }
        return fl;
    }

    //Tìm các chuyến bay có hoa hồng lớn hơn một số nhập vào
    public ArrayList<Flight> getFlight3(double a){
        ArrayList<Flight> fl = new ArrayList<>();
        for(Flight flight: flights){
            if(flight.getBenefits() > a){
                fl.add(flight);
            }
        }
        return fl;
    }
    
    //Thay thế một chuyến bay bởi một chuyến bay khác
    public void replaceFlight(Flight fl1, Flight fl2){
        flights.set(flights.indexOf(getFlight(fl1.getFlightID(),fl1.getFrom(),fl1.getTo(),fl1.getTakeOffDate())), fl2);
    }

    //Xóa một chuyến bay
    public Boolean removeFlight(Flight fl){
        flights.remove(fl);
        return true;
    }

    //Tính tổng số hoa hồng phòng vé nhận được từ một khoảng thời gian nhập vào (dd-mm-yyyy) -> (dd-mm-yyyy)
    public Double caculateCommission(Date startDate, Date endDate){
        double benefits = 0;
        for(Flight fl: flights){
            for(Booker booker: fl.getBookerList()){
                if(booker.getBookingDate().compareTo(startDate) >= 0 && booker.getBookingDate().compareTo(endDate) <= 0){
                    benefits+= fl.getBenefits();
                }
            }
        }
        return benefits;
    }

    //Lấy ra tất cả các chuyến bay nội địa hoặc quốc tế
    public ArrayList<Flight> getDomestic_Internal(String a) {
        ArrayList<Flight> List = new ArrayList<>();
        for (Flight fl : flights) {
            if(fl.getFlightType() == a){
                List.add(fl);
            }
        }
        return List;
    }

    //Cập nhật phần trăm hoa hồng cho DomesticFlight
    public Boolean UpdateDomesticCommission(double newCommission){
        ArrayList<Flight> List = getDomestic_Internal("Domestic");
        for(Flight fl: List){
                fl.UpdateCommission(newCommission);
        }
        return true;
    }

    //Cập nhật phần trăm hoa hồng cho InternalFlight
    public Boolean UpdateInternalCommission(double newCommission, int newBonus){
        ArrayList<Flight> List = getDomestic_Internal("Internal");
        for(Flight fl: List){
            InternalFlight fl2 = (InternalFlight) fl;
            fl2.UpdateCommission(newCommission, newBonus);
            fl = (Flight) fl2;
        }
        return true;
    }
}
    
