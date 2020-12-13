package lab2.moop.film;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FilmDAO {

    public void showFilms(final Statement dbStatement) {
        final String sqlQueryStr = "SELECT ID, GenreID, Title, Metascore, Duration FROM Films";
        try {
            final ResultSet resultSet = dbStatement.executeQuery(sqlQueryStr);
            System.out.println("Films list:");
            System.out.println("ID - GenreID - Title - Metascore - Duration");
            while (resultSet.next()) {
                final int id = resultSet.getInt("ID");
                final int genreId = resultSet.getInt("GenreId");
                final String title = resultSet.getString("Title");
                final int metascore = resultSet.getInt("Metascore");
                final int duration = resultSet.getInt("Duration");
                System.out.println(">>" + id + " - " + title + " - " + genreId + " - " + metascore + " - " + duration);
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("ERROR while getting films >> " + e.getMessage());
        }
    }

    public void findFilmsByGenreId(final Statement dbStatement, final int genreIdParam) {
        final String sqlQueryStr = "SELECT ID, Title, GenreId, Metascore, Duration FROM Films " +
                "WHERE GenreId = " + genreIdParam;
        try {
            ResultSet rs = dbStatement.executeQuery(sqlQueryStr);
            System.out.println("Films list:");
            System.out.println("ID - GenreId - Title - Metascore - Duration");
            while (rs.next()) {
                final int id = rs.getInt("ID");
                int genreId = rs.getInt("GenreId");
                final String title = rs.getString("Title");
                final int metascore = rs.getInt("Metascore");
                final int duration = rs.getInt("Duration");
                System.out.println(">>" + id + " - " + title + " - " + genreId + " - " + metascore + " - " + duration);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("ERROR while getting films list for GenreId = " + genreIdParam + " >> " + e.getMessage());
        }
    }

    public boolean addFilm(final Statement dbStatement, final int genreId, final String title, final int metascore, final int duration) {
        final String sqlQueryStr = "INSERT INTO Model (Title, GenreId, Metascore, Duration) " +
                "VALUES ('" + title + "', " + genreId + ", " + metascore  + ", " + duration + ")";
        try {
            dbStatement.executeUpdate(sqlQueryStr);
            System.out.println("Film \"" + title + "\" added successfully");
            return true;
        } catch (SQLException e) {
            System.out.println("ERROR Film \"" + title + "\" not being added >> " + e.getMessage());
            return false;
        }
    }

    public boolean updateFilm(
            final Statement dbStatement, final int id, final int genreId, final String title, final int metascore, final int duration) {
        String sqlQueryStr = "UPDATE Films SET ID = " + id;

        if (genreId != 0) {
            sqlQueryStr += ", GenreId = " + genreId;
        }

        if ((title != null) && !title.isEmpty()) {
            sqlQueryStr += ", Title = '" + title + "'";
        }

        if (metascore > 0) {
            sqlQueryStr += ", Metascore = " + metascore;
        }

        if (duration > 0) {
            sqlQueryStr += ", Duration = " + duration;
        }

        sqlQueryStr += " WHERE ID = " + id;

        try {
            dbStatement.executeUpdate(sqlQueryStr);
            System.out.println("Film \"" + title + "\" added successfully");
            return true;
        } catch (SQLException e) {
            System.out.println("ERROR film \"" + title + "\" not being updated >> " + e.getMessage());
            return false;
        }
    }

    public boolean deleteFilm(final Statement dbStatement, int id) {
        final String sqlQueryStr = "DELETE FROM Films WHERE ID =" + id;
        try {
            int resultCode = dbStatement.executeUpdate(sqlQueryStr);
            if (resultCode > 0) {
                System.out.println("Film with ID = " + id + " deleted successfully!");
                return true;
            } else {
                System.out.println("Film with ID = " + id + " not found!");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("ERROR while deleting film >> " + e.getMessage());
            return false;
        }
    }
}