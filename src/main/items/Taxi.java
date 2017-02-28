package main.items;

import main.exceptions.InvalidBrandNameException;
import main.exceptions.InvalidRegistrationNumberException;
import main.exceptions.InvalidTaxiNameException;
import main.io.DataFileReader;
import main.utils.DataFormatValidator;

/**
 * Class Name: Taxi.java
 * 
 * Description: This class creates Taxi objects with the data taken
 * 				from the file taxi.txt
 * 
 * @author George Goniotakis
 * @since Feb 9, 2017
 */

public class Taxi {

	private String registrationNumber; //Taxi registration number
	private String driverName; //Taxi driver`s name
	private String brand; //Taxi`s brand
	
	/**
	 * This constructor creates objects of type Taxi.
	 * 
	 * @throws InvalidRegistrationNumberException If the taxi`s registration number is invalid show an error message
	 * @throws InvalidTaxiNameException If the taxi`s name is invalid show an error message
	 * @throws InvalidBrandNameException If the taxi`s brand name is invalid show an error mesage
	 * 
	 * @param registrationNumber Taxi`s registration number
	 * @param driverName Taxi`s driver name
	 * @param brand Taxi`s brand
	 */
	public Taxi(String registrationNumber, String driverName, String brand){
		
		this.registrationNumber = registrationNumber;
		this.driverName = driverName;
		this.brand = brand;
		
		/* Checks */
		
		try{
			
			if ( !DataFormatValidator.validateRegistrationNumber(registrationNumber)) 
				throw new InvalidRegistrationNumberException(DataFileReader.FILE_NAME_TAXIS, DataFileReader.line_counter);		
			
			if ( !DataFormatValidator.validateDriverName(driverName)) 
				throw new InvalidTaxiNameException(DataFileReader.FILE_NAME_TAXIS, DataFileReader.line_counter);
				
			if ( !DataFormatValidator.validateBrand(brand))		
				throw new InvalidBrandNameException(DataFileReader.FILE_NAME_TAXIS, DataFileReader.line_counter);
			
		} catch(InvalidRegistrationNumberException | InvalidTaxiNameException | 
				InvalidBrandNameException e){
			
			System.out.println(e.getMessage()); //Show error message
			DataFileReader.objectChecker = false;
		}	
		
		/* End of checks */
	}
	
	/**
	 * This method returns the information of a Taxi in one
	 * line.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return "Taxi{" +
				"registrationNumber='" + registrationNumber + '\'' +
				", driverName='" + driverName + '\'' +
				", brand='" + brand + '\'' +
				'}';
	}
	
	/* Getters and Setters */
	
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}

