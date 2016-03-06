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

import static Utils.Utilities.P;

public class Mysql {
	private String USERNAME = "root";
	private String PASSWORD = "mysore159";
	private String HOSTNAME = "mydb.cvchqpuax6xy.eu-west-1.rds.amazonaws.com";
	private String DBNAME = null;
	private Integer PORT = 3306;
	
	private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    private String db_url = null;
    
    private static Mysql instance = null;
    
    public static Mysql getInstance() {
    	if( instance == null ) {
    		instance = new Mysql("ppw");
    	}
    	return instance;
    }
    
	
    protected Mysql(String dbname) {
    	this.Initiliaise(dbname);
    }
    
    protected Mysql() {
    	this.Initiliaise("ppw");
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
	
	public int executeUpdate(String query) {
		try {
			this.st = this.con.createStatement();
			int rc = this.st.executeUpdate(query);
			return rc;
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
            return 0;
		}
	}
	
	public void closeConnection() {
		try {
			this.con.close();
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public int Insert(String t, Hashtable rd) {
		Set k = rd.keySet();		
		Collection v = rd.values();
		
		System.out.println(rd.values().getClass());
		String query = "INSERT INTO " + t + " " +
					   Utilities.set_toString(k) + 
					   " VALUES " +
					   Utilities.map_toString(v); 
		return this.executeUpdate(query);
	}
	
	public ResultSet Select() {
		ResultSet ss = null;
		return ss;
	}
	
	public boolean DeleteById(String t, int id) {
		String query = "DELETE FROM " + t + " WHERE " + 
							"id=" + String.valueOf(id);
		return ( this.executeUpdate(query) > 0 );
	}
	
	public int UpdateById(String t, int id, Hashtable rd) {
		String query = "UPDATE " + t + " SET " + Utilities.key_value(rd) +
							" WHERE id=" + String.valueOf(id) +  ";";
		return this.executeUpdate(query);
	}
	
	public int UpdateByWhere(String t, Hashtable where, Hashtable set) {		
		String query = "UPDATE " + t + " SET " + Utilities.key_value(set) 
							+ " WHERE " + Utilities.key_value(where, "AND") + ";";
		
		return this.executeUpdate(query); 
	}
	
	public boolean Check(String table, String c, 
			String expected_v, String...where) {
		
		String q = "select "+ c + " from " + table + " where ";
		for(int i=0; i<where.length; i++) {
			q += where[i];
		}
		q += ";";
				
		ResultSet rs = this.executeQuery(q);
		
		try {
			if(rs.next()) {
				return (rs.getString("status").toString().equals(expected_v));
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/* PRIVATE METHODS */
	
	private void Initiliaise(String dbname) {
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
}