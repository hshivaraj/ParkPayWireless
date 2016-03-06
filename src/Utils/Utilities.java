package Utils;

import java.util.Collection;
import java.util.Hashtable;
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
	
	public static String key_value(Hashtable rd) {
		String kv = "";
		Iterator it = rd.keySet().iterator();
		
		while(it.hasNext()) {
			String k = it.next().toString(); 
			if( rd.get(k) == "null") {
				kv += k + "IS NULL";
			} else {
				kv += k + "=" + rd.get(k);
			}
			if(it.hasNext()) {
				kv += ", ";
			}
		}
		return kv;
	}
	
	public static String key_value(Hashtable rd, String append) {
		String kv = "";
		Iterator it = rd.keySet().iterator();
		
		while(it.hasNext()) {
			String k = it.next().toString(); 
			if( rd.get(k) == "null") {
				kv += k + " IS NULL ";
			} else {
				kv += k + "=" + rd.get(k);
			}
			if(it.hasNext()) {
				kv += " " + append + " ";
			}
		}
		return kv;
	}
	
}
