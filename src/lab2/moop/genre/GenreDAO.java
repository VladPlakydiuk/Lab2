package lab2.moop.genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GenreDAO {

    public void showGenres(final Statement dbStatement) {
        final String sqlQueryStr = "SELECT ID, Name FROM Genres";
        try {
            ResultSet rs = dbStatement.executeQuery(sqlQueryStr);
            System.out.println("Genres list:");
            System.out.println("ID - Name");
            while (rs.next()) {
                final int id = rs.getInt("ID");
                final String name = rs.getString("Name");
                System.out.println(">>" + id + " - " + name);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("ERROR while getting genres list");
            System.out.println(" >> " + e.getMessage());
        }
    }

    public boolean addGenre(final Statement dbStatement, final String name) {
        final String sqlQueryStr = "INSERT INTO Genres (Name) " + "VALUES ('" + name + "')";
        try {
            dbStatement.executeUpdate(sqlQueryStr);
            System.out.println("Genre \"" + name + "\" have been added successfully");
            return true;
        } catch (SQLException e) {
            System.out.println("ERROR genre \"" + name + "\" not being added >> " + e.getMessage());
            return false;
        }
    }

    public boolean updateGenre(final Statement dbStatement, final int id, final String name) {
        final String sqlQueryStr = "UPDATE Genres SET Name = '" + name + "' WHERE ID = " + id;

        try {
            dbStatement.executeUpdate(sqlQueryStr);
            System.out.println("Genre \"" + name + "\" have been updated successfully");
            return true;
        } catch (SQLException e) {
            System.out.println("ERROR genre \"" + name + "\" not being updated >> " + e.getMessage());
            return false;
        }
    }

    public boolean deleteGenre(final Statement dbStatement, final int id) {
        final String sqlQueryStr = "DELETE FROM Genres WHERE ID =" + id;
        try {
            int resultCode = dbStatement.executeUpdate(sqlQueryStr);
            if (resultCode > 0) {
                System.out.println("Genre with ID = " + id + "have been deleted successfully");
                return true;
            } else {
                System.out.println("Genre with ID = " + id + " not found");

                return false;
            }
        } catch (SQLException e) {
            System.out.println("ERROR while deleting genre with ID = " + id + " >> " + e.getMessage());
            return false;
        }
    }
}
