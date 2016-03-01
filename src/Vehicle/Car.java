package Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;

import Location.Location;
import UserException.UnknownVehnicalException;
import Utils.Mysql;


public class Car extends Vehicle {
	
	public Car(String model, 
			String regno,
			String make,
			String colur, 
			OwnerShip type ) {
		
	}
	
	public Car(String regno) throws Exception {
		
		/* 1. Check if the car registration exists in the data
		 * 2. And then create an object. 
		 * 3. If it donst exists report the car is not registered for this service.
		 * 
		 */
		
		if( ! this.isCarValid(regno) ) {
			throw new UnknownVehnicalException();
		}
		this.reg = regno;
		
	}
}
