package limit.dne.retrofitwithrxjava.Network;

import io.reactivex.Observable;
import limit.dne.retrofitwithrxjava.MovieWrapper;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDataService {

    @GET("3/discover/movie?language=en&sort_by=popularity.desc")
    Observable<MovieWrapper> popularMovies(@Query("page") int page);
}
