import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    static ArrayList<Flight> flights = new ArrayList<>();
    static FlightManager FLM = new FlightManager(flights);
    static Scanner sc = new Scanner(System.in);
    static SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
   
    public static void main(String[] args) throws ParseException{
        ArrayList<Booker> bookerList = new ArrayList<>();
        bookerList.add(new Booker("Jame Smith", "001202004415", df.parse("15-07-2022"))); 
        bookerList.add(new Booker("New Anna", "001202004422", df.parse("16-07-2022")));
        bookerList.add(new Booker("Joker", "001202004433", df.parse("17-07-2022")));
        DemoFlight("Domestic", "ha noi", "ho chi minh", df.parse("21-07-2022"), "jetstar", "js1540", 1200000, bookerList);
        DemoFlight("Domestic", "hai phong", "ho chi minh", df.parse("22-07-2022"), "jetstar", "js1541", 1100000, new ArrayList<Booker>());
        DemoFlight("Domestic", "dong hoi", "ha noi", df.parse("23-07-2022"), "bamboo", "vn1340", 1000000, new ArrayList<Booker>());
        DemoFlight("Internal", "Vietnam", "Australia", df.parse("24-07-2022"), "vietjet", "vj2120", 900000, new ArrayList<Booker>());
        DemoFlight("Internal", "Thailan", "Vietnam", df.parse("20-07-2022"), "vietnamAirline", "VN2640", 1300000, new ArrayList<Booker>());
        DemoFlight("Internal", "Laos", "China", df.parse("27-07-2022"), "pacific", "VN2640", 1600000, new ArrayList<Booker>());
        int choose;
        do {
            ShowMenu();
            choose = Integer.parseInt(sc.nextLine());
            switch(choose){
                case 1 -> {
                    createFlight();
                }
                case 2 -> {
                    deleteFlight();
                }
                case 3 -> {
                    updateFlight();
                }
                case 4 -> {
                    searchFlight();
                }
                case 5 -> {
                    fixCommission();
                }
                case 6 -> {
                    CaculateCommission();
                }
                case 7 -> {
                    ShowList();
                }
                case 8 -> {
                    break;
                }
            }
        } while(choose != 8);
    }

    public static void ShowMenu(){
        System.out.println("\n|----------WELCOME----------|");
        System.out.println("    (1> Create Flight <1)");
        System.out.println("    (2> Delete Flight <2)");
        System.out.println("    (3> Update Flight <3)");
        System.out.println("    (4> Search Flight <4)");
        System.out.println("  (5> Update Commission <5)");
        System.out.println(" (6> Caculate Commission <6)");
        System.out.println("      (7> Show List <7)");
        System.out.println("       (8> Exit!!! <8)");
        System.out.println("|---------------------------|");
        System.out.print("--> Please Enter Your Choice(1->8): ");
    }
    
    //Hàm nhập ngày
    public static Date inputDate(){
        Date date;
        while(true){
            try{
                date = df.parse(sc.nextLine());break;
            } catch(ParseException ex){
                System.out.print("invalid! Input again: ");
            }
        }
        return date;
    }
    
    //Tạo một chuyến bay mới
    public static void createFlight(){
        System.out.println("-------------CREATE FLIGHT-------------");
        System.out.print("\nPlease Enter Flight Type(<1>Domestic - <2>Internal): ");
        int flightType;
        while (true) {
            flightType = Integer.parseInt(sc.nextLine());
            if (flightType == 1 || flightType == 2) {
                break;
            }
            else { 
                System.out.print("invalid! Input again: ");
            }
        }
        System.out.print("\nPlease Enter Departure: ");
        String from = sc.nextLine();
        System.out.print("\nPlease Enter Destination: ");
        String to = sc.nextLine();
        System.out.print("\nPlease Enter Date Of Department(dd-mm-yyyy): ");
        Date date = inputDate();
        System.out.print("\nPlease Enter Airline: ");
        String airline = sc.nextLine();
        System.out.print("\nPlease Enter Flight ID: ");
        String flightID = sc.nextLine();
        System.out.print("\nPlease Enter Airfares: ");
        Double airfares;
        while (true) {
            try {
                airfares = Double.parseDouble(sc.nextLine());;
                break;
            } catch (NumberFormatException ex) {
                System.out.print("invalid! Input again: ");
            }
        }
        ArrayList<Booker> bookerList = new ArrayList<>();
        if (flightType == 1) {
            Flight fl = new DomesticFlight("Domestic", from, to, date, airline, flightID, airfares, bookerList);
            DomesticFlight fl2 = (DomesticFlight) fl;
            FLM.createFlight(fl2);
        } else if (flightType == 2) {
            Flight fl = new InternalFlight("Internal", from, to, date, airline, flightID, airfares, bookerList);
            InternalFlight fl2 = (InternalFlight) fl;
            FLM.createFlight(fl2);
        } 
    }

    //Tìm một chuyến bay
    public static Flight findFlight(){
        System.out.print("\nPlease Enter Departure: ");
        String from = sc.nextLine();
        System.out.print("\nPlease Enter Destination: ");
        String to = sc.nextLine();
        System.out.print("\nPlease Enter Date Of Department(dd-mm-yyyy): ");
        Date date = inputDate();
        System.out.print("\nPlease Enter Flight ID: ");
        String flightID = sc.nextLine();
        return FLM.getFlight(flightID, from, to, date);
    }

    //Xóa một chuyến bay
    public static void deleteFlight(){
        System.out.print("\nWhich Flight Do You Want To Delete?: ");
        Flight fl = findFlight();
        if(fl == null){
            System.out.print("\nThis Flight Do Not Exist!");
        }
        else{
            FLM.removeFlight(fl);
            System.out.print("\nDone!");
        }
    }

    //Cập nhật thông tin của một chuyến bay
    public static void updateFlight(){
        System.out.print("\nWhich Flight Do You Want To Update?: ");
        Flight fl = findFlight();
        Flight fl2 = null;
        if(fl == null){
            System.out.println("\nThis Flight Do Not Exist!");
        }
        else{
            System.out.print("\n   (1)_Departure_(1)");
            System.out.print("\n  (2)_Destination_(2)");
            System.out.print("\n (3)_Take Off Date_(3)");
            System.out.print("\n    (4)_Airline_(4)");
            System.out.print("\n   (5)_Flight ID_(5)");
            System.out.print("\n   (6)_Airfares_(6)");
            System.out.print("\n  (7)_Booker List_(7)");
            System.out.print("\nPlease Select Update Field(1->7): ");
            int action = Integer.parseInt(sc.nextLine());
            switch (action) {
                case 1 -> {
                    System.out.print("\nPlease Enter New Departure: ");
                    String newFrom = sc.nextLine();
                    fl.setFrom(newFrom);
                }
                case 2 -> {
                    System.out.print("\nPlease Enter New Destination: ");
                    String newTo = sc.nextLine();
                    fl.setTo(newTo);
                }
                case 3 -> {
                    System.out.print("\nPlease Enter New Date Of Department(dd-mm-yyyy): ");
                    Date newDate = inputDate();
                    fl.setTakeOffDate(newDate);
                }
                case 4 -> {
                    System.out.print("\nPlease Enter New Airline: ");
                    String newAirline = sc.nextLine();
                    fl.setAirline(newAirline);
                }
                case 5 -> {
                    System.out.print("\nPlease Enter New FlightID: ");
                    String newFlightID = sc.nextLine();
                    fl.setFlightID(newFlightID);
                }
                case 6 -> {
                    System.out.print("\nPlease Enter New Airfares: ");
                    Double newAirfares;
                    while (true) {
                        try {
                            newAirfares = Double.parseDouble(sc.nextLine());
                            break;
                        } catch (NumberFormatException ex) {
                        System.out.print("invalid! Input again: ");
                        }
                    }
                    fl.setAirfares(newAirfares);
                }
                case 7 -> {
                    int choose;
                    System.out.print("\nEnter Number: <1>_More Booker   <2>_Update Booker   <3>_Delete Booker");
                    System.out.print("\nYour Choice: ");
                    while (true) {
                        choose = Integer.parseInt(sc.nextLine());
                        if (choose == 1 || choose == 2 || choose == 3) {
                            break;
                        }
                        else { 
                            System.out.print("invalid! Input again: ");
                        }
                    }
                    if(choose == 1){
                        System.out.print("\n Please Enter Booker Name: ");
                        String name = sc.nextLine();
                        System.out.print("\n Please Enter Booker Citizen ID: ");
                        String citizenID = sc.nextLine();
                        System.out.print("\nPlease Enter Booking Date: ");
                        Date date;
                        while(true){
                            date = inputDate();
                            if(date.compareTo(fl.getTakeOffDate()) < 0){
                                break;
                            }
                            else{
                                System.out.print("\nBooking Date Must Be Earlier Than Department Date, Input Again: ");
                            }
                        }
                        fl.AddBooker(new Booker(name, citizenID, date));
                    }
                    else if(choose == 2){
                        System.out.print("\n Please Enter Citizen ID To Find Booker: ");
                        String citizenID = sc.nextLine();
                        Booker booker = fl.FindBooker(citizenID);
                        int choose2;
                        System.out.print("\nEnter Number: <1>_Update Name   <2>_Update Citizen ID   <3>_Update Booking Date");
                        System.out.print("\nYour Choice: ");
                        while (true) {
                            choose2 = Integer.parseInt(sc.nextLine());
                            if (choose2 == 1) {
                                System.out.print("\n Please Enter New Name:");
                                String name = sc.nextLine();
                                booker.setName(name);
                                break;
                            }
                            else if(choose2 == 2){
                                System.out.print("\n Please Enter New Citizen ID:");
                                String ID = sc.nextLine();
                                booker.setCitizenID(ID);
                                break;
                            }
                            else if(choose2 == 3){
                                System.out.print("\nPlease Enter New Booked Date: ");
                                Date newDate;
                                while(true){
                                    newDate = inputDate();
                                    if(newDate.compareTo(fl.getTakeOffDate()) > 0){
                                        break;
                                    }
                                    else{
                                        System.out.print("\nBooked Date Must Be Earlier Than Department Date, Input Again: ");
                                    }
                                }
                                 booker.setBookingDate(newDate);
                                break;
                            }
                            else { 
                            System.out.print("invalid! Input again: ");
                            }
                        }
                        fl.ReplaceBooker(citizenID, booker);
                    }
                    else{
                        System.out.print("\n Please Enter Citizen ID To Find Booker You Want To Delete: ");
                        String citizenID = sc.nextLine();
                        fl.RemoveBooker(fl.FindBooker(citizenID));
                    }
                }  
            }
            fl2 = fl;
            FLM.replaceFlight(fl, fl2);
        }
    }

    //Tìm các chuyến bay theo ngày bay, nơi đi, nơi đến hoặc theo hoa hồng
    public static void searchFlight(){
        int choose; 
        ArrayList<Flight> fl = new ArrayList<>();
        System.out.print("\nEnter Number: <1>_By Booked Date, Depature, Destination   <2>_By Commission");
        System.out.print("\nYour Choice: ");
        while (true) {
            choose = Integer.parseInt(sc.nextLine());
            if(choose == 1){
                System.out.print("\nPlease Enter Departure: ");
                String from = sc.nextLine();
                System.out.print("\nPlease Enter Destination: ");
                String to = sc.nextLine();
                System.out.print("\nPlease Enter Date Of Department(dd-mm-yyyy): ");
                Date date = inputDate();
                fl = FLM.getFlight2(from, to, date);
                System.out.println(" _______________________________________________________________");
                System.out.println("|Airline        |FlightID       |Type           |Airfares       |");
                System.out.println("|_______________|_______________|_______________|_______________|");
                for(Flight flight: fl){
                    System.out.printf("|%15s|%15s|%15s|%15s|",flight.getAirline(),flight.getFlightID(),flight.getFlightType(),flight.getAirfares());
                }
                System.out.println("\n|_______________|_______________|_______________|_______________|");
                break;
            }
            else if(choose == 2){
                System.out.print("\nPlease Enter Minimum Commission Level: ");
                double com = Double.parseDouble(sc.nextLine());
                fl = FLM.getFlight3(com);
                System.out.print(" _______________________________________________________________________________________________________________");
                System.out.print("\n|From           |To             |Take Off Date  |Airline        |FlightID       |Type           |Commission     |");
                System.out.print("\n|_______________|_______________|_______________|_______________|_______________|_______________|_______________|");
                for(Flight flight: fl){
                    System.out.printf("\n|%15s|%15s|%15s|%15s|%15s|%15s|%15f|",flight.getFrom(),flight.getTo(),df.format(flight.getTakeOffDate()),flight.getAirline(),flight.getFlightID(),flight.getFlightType(),flight.getBenefits());
                }
                System.out.print("\n|_______________|_______________|_______________|_______________|_______________|_______________|_______________|");
                break;
            }
            else { 
                System.out.print("invalid! Input again: ");
            }
        }
    }

    //Cập nhật phần trăm hoa hồng cho chuyến bay nội địa hoặc quốc tế
    public static void fixCommission(){
        int choose; 
        System.out.print("\nEnter Number: <1>_Domestic Flight Commission     <2>_Internal Flight Commission");
        System.out.print("\nYour Choice: ");
        while (true) {
            choose = Integer.parseInt(sc.nextLine());
            if (choose == 1 || choose == 2) {
                break;
            }
            else { 
                System.out.print("invalid! Input again: ");
            }
        }
        System.out.print("\nPlease Enter New Commission Percentage: ");
        double newCom;
        while (true) {
            try {
                newCom = Double.parseDouble(sc.nextLine());
                break;
            } catch (NumberFormatException ex) {
                System.out.print("invalid! Input again: ");
            }
        }
        if(choose == 1){
            FLM.UpdateDomesticCommission(newCom);
        }
        else {
            System.out.print("\nPlease Enter New Bonus: ");
            int newBo;
            while (true) {
                try {
                    newBo = Integer.parseInt(sc.nextLine()); break;
                } catch (NumberFormatException ex) {
                    System.out.print("invalid! Input again: ");
                }
            }
            FLM.UpdateInternalCommission(newCom, newBo);
        }
    }

    //Tính tổng hoa hồng phòng vé thu được từ dd-MM-yyyy đến dd-MM-yyyy
    public static void CaculateCommission(){
        System.out.print("\nPlease Enter The Start Date: ");
        Date startDate = inputDate();
        System.out.print("\nPlease Enter The End Date: ");
        Date endDate;
        while(true){
            endDate = inputDate();
            if(endDate.compareTo(startDate) > 0){
                break;
            }
            else{
                System.out.print("\nStart Date Must Be Earlier Than End Date");
            }
        }
        System.out.printf("Total: %15f",FLM.caculateCommission(startDate, endDate));
    }

    //In ra tất cả chuyến bay
    public static void ShowList(){
        System.out.print("\nChoose: <1>_Show Flight List   <2>_Show Booker List Of A Flight\nYour Choice: ");
        int choose;
        while (true) {
            choose = Integer.parseInt(sc.nextLine());
            if (choose == 1) {
                System.out.println(" _______________________________________________________________________________________________________________");
                System.out.println("|           From|             To|  Take Off Date|        Airline|       FlightID|           Type|       Airfares|");
                System.out.println("|_______________|_______________|_______________|_______________|_______________|_______________|_______________|");
                for(Flight fl: FLM.getFlights()){
                    System.out.printf("|%15s|%15s|%15s|%15s|%15s|%15s|%15f|\n",fl.getFrom(),fl.getTo(),df.format(fl.getTakeOffDate()),fl.getAirline(),fl.getFlightID(),fl.getFlightType(),fl.getAirfares());
                }
                System.out.println("|_______________|_______________|_______________|_______________|_______________|_______________|_______________|");
                break;
            }
            else if(choose == 2){
                Flight fl = findFlight();
                System.out.println(" _______________________________________________");
                System.out.println("|           Name|     Citizen ID|   Booking Date|");
                System.out.println("|_______________|_______________|_______________|");
                for(Booker bk: fl.getBookerList()){
                    System.out.printf("|%15s|%15s|%15s|\n",bk.getName(),bk.getCitizenID(),df.format(bk.getBookingDate()));
                }
                System.out.println("|_______________|_______________|_______________|");
                break;
            }
            else { System.out.print("invalid! Input again: ");}
        } 
    }

    //Tạo chuyến bay demo
    public static void DemoFlight(String flightType, String from, String to, Date date, String airline, String flightID, double airfares, ArrayList<Booker> bookerList) {
        if (flightType == "Domestic") {
            Flight fl = new DomesticFlight("Domestic", from, to, date, airline, flightID, airfares, bookerList);
            DomesticFlight fl2 = (DomesticFlight) fl;
            FLM.createFlight(fl2);
        } else if (flightType == "Internal") {
            Flight fl = new InternalFlight("Internal", from, to, date, airline, flightID, airfares, bookerList);
            InternalFlight fl2 = (InternalFlight) fl;
            FLM.createFlight(fl2);
        } 
    }
}
