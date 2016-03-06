package Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
		
		/* 1. Insert an entry in Part table for the vehicle
 		 * 2. Update the status for the vehicle in the car table.
		 * 3. 
		 */
		
		if( this.isParked() ) {
			return false;
		} else {
			Mysql con = Mysql.getInstance();
			Hashtable<String, String> v = new Hashtable<String, String>();

			v.put("car_id", this.id.toString());
			v.put("start_time", String.valueOf((new Date().getTime())));
			con.Insert("park", v);
			
			v.clear();
			v.put("status", "\'p\'");
			con.UpdateById("car", id, v);
			this.setStatus(Status.PARKED);
		}
		return true;
	}
	
	public boolean stopParking() {
		
		/* 1. Update the park table with the end time.
		 * 2. Update the column for the vehicle record 
		 *    in car table to not parked. 
		 */
		
		if( this.isParked() ) {
			Mysql con = Mysql.getInstance();
			Hashtable<String, String> set = new Hashtable<String, String>();
			Hashtable<String, String> where = new Hashtable<String, String>();
			
			where.put("car_id", id.toString());
			where.put("end_time", "null");
			
			set.put("end_time", String.valueOf((new Date().getTime())));
			int rc = con.UpdateByWhere("park", where, set);
			
			this.setStatus(Status.NOTPARKED);
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
	
	protected void setStatus(Status s) {
		Mysql con = Mysql.getInstance();
		Hashtable<String, String> v = new Hashtable<String, String>();
		
		if( s == Status.PARKED) {
			v.put("status", "\'p\'");
		} else if( s == Status.NOTPARKED ) {
			v.put("status", "\'np\'");
		}
		
		con.UpdateById("car", id, v);
		this.status = s;
	}
}
