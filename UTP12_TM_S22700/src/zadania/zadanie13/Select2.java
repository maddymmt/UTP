package zadania.zadanie13;

import java.sql.*;

import static java.sql.ResultSet.FETCH_REVERSE;

public class Select2 {

    static public void main(String[] args) {
        new Select2();
    }
    Select2(){
        Connection con = null;
        String url = "jdbc:derby://localhost/ksidb";
        String sel = """
                select * from POZYCJE
                join AUTOR A on A.AUTID = POZYCJE.AUTID
                where ROK >= 2000 AND CENA > 30
                                """;
        try  {
            con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sel);
            ResultSetMetaData rsmd = rs.getMetaData();
            int cc = rsmd.getColumnCount();
            for (int i = 1; i <= cc; i++)
                System.out.print(rsmd.getColumnLabel(i) + "     ");

            System.out.println("\n------------------------------ przewijanie do góry");

            // ... ?
           // rs.setFetchDirection(ResultSet.FETCH_REVERSE);
            rs.afterLast();
            while (rs.previous()) {
                for (int i = 1; i <= cc; i++) System.out.print(rs.getString(i) + ", ");
                System.out.println("");
            }

            System.out.println("\n----------------------------- pozycjonowanie abs.");
            int[] poz =  { 3, 7, 9  };
            for (int p = 0; p < poz.length; p++)  {
                System.out.print("[ " + poz[p] + " ] ");
                // ... ?

                rs.absolute(poz[p]);

                for (int i = 1; i <= cc; i++) System.out.print(rs.getString(i) + ", ");
                System.out.println("");

            }
            stmt.close();
            con.close();
        } catch (Exception exc)  {
            System.out.println(exc.getMessage());
        }
    }
}
