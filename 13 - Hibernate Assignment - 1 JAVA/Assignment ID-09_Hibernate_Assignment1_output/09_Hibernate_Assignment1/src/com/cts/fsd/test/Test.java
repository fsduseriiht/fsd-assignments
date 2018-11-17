package com.cts.fsd.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptEngineManager;

import com.cts.fsd.dao.ApplicationDAO;
import com.cts.fsd.util.DBUtility;

public class Test {

	public static void main(String[] args) {
		new Test().checkDbConnection();
	}
	
	public void checkDbConnection() {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DBUtility.getConnection();
            System.out.println("Creating table in given database...");
    		stmt = conn.createStatement();
//    		String sql = "CREATE TABLE   REGISTRATION_1 (id INTEGER not NULL, first VARCHAR(255), last VARCHAR(255), age INTEGER, PRIMARY KEY ( id ))";
//    		String sql = "Drop TABLE REGISTRATION";

//    		String sql = "CREATE TABLE TEST_TABLE1 (column1_int NUMBER not NULL, column2_dec NUMBER not NULL, column3_alpha VARCHAR(255), column4_date DATE)";
//    		stmt.executeUpdate(sql);
    		
    		
    		
    		
    		// creating the Book Table
//    		String sql = "CREATE TABLE BOOK_TABLE (bookId NUMBER not NULL, title VARCHAR(255) not NULL, price NUMBER not NULL, volume NUMBER , publishDate DATE , subjectId NUMBER)";
//    		stmt.executeUpdate(sql);
    		
    		// creating the Subject Table
//    		String sql = "CREATE TABLE SUBJECT_TABLE (subjectId NUMBER not NULL, subtitle VARCHAR(255) not NULL, durationInHours NUMBER not NULL)";
//    		stmt.executeUpdate(sql);
    		
    		List<String> queryList = new ArrayList<String>();
    		queryList.add("INSERT into TEST_TABLE1 values (1, 1.2, 'Ramesh', PARSEDATETIME('24 Aug 2018','dd MMM yyyy'));");
    		queryList.add("INSERT into TEST_TABLE1 values (2, 2.2, 'Jignesh', PARSEDATETIME('26-01-2018','dd-MM-yyyy'));");
    		
//    		for(String sqlFromList : queryList) {
//    			stmt.executeUpdate(sqlFromList);
//    		}
    		
    		
    		System.out.println("Created table in given database...");
    		
    		String selectQuery = "SELECT * FROM TEST_TABLE1";
    		ResultSet rs = stmt.executeQuery(selectQuery);
    		System.out.println(String.format("%15s\t%15s\t%15s\t%15s", "column1_int", "column2_dec", "column3_alpha", "column4_date"));
    		System.out.println(String.format("%15s\t%15s\t%15s\t%15s", "---------------", "---------------", "---------------", "---------------"));
    		while (rs.next()) {
    			System.out.println(String.format("%15s\t%15s\t%15s\t%15s", rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4)));
			}
    		
    		
    		
    		
    		ApplicationDAO.getAllSubjectsFromDB();
    		
    		
    		
    		stmt.close();
    		
    		
    		
    		
    		
    		
            String myExpression = "true || true && false";
            System.out.println("Eval(myExpression) = " + new ScriptEngineManager().getEngineByName("JavaScript").eval(myExpression));
    		
    		
    		
        } 
        catch (Exception e) {
            System.err.println("Exception while creating the connection " + e.getMessage());
        } 
        finally {
            try {
				DBUtility.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        }
    }
}
