package main.model;

/**
 * @author George C. and Jules
 */
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
/*
    public void generateAndAddTaxi() {

        Taxi tx;
        boolean taxiIsValid = false;
        while(!taxiIsValid) {
            tx = TaxiGenerator.generateTaxi();

            // does not exist in list
           /* if (!taxiList.containsTaxi(tx.getTaxiRegistrationNumber())) {
                // add it to list and queue
                taxiIsValid = true;
            } else {

                // exists in list but not in queue
                if(!taxiQueue.containsTaxi(tx.getTaxiRegistrationNumber())) {
                    // reject if reg number exists
                    if() {

                    }
                    // else : add it to queue
                    else {
                        taxiIsValid = true;
                    }

                }

               // exists in list and in queue
                else{
                    reject
                }

            }



        }

    }
*/
    public void addGroup() {

        //taxiQueue.add(GroupOfPassengersGenerator.generateGroupOfPassengers());
    }

    /**
     *
     * @param numberOfTaxis
     */
    public void fillTaxiQueue(int numberOfTaxis) {

        if (numberOfTaxis > 0) {

            //taxiList.add(TaxiGenerator.generateTaxi(numberOfTaxis));

        }
    }

    public void fillGroupsQueue(int numberOfGroups) {
        if (numberOfGroups > 0) {
            //passengerQueue.add(groupOfPassengersGenerator.generateGroupOfPassengers(numberOfGroups));
        }
    }
}
