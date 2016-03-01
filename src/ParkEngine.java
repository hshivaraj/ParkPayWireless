import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import Location.Location;
import Utils.Log;
import Utils.Mysql;
import Utils.Utilities;
import Vehicle.Car;

public class ParkEngine {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		Log ll = new Log("/tmp/ppw.log");
		
		Car c;
		c = new Car("AV14 URM");
		Location l = new Location(10,20);
		c.park(l);
		
		if( c.has_parked() ) {
			ll.I("Car is been parked");
		} else {
			ll.I("Car is not parked");
		}
		
		c.stop_parking();
		
		if( c.has_parked() ) {
			ll.I("Car is been parked", 10, "thats it");
		} else {
			ll.I("Car is not parked");
		}

		Hashtable<String, Comparable> h = new Hashtable<String, Comparable>();
		h.put("key1", 100);
		h.put("key2", "harish");
		
//		Mysql sql = new Mysql("practice");
//		sql.Insert("ppw", h);
//		ResultSet rs = sql.executeQuery("select * from pet");
//		while(rs.next()) {
//			System.out.println(rs.getString("name"));
//		}
		
	}

}
