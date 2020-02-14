package limit.dne.retrofitwithrxjava;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

public class Movie implements Parcelable {

    @Json(name = "poster_path")
    private String posterPath;
    @Json(name = "title")
    private String title;

    protected Movie(Parcel input){
        posterPath = input.readString();
        title = input.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel input) {
            return new Movie(input);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(posterPath);
        dest.writeString(title);
    }
}
