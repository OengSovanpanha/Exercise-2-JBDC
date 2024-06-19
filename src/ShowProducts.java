import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ShowProducts {
    // Database credentials and URL
    private static final String DB_URL = "jdbc:mysql://localhost:3306/TestJBDC";
    private static final String USER = "root";
    private static final String PASS = "123451";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Execute a query to retrieve products
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "SELECT id, name, price_per_unit, active_for_sell FROM Product";
            ResultSet rs = stmt.executeQuery(sql);

            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double pricePerUnit = rs.getDouble("price_per_unit");
                boolean activeForSell = rs.getBoolean("active_for_sell");

                // Display values
                System.out.print("ID: " + id);
                System.out.print(", Name: " + name);
                System.out.print(", Price per Unit: " + pricePerUnit);
                System.out.println(", Active for Sell: " + activeForSell);
            }

            // Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (Exception e) {
                // Do nothing
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Goodbye!");
    }
}