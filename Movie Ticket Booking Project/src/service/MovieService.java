package service;

import model.Movie;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MovieService {
    private List<Movie> movies = new ArrayList<>();
    private int idCounter = 1;

    public MovieService() {
        loadMovies();
    }

    public List<Movie> getAllMovies() { return movies; }

    public void addMovie(String name, String timing, double price) {
        movies.add(new Movie(idCounter++, name, timing, price));
        saveMovies();
    }

    private void saveMovies() {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("data/movies.txt"))) {
            for(Movie m : movies){
                bw.write(m.getId() + "," + m.getName() + "," + m.getTiming() + "," + m.getPrice());
                bw.newLine();
            }
        } catch(IOException e) { e.printStackTrace(); }
    }

    private void loadMovies() {
        File file = new File("data/movies.txt");
        if(!file.exists()) return;

        int maxId = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String timing = parts[2];
                double price = Double.parseDouble(parts[3]);
                movies.add(new Movie(id, name, timing, price));
                if(id > maxId) maxId = id;
            }
        } catch(IOException e) { e.printStackTrace(); }
        idCounter = maxId + 1;
    }
}
