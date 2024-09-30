/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sandra
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class controller {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/R2CICLO3";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private view views;

    public controller(view views) {
        this.views = views;
    }

    public void fetchAndDisplayResults() {
        List<model> results = new ArrayList<>();

        try(Connection connection = DriverManager.getConnection(DB_URL,USER,PASSWORD)) {
            String query = "SELECT title, author, link FROM datosAPI";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String link = resultSet.getString("link");

                results.add(new model(title, author, link));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Mostrar resultados
        views.displayResults(results);
    }
}
