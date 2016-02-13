package Vehical;

import java.sql.ResultSet;
import java.sql.SQLException;

import Location.Location;
import UserException.UnknownVehnicalException;
import Utils.Mysql;


public class Car implements Vehical{
	
	private enum Status {
		parked,
		not_parked
	};
	
	private String model;
	private String owner;
	private Status s;
	
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
		if( ! this.car_valid(regno) ) {
			throw new UnknownVehnicalException();
		}
		
	}
	
	public boolean park(Location l) {
		if( this.has_parked() ) {
			return false;
		} else {
			this.s = Status.parked;
		}
		return true;
	}
	
	public boolean stop_parking() {
		if( this.has_parked() ) {
			this.s = Status.not_parked;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean has_parked() {
		return this.s == Status.parked;
	}
	
	private boolean car_valid(String reg) throws SQLException {
		Mysql sql = new Mysql("ppw");
		ResultSet rs = sql.executeQuery("select 1 from car where reg='" + reg + "' LIMIT 1");
		return (rs.next()) ? true : false; 
	}
}
