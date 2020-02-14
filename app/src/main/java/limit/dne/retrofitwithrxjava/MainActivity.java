package limit.dne.retrofitwithrxjava;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import limit.dne.retrofitwithrxjava.Network.NetworkModule;

public class MainActivity extends AppCompatActivity {

    List<Movie> mMovies;
    MovieListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadPopularMovies();
    }

    public void loadPopularMovies(){
        NetworkModule networkModule = new NetworkModule();
        Observable<List<Movie>> movieListObservable = networkModule.getMovieDataService().popularMovies(1).map(MovieWrapper::getMovieList);
        movieListObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::loadSuccess, this::loadFail);
    }

    public void loadSuccess(List<Movie> movieList){
        mMovies = new ArrayList<>(movieList);
        adapter = new MovieListAdapter(mMovies);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void loadFail(Throwable throwable){
        throwable.printStackTrace();
    }

}
