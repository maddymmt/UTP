package zadania.zadanie13;


import java.sql.*;

public class Select {
    static public void main(String[] args) {
        new Select();
    }


    Select() {
        Connection con = null;
        String url = "jdbc:derby://localhost/ksidb";
        String sel = """
                select * from POZYCJE
                join AUTOR A on A.AUTID = POZYCJE.AUTID
                where ROK >= 2000 AND CENA > 30
                                """;

        try {
            con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sel);
            while (rs.next()) {
                String nazwisko = rs.getString("name");
                System.out.println("Autor: " + nazwisko +  " "+ rs.getString("tytul") + " " + rs.getString("rok") + " " + rs.getString("cena"));
            }
            stmt.close();
            con.close();
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }

    }
}
