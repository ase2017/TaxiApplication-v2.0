package main.model;


/**
 * @author George and Jules
 */
public enum WindowStatuses {

    AVAILABLE ("Available"),
    BUSY ("Busy"),
    BREAK ("Break"),
    UNAVAILABLE("Unavailable");

    private final String status;

    private WindowStatuses(String s) {
        status = s;
    }

    /**
     *
     */
    public boolean equalsName(String windowStatus) {
        return status.equals(windowStatus);
    }



    /**
     * Returns the associated String of the current enum
     * @return the associated String of the current enum
     */
    public String toString() {
        return this.status;
    }
}