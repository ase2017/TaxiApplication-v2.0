package main;


/**
 * Application Name: TaxiApplication v1.0
 *
 * Description: This application is reading the content of four files,
 * 				and outputs three reports. This application is the first
 * 				part of our coursework for the course F21AS - Advanced Software
 * 				Engineering at Heriot-Watt University (Edinburgh Campus).
 *
 * Students: George Goniotakis (gg29)
 * 			 Georgios Chiotis (gc25)
 * 			 Jules Detrie (jd57)
 *
 * @author George Goniotakis
 * @since Feb 9, 2017
 */

public class Main {

	 public static void main(String[] args) {
 		
		 TaxiService taxiService = new TaxiService();
         taxiService.start();

 	}
}
