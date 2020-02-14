package limit.dne.retrofitwithrxjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    private List<Movie> movieList;
    private Context context;
    private final String BASE_POSTER_PATH = "https://image.tmdb.org/t/p/w342";

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        public ImageView posterImage;
        public TextView titleText;
        public Movie movie;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            posterImage = (ImageView) itemView.findViewById(R.id.imageViewPoster);
            titleText = (TextView) itemView.findViewById(R.id.textViewTitle);
        }
    }

    public MovieListAdapter(List<Movie> movieList){
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_view_row, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.movie = movieList.get(position);
        holder.titleText.setText(holder.movie.getTitle());
        RequestOptions options = new RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).priority(Priority.HIGH);
        Glide.with(context).asBitmap().load(BASE_POSTER_PATH + holder.movie.getPosterPath()).apply(options).into(holder.posterImage);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
