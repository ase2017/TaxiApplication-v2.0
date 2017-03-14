package main.model;


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
