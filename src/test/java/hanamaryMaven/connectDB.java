package hanamaryMaven;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;


public class connectDB {

	Connection con = null; // con object from db
	readPropertyFile file = null;

	//attribute: thuộc tính cần so sánh khi query để so sánh trên UI
	//hàm query trả về dạng String // trả về 1 danh sách giá trị attribute khi query ra nhiều hàng 
	public ArrayList<String> QueryDB(String query, String attribute) throws ClassNotFoundException, IOException, SQLException {

		try {
			file = new readPropertyFile();
			// open a connection
			con = DriverManager.getConnection(file.getDBConnectionString(), file.getDBusername(), file.getDBpassword());
			Class.forName("com.mysql.jdbc.Driver");			
			// execute a query
			Statement stat = con.createStatement(); // create stat 4 db connection
			ResultSet rs = stat.executeQuery(query); // send query to db
			
			ArrayList<String> value= new ArrayList<String>();
			// results from query r stored in rs object.
			
			while (rs.next()) {
				value.add(rs.getString(attribute)); // lấy giá trị trong cột attributeer từ database 
			}			
			return value;			
		} catch (IOException e) {
			throw e;
		} finally {
			if (con != null)
				con.close();
		}
	}
	
	//mảng các giá trị tất cả các trường query ra 1 rows 
	public ArrayList<String> QueryRowfromDB(String query, String[] comparedColumn) throws IOException, SQLException, ClassNotFoundException{
		try {
			file = new readPropertyFile();
			// open a connection
			con = DriverManager.getConnection(file.getDBConnectionString(), file.getDBusername(), file.getDBpassword());
			Class.forName("com.mysql.jdbc.Driver");			
			// execute a query
			Statement stat = con.createStatement(); // create stat 4 db connection
			ResultSet rs = stat.executeQuery(query); // send query to db
			
			ArrayList<String> value= new ArrayList<String>();
			// results from query r stored in rs object.
			
			int i = 0; 
			while (rs.next() && i< comparedColumn.length) {
				value.add(rs.getString(comparedColumn[i])); // lấy giá trị trong cột attributeer từ database 
				i++;
			}			
			return value;			
		} catch (IOException e) {
			throw e;
		} finally {
			if (con != null)
				con.close();
		}
	}
	//hàm query trả về dạng datetime
	public ArrayList<Timestamp> QueryDatetimeDB(String query, String attribute) throws ClassNotFoundException, IOException, SQLException {

		try {
			file = new readPropertyFile();
			// open a connection
			con = DriverManager.getConnection(file.getDBConnectionString(), file.getDBusername(), file.getDBpassword());
			Class.forName("com.mysql.jdbc.Driver");			
			// execute a query
			Statement stat = con.createStatement(); // create stat 4 db connection
			ResultSet rs = stat.executeQuery(query); // send query to db
			
			ArrayList<Timestamp> value= new ArrayList<Timestamp>();
			// results from query r stored in rs object.
			
			while (rs.next()) {
				value.add(rs.getTimestamp(attribute)); // lấy giá trị trong cột attributeer từ database 
			}			
			return value;			
		} catch (IOException e) {
			throw e;
		} finally {
			if (con != null)
				con.close();
		}

	}
}
