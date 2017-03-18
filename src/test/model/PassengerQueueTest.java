package test.model;

import main.model.GroupOfPassengers;
import main.model.PassengerQueue;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;


/**
 * @author Jules
 */
public class PassengerQueueTest {

    @Test
    public void add(){
        PassengerQueue passengerQueue = new PassengerQueue();

        GroupOfPassengers groupOfPassengersNull = null;
        GroupOfPassengers groupOfPassengers = new GroupOfPassengers(5,"test");

        // trying to add a null passenger
        assertNotNull(passengerQueue.getGroupOfPassengersQueue());
        passengerQueue.add(groupOfPassengersNull);
        assertNotNull(passengerQueue.getGroupOfPassengersQueue());
        assertTrue(passengerQueue.getGroupOfPassengersQueue().size() == 0);

        // setting queue to null then adding a valid group
        passengerQueue.setGroupOfPassengersQueue(null);
        assertNull(passengerQueue.getGroupOfPassengersQueue()); // null before add
        passengerQueue.add(groupOfPassengers);
        assertNotNull(passengerQueue.getGroupOfPassengersQueue()); // not null after add
        assertTrue(passengerQueue.getGroupOfPassengersQueue().size() == 1);

    }

    @Test
    public void popGroup(){
        PassengerQueue passengerQueue = new PassengerQueue();

        GroupOfPassengers groupOfPassengersNull = null;
        GroupOfPassengers groupOfPassengers = new GroupOfPassengers(5,"test");

        // popping an empty queue
        assertNull(passengerQueue.popGroup());

        // popping an empty queue
        passengerQueue.setGroupOfPassengersQueue(null);
        assertNull(passengerQueue.popGroup());

        // popping from non empty list
        passengerQueue.setGroupOfPassengersQueue(new LinkedList<>());
        passengerQueue.add(groupOfPassengers);
        assertTrue(passengerQueue.getGroupOfPassengersQueue().size() == 1);
        assertNotNull(passengerQueue.popGroup());
    }
}
