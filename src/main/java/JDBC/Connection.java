package JDBC;

import java.sql.*;

public class Connection {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/store";
        String name = "root";
        String pass = "pravin";
        String query = "select * from product";


        //CREATING CONNECTION
        Class.forName("com.mysql.cj.jdbc.Driver");
        java.sql.Connection conn = DriverManager.getConnection(url, name, pass);
        System.out.println("Connection Established...");


        //FETCHING DATA FROM THE DATABASE
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        for (int i = 1; i <= 6; i++) {
            rs.next();
            String product_name = rs.getString("product_name");
            System.out.println(product_name);
        }

        //UPDATING THE TABLE
        String query3 = "Update product set product_name = \"Hand Wash\" where product_id = 4";
        Statement stmt = conn.createStatement();
        {
            stmt.executeUpdate(query3);
            System.out.println("Database updated successfully ");
        }

        //INSERTING INTO DATABASE AND PRINTING THE GENERATED AUTO-INCREMENT KEY
        String query2 = "insert into product (product_name,quantity) \n" + "values\n" + "(\"Shampoo\",50)";
        PreparedStatement ps = conn.prepareStatement(query2, Statement.RETURN_GENERATED_KEYS);

        ps.execute();

        ResultSet rs2 = ps.getGeneratedKeys();
        int generatedKey = 0;
        if (rs2.next()) {
            generatedKey = rs2.getInt(1);
        }

        System.out.println("Inserted record's ID: " + generatedKey);

        st.close();
        conn.close();


    }
}

