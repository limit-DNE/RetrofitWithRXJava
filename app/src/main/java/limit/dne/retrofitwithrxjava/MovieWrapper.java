package limit.dne.retrofitwithrxjava;

import com.squareup.moshi.Json;

import java.util.List;

public class MovieWrapper {

    @Json(name = "results")
    private List<Movie> movies;

    public List<Movie> getMovieList() {
        return movies;
    }
}
