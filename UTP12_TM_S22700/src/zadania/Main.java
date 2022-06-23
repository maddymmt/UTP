package zadania;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

public class Main {

    public static void main(String[] args) {

        Connection con;
        DatabaseMetaData md;

        String driverName = "org.apache.derby.jdbc.ClientDriver";			// nazwa sterownika
        String url = "jdbc:derby://localhost/ksidb";				// url bazy


        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(url);
            md = con.getMetaData();




        } catch (Exception exc)  {

        }



    }
}
