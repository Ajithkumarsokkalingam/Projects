package OnlineReservationSystem;

public class Reservation {
    private String name;
    private String trainNumber;
    private String trainName;
    private String classType;
    private String dateOfJourney;
    private String from;
    private String to;
    private int reservationId;

    public Reservation(String name, String trainNumber, String trainName, String classType, String dateOfJourney, String from, String to, int reservationId) {
        this.name = name;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.from = from;
        this.to = to;
        this.reservationId = reservationId;
    }

    // Getters and setters...
    public String getName() {
        return name;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getClassType() {
        return classType;
    }

    public String getDateOfJourney() {
        return dateOfJourney;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getReservationId() {
        return reservationId;
    }
}
