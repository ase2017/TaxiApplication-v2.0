package main.model;


public class TaxiData {

    TaxiGenerator taxiGenerator = new TaxiGenerator();
    GroupOfPassengersGenerator groupOfPassengersGenerator = new GroupOfPassengersGenerator();

    TaxiList taxiList;
    TaxiQueue taxiQueue;
    PassengerQueue passengerQueue;


    public TaxiData(int numberOfTaxis, int numberOfGroups) {

        taxiList = new TaxiList();

        fillTaxiQueue(numberOfTaxis);

        fillGroupsQueue(numberOfGroups);
    }

    public void addTaxiToQueue() {

        // TODO : balance the queues (add to the smallest queue)
        Taxi tx;
        boolean taxiIsValid = false;
        while(!taxiIsValid) {
            tx = TaxiGenerator.generateTaxi();

            // does not exist in list
            if (!taxiList.containsTaxi(tx.getTaxiRegistrationNumber())) {
                // add it to list and queue
            } else {

                // exists in list but not in queues
                if(!taxiQueue.containsTaxi(tx.getTaxiRegistrationNumber())) {
                    // TODO : adapt with queues
                    // reject if reg number exists
                    // else : add it to queue
                }

               // exists in list and in queues
                else{
                    // reject
                }

            }



        }

    }

    public void addGroup() {

        taxiQueue.add(GroupOfPassengersGenerator.generateGroupOfPassengers());
    }

    /**
     *
     * @param numberOfTaxis
     */
    public void fillTaxiQueue(int numberOfTaxis) {

        if (numberOfTaxis > 0) {

            taxiList.add(TaxiGenerator.generateTaxi(numberOfTaxis));

        }
    }

    public void fillGroupsQueue(int numberOfGroups) {
        if (numberOfGroups > 0) {
            passengerQueue.add(groupOfPassengersGenerator.generateGroupOfPassengers(numberOfGroups));
        }
    }
}
