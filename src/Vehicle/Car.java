package Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;

import Location.Location;
import UserException.UnknownVehnicalException;
import Utils.Mysql;


public class Car extends Vehicle {
	
	public Car(String regno) throws Exception {
		
		if( ! this.isVehicleValid(regno) ) {
			throw new UnknownVehnicalException();
		}
		Mysql conn = Mysql.getInstance();
		ResultSet rs = conn.executeQuery("select * from car where reg='" + regno + "' LIMIT 1");
		
		if( !rs.next() ) {
			throw new UnknownVehnicalException();
		} 
		this.id = rs.getInt("id");
		this.reg = rs.getString("reg");
		this.status = Status.NOTPARKED;
		
	}
}
