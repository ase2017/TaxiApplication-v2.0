package main.model;

import java.util.Random;

/**
 * Holds the destination names and provides method to return a random destination name
 * @author George C. and Jules
 */
public enum DestinationList {

    HERIOT_WATT ("Heriot Watt"),
    MAIN_STATION ("Main Station"),
    OCEAN_TERMINAL ("Ocean Terminal"),
    SCOTTISH_WHISKY_EXPERIENCE ("Scottish Whisky Experience"),
    PRINCESS_STREET ("Princess Street"),
    HAYMARKET ("Haymarket"),
    NAPIER_UNIVERSITY ("Napier University"),
    BT_MURRAYFIELD_STADIUM ("BT Murrayfield Stadium"),
    NATIONAL_MUSEUM_OF_SCOTLAND("National Museum of Scotland"),
    SCOTTISH_NATIONAL_GALLERY("Scottish National Gallery"),
    PALACE_OF_HOLYROODHOUSE("Palace of Holyroodhouse"),
    EDINBURGH_PLAYHOUSE("Edinburgh Playhouse"),
    ROYAL_EDINBURGH_HOSPITAL("Royal Edinburgh Hospital"),
    EDINBURGH_AIRPORT("Edinburgh Airport"),
    PORTOBELLO_BEACH("Portobello Beach"),
    FETTES_COLLEGE("Fettes College"),
    DUDDINGSTON_GOLF_CLUB("Duddingston Golf Club");


    private final String destinationName;
    private static final Random rand = new Random();

    private DestinationList(String s) {
        destinationName = s;
    }

    /**
     * Returns a random name in the enum
     * @return a String which is a random name in the enum
     */
    public static String getRandomDestinationName(){
        return DestinationList.values()[rand.nextInt(DestinationList.values().length)].toString();
    }

    public String toString() {
        return this.destinationName;
    }


}
