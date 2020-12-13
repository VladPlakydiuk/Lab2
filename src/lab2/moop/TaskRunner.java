package lab2.moop;

import lab2.moop.database.DatabaseConnector;
import lab2.moop.film.FilmDAO;
import lab2.moop.genre.GenreDAO;

import java.util.Scanner;

public class TaskRunner {

    private static final String DB_NAME = "VideoStore";
    private static final String DB_IP = "localhost";
    private static final int DB_PORT = 3306;

    private static final String YES = "y";
    private static final String NO_ENTERING_VAL = "0";

    private final DatabaseConnector dbConnector;

    public TaskRunner() throws Exception {
        this.dbConnector = new DatabaseConnector(DB_NAME, DB_IP, DB_PORT);
    }

    public void run() throws Exception {
        System.out.println("Start exploring genres");
        exploreGenres();
        System.out.println("");

        System.out.println("Start exploring films");
        exploreFilms();
        System.out.println("");

        dbConnector.closeConnection();
    }

    private void exploreGenres() {
        final GenreDAO genreDAO = new GenreDAO();
        genreDAO.showGenres(dbConnector.getDbStatement());
        System.out.println("");

        final Scanner inScanner = new Scanner(System.in);

        // add genres
        System.out.println("Do you want to add genres (y/n)?");
        if (YES.equals(inScanner.next().toLowerCase())) {
            genreDAO.addGenre(dbConnector.getDbStatement(), "Thriller");
            genreDAO.addGenre(dbConnector.getDbStatement(), "Sci-Fi");
            genreDAO.addGenre(dbConnector.getDbStatement(), "Drama");
            genreDAO.addGenre(dbConnector.getDbStatement(), "Animation");

            genreDAO.showGenres(dbConnector.getDbStatement());
        }


        // update genres
        System.out.println("Do you want to update genres (y/n)?");
        if (YES.equals(inScanner.next().toLowerCase())) {
            System.out.println("How many genres you want to update?");
            int numberOfGenresToUpdate = inScanner.nextInt();
            for (int i = 0; i < numberOfGenresToUpdate; ++i) {
                System.out.println("Enter GenreID: ");
                final int genreId = inScanner.nextInt();

                System.out.println("Enter genre new Name (0 - for not changing): ");
                String name = inScanner.next();
                if (NO_ENTERING_VAL.equals(name)) {
                    name = "";
                }

                genreDAO.updateGenre(dbConnector.getDbStatement(), genreId, name);
            }

            genreDAO.showGenres(dbConnector.getDbStatement());
        }


        // delete genres
        System.out.println("How many genres you want to delete?");
        final int numberOfGenresToDelete = inScanner.nextInt();
        if (numberOfGenresToDelete > 0) {
            System.out.println("Which " + numberOfGenresToDelete + " genres you want to delete (enter their ID's)?");
        }
        for (int i = 0; i < numberOfGenresToDelete; ++i) {
            final int genreId = inScanner.nextInt();
            genreDAO.deleteGenre(dbConnector.getDbStatement(), genreId);
        }

        genreDAO.showGenres(dbConnector.getDbStatement());
    }

    private void exploreFilms() {
        final FilmDAO filmDAO = new FilmDAO();
        filmDAO.showFilms(dbConnector.getDbStatement());
        System.out.println("");

        final Scanner inScanner = new Scanner(System.in);

        // search for films by genre
        System.out.println("Do you want to search for films by genre (y/n)?");
        if (YES.equals(inScanner.next().toLowerCase())) {
            System.out.println("Do you want to see a list of genres (y/n)?");
            if (YES.equals(inScanner.next().toLowerCase())) {
                final GenreDAO genre = new GenreDAO();
                genre.showGenres(dbConnector.getDbStatement());
                System.out.println("");
            }

            System.out.println("Enter GenreID: ");
            final int genreId = inScanner.nextInt();
            filmDAO.findFilmsByGenreId(dbConnector.getDbStatement(), genreId);
            System.out.println("");
        }


        // add films
        System.out.println("Do you want to add films (y/n)?");
        if (YES.equals(inScanner.next().toLowerCase())) {
            filmDAO.addFilm(dbConnector.getDbStatement(), 1, "Dragged Across Concrete", 60, 158);
            filmDAO.addFilm(dbConnector.getDbStatement(), 2, "Interstellar", 74, 148);
            filmDAO.addFilm(dbConnector.getDbStatement(),  3, "The Shawshank Redemption", 80, 142);
            filmDAO.addFilm(dbConnector.getDbStatement(),  4, "WALL-E", 95, 152);

            filmDAO.showFilms(dbConnector.getDbStatement());
            System.out.println("");
        }


        // update films
        System.out.println("Do you want to update films (y/n)?");
        if (YES.equals(inScanner.next().toLowerCase())) {
            System.out.println("How many films you want to update?");
            int numberOfFilmsToUpdate = inScanner.nextInt();
            for (int i = 0; i < numberOfFilmsToUpdate; ++i) {
                System.out.println("Enter film ID: ");
                final int filmId = inScanner.nextInt();

                System.out.println("Enter film`s new Title (0 - for not changing): ");
                String title = inScanner.next();
                if (NO_ENTERING_VAL.equals(title)) {
                    title = "";
                }

                System.out.println("Enter new GenreId (0 - for not changing): ");
                final int genreId = inScanner.nextInt();

                System.out.println("Enter new Metascore (0 - for not changing): ");
                final int metascore = inScanner.nextInt();

                System.out.println("Enter new Duration (0 - for not changing): ");
                final int duration = inScanner.nextInt();

                filmDAO.updateFilm(dbConnector.getDbStatement(), filmId, genreId, title, metascore, duration);
            }

            filmDAO.showFilms(dbConnector.getDbStatement());
            System.out.println("");
        }


        // delete films
        System.out.println("How many films you want to delete?");
        final int numberOfFilmsToDelete = inScanner.nextInt();
        if (numberOfFilmsToDelete > 0) {
            System.out.println("Which " + numberOfFilmsToDelete + " films you want to delete (enter their ID's)?");
        }
        for (int i = 0; i < numberOfFilmsToDelete; ++i) {
            final int filmId = inScanner.nextInt();
            filmDAO.deleteFilm(dbConnector.getDbStatement(), filmId);
        }

        filmDAO.showFilms(dbConnector.getDbStatement());
    }
}
