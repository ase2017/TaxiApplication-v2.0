package main.model;

/**
 * Abstract class that possess common information about classes than can be in queues (Taxi, GroupsOfPassengers)
 * @author Jules and George C.
 */
public abstract class QueueItem {

    long arrivalTime;
    long queueDepartureTime;
    long finalDepartureTime;

    public long getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public long getQueueDepartureTime() {
        return queueDepartureTime;
    }

    public void setQueueDepartureTime(long queueDepartureTime) {
        this.queueDepartureTime = queueDepartureTime;
    }

    public long getFinalDepartureTime() {
        return finalDepartureTime;
    }

    public void setFinalDepartureTime(long finalDepartureTime) {
        this.finalDepartureTime = finalDepartureTime;
    }
}
