package zadania.zadanie12;

import java.sql.*;

public class Ins1 {

    static public void main(String[] args) {
        new Ins1();
    }

    Statement stmt;

    Ins1() {
        Connection con = null;
        String url = "jdbc:derby://localhost/ksidb";

        try {
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();

        } catch (Exception exc) {
            System.out.println(exc);
            System.exit(1);
        }
        // nazwy wydawców do wpisywania do tabeli
        String[] wyd = {"PWN", "PWE", "Czytelnik", "Amber", "HELION", "MIKOM"};

        // pierwszy numer wydawcy do wpisywania do tabeli: PWN ma numer 15, PWE ma 16, ...
        int beginKey = 15;

        String[] ins = new String[wyd.length];

        for (int i = 0; i < wyd.length; i++) {
            ins[i] = "insert into wydawca (wydid, name) values (" + beginKey++ + ", '" + wyd[i]+"')";
        }

    // ? ... tablica instrukcji SQL do wpisywania rekordów do tabeli: INSERT ...

        int insCount = 0;   // ile rekordów wpisano
        try {
            for (int i = 0; i < ins.length; i++) {
                // wpisywanie rekordów
                insCount += stmt.executeUpdate(ins[i]);
            }
            // ...
            System.out.println(insCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
//...

        // ...
        beginKey = 15;
        int delCount =  0;

        try  {
            // przygotowanie instrukcji prekompilowanej
           PreparedStatement stmt = con.prepareStatement("DELETE FROM WYDAWCA where name = ?");	// usunięcie z tabeli WYDAWCA rekordu o podanej nazwie wydawcy z tablicy wyd lub o podanym numerze wydawcy zaczynającym się od beginKey
            for (int i=0; i < wyd.length; i++)   {
                // ... ?
                stmt.setString(1, wyd[i]);
                delCount += stmt.executeUpdate();

            }
            System.out.println(delCount);
            con.close();
        } catch(SQLException exc)  {
            System.out.println(exc);
        }

        // ...


    }
}