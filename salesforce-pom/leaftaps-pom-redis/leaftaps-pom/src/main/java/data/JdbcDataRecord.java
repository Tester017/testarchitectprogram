package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import config.ConfigurationManager;

public class JdbcDataRecord {
   
    public static String getRandomLeadId() {
    	
        String query = "SELECT PARTY_ID FROM opentaps.PERSON ORDER BY RAND() LIMIT 1";
        String leadId = null;
        try (Connection connection = DriverManager.getConnection(ConfigurationManager.configuration().dbUrl(), 
        		ConfigurationManager.configuration().dbUser(), ConfigurationManager.configuration().dbPassword());
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            if (rs.next()) {
                leadId = rs.getString("PARTY_ID");
                System.out.println("Fetched Lead ID: " + leadId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leadId;
    }
}
