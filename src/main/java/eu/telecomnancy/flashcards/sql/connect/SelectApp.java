package eu.telecomnancy.flashcards.sql.connect;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

/**
 *
 * @author sqlitetutorial.net
 */
public class SelectApp {

    /**
     * Connect to the test.db database
     * @return the Connection object
     */
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:db/database.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    
    /**
     * select all rows in the cards table
     */
    public void selectAllCards(){
        String sql = "SELECT question, answer FROM cards";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("question") +  "\t" + 
                                   rs.getString("answer"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Get the warehouse whose capacity greater than a specified capacity (example with parameter)
     * @param capacity 
     */
    public void getCapacityGreaterThan(double capacity){
        String sql = "SELECT id, name, capacity "
                   + "FROM warehouses WHERE capacity > ?";
 
        try (Connection conn = this.connect();
            PreparedStatement pstmt  = conn.prepareStatement(sql)){
            
            // set the value
            pstmt.setDouble(1,capacity);
            //
            ResultSet rs  = pstmt.executeQuery();
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" + 
                                    rs.getString("name") + "\t" +
                                    rs.getDouble("capacity"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}