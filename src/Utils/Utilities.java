package Utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Utilities {
	
	public static void P(String msg) {
		System.out.println(msg);
	}
	
	public static String set_toString(Set s) {
		String ss;
		Iterator<Set> it = s.iterator();
		
		ss = "(" + it.next();
		while(it.hasNext()) {
			ss += "," + it.next();
		}
		ss += ")";
		return ss;
	}
	
	public static String map_toString(Collection<Map> m) {
		String ss;
		Iterator it = m.iterator();
		ss = "(" + it.next();
		while(it.hasNext()) {
			ss += "," + it.next();
		}
		ss += ")";
		return ss;
	}
	

}
