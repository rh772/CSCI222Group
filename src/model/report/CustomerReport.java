package model.report;

/**
 * Created by hdd on 17/05/15.
 */
public class CustomerReport {
    private int totalCost;
    private int flightCost;
    private int serviceCost;
    private int totalReservation;

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public int getFlightCost() {
        return flightCost;
    }

    public void setFlightCost(int flightCost) {
        this.flightCost = flightCost;
    }

    public int getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(int serviceCost) {
        this.serviceCost = serviceCost;
    }

    public int getTotalReservation() {
        return totalReservation;
    }

    public void setTotalReservation(int totalReservation) {
        this.totalReservation = totalReservation;
    }
}
