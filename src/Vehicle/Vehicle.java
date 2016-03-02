package Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import Location.Location;
import Utils.Mysql;

public abstract class Vehicle {
	
	protected enum Status {
		PARKED("p"),
		NOTPARKED("np");
		
		private String type;
	
		private Status(String type) {
			this.type = type;
		}
	
		@Override
		public String toString() {
			return this.type;
		}
	};
	
	protected String reg;
	protected Integer id;
	protected Status status = Status.NOTPARKED;
	
	public boolean startParking(Location l) {
		if( this.isParked() ) {
			return false;
		} else {
			Mysql con = Mysql.getInstance();
			Hashtable<String, String> v = new Hashtable<String, String>();
			v.put("car_id", this.id.toString());
			v.put("start_time", "0000");
			
			con.Insert("park", v);
			
			this.status = Status.PARKED;
		}
		return true;
	}
	
	public boolean stopParking() {
		if( this.isParked() ) {
			this.status = Status.NOTPARKED;
			return true;
		} else {
			return false;	
		}
	}
	
	public boolean isParked() {
		Mysql sql = Mysql.getInstance();
		return sql.Check("car", "status", Status.PARKED.toString(), "reg=\'"+this.reg+"\'");
	}
	
	protected boolean isVehicleValid(String reg) throws SQLException {
		Mysql sql = Mysql.getInstance();
		ResultSet rs = sql.executeQuery("select 1 from car where reg='" + reg + "' LIMIT 1");
		return (rs.next()) ? true : false; 
	}
	
}
