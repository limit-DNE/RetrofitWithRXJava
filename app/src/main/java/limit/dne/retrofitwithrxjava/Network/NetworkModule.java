package limit.dne.retrofitwithrxjava.Network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class NetworkModule {

    public static final int TIMEOUT = 30000;

    public NetworkModule(){

    }

    public MovieDataService getMovieDataService(){

        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        okBuilder.connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS).addInterceptor(new ResponseInterceptor());
        OkHttpClient client = okBuilder.build();
        Retrofit.Builder retroBuilder = new Retrofit.Builder().baseUrl("https://api.themoviedb.org/").addConverterFactory(MoshiConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(client);
        Retrofit retrofit = retroBuilder.build();
        return retrofit.create(MovieDataService.class);
    }
}
