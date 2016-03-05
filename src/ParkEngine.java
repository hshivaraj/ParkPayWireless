import Location.Location;
import Utils.Log;
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
		c.startParking(l);
		
		if( c.isParked() ) {
			ll.I("Car is been parked");
		} else {
			ll.I("Car is not parked");
		}
		
		c.stopParking();
		
		if( c.isParked() ) {
			ll.I("Car is been parked", 10, "thats it");
		} else {
			ll.I("Car is not parked");
		}
	}

}
