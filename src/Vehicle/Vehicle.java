package Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;

import Location.Location;
import Utils.Mysql;

public abstract class Vehicle {
	
	protected enum Status {
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
	
	protected String model;
	protected String owner;
	protected String reg;
	protected Status s = Status.NOTPARKED;
	
	public boolean startParking(Location l) {
		if( this.isParked() ) {
			return false;
		} else {
			this.s = Status.PARKED;
		}
		return true;
	}
	
	public boolean stopParking() {
		if( this.isParked() ) {
			this.s = Status.NOTPARKED;
			return true;
		} else {
			return false;	
		}
	}
	
	public boolean isParked() {
		Mysql sql = Mysql.getInstance();
		return sql.Check("car", "status", Status.PARKED.toString(), "reg=\'"+this.reg+"\'");
	}
	
	protected boolean isCarValid(String reg) throws SQLException {
		Mysql sql = Mysql.getInstance();
		ResultSet rs = sql.executeQuery("select 1 from car where reg='" + reg + "' LIMIT 1");
		return (rs.next()) ? true : false; 
	}
	
}
