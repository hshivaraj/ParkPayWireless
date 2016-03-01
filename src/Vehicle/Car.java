package Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;

import Location.Location;
import UserException.UnknownVehnicalException;
import Utils.Mysql;


public class Car implements Vehicle{
	
	private enum Status {
		PARKED("parked"),
		NOTPARKED("not parked");
		
		private String type;
	
		private Status(String type) {
			this.type = type;
		}
	
		@Override
		public String toString() {
			return this.type;
		}
	};
	
	private String model;
	private String owner;
	private String reg;
	private Status s = Status.NOTPARKED;
	
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
		this.reg = regno;
		
	}
	
	public boolean park(Location l) {
		if( this.has_parked() ) {
			return false;
		} else {
			this.s = Status.PARKED;
		}
		return true;
	}
	
	public boolean stop_parking() {
		if( this.has_parked() ) {
			this.s = Status.NOTPARKED;
			return true;
		} else {
			return false;	
		}
	}
	
	public boolean has_parked() {
		Mysql sql = Mysql.getInstance();
		return sql.Check("car", "status", Status.PARKED.toString(), "reg=\'"+this.reg+"\'");
	}
	
	private boolean car_valid(String reg) throws SQLException {
		Mysql sql = Mysql.getInstance();
		ResultSet rs = sql.executeQuery("select 1 from car where reg='" + reg + "' LIMIT 1");
		return (rs.next()) ? true : false; 
	}
}
