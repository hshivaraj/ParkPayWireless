package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import Utils.Utilities;

public class Mysql {
	private String USERNAME = "root";
	private String PASSWORD = "mysore159";
	private String HOSTNAME = "localhost";
	private String DBNAME = null;
	private Integer PORT = 3306;
	
	private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    
    private String db_url = null;
	
    public Mysql(String dbname) {
    	this.DBNAME = dbname;
    	this.db_url = "jdbc:mysql://" + this.HOSTNAME + ":" + 
    			this.PORT.toString() + "/" + dbname;
    	
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    	} catch(ClassNotFoundException ex) {
    		System.out.println(ex.getMessage());
    	}
    	
    	try {
    		this.con = DriverManager.getConnection(this.db_url, this.USERNAME, 
    				this.PASSWORD);
    	} catch (SQLException ex) {
    		System.out.println(ex.getMessage());
    	}
    }
    
	public Mysql(String hostname, Integer port, String dbname, 
			String username, String passwd) {

		this.USERNAME = username;
		this.PASSWORD = passwd;
		this.DBNAME = dbname;
		this.PORT = port;
		this.HOSTNAME = hostname;
	}
	
	public ResultSet executeQuery(String query) {
		try{
 			this.st = this.con.createStatement();
			this.rs = this.st.executeQuery(query);
			return this.rs;
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
            return null;
		}
	}
	
	public void closeConnection() {
		try {
			this.con.close();
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public boolean Insert(String t, Hashtable rd) {
		Set k = rd.keySet();		
		Collection v = rd.values();
		
		System.out.println(rd.values().getClass());
		String query = "INSERT INTO " + t + " " +
					   Utilities.set_toString(k) + 
					   " VALUES " +
					   Utilities.map_toString(v); 
		System.out.println(query);
		return true;
	}
	
	public ResultSet Select() {
		ResultSet ss = null;
		return ss;
	}
	
	public boolean Delete() {
		return true;
	}
	
	public boolean Update() {
		return true;
	}
}