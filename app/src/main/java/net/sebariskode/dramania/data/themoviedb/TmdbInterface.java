package net.sebariskode.dramania.data.themoviedb;

import net.sebariskode.dramania.data.DramaResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by bagus on 22/10/16.
 */

public interface TmdbInterface {
    @GET("airing_today")
    Call<DramaResults> getAiringToday(
        @Query("api_key") String api,
        @Query("page") String page
    );

    @GET("popular")
    Call<DramaResults> getPopular(
            @Query("api_key") String api,
            @Query("page") String page
    );

    @GET("top_rated")
    Call<DramaResults> getTopRated(
            @Query("api_key") String api,
            @Query("page") String page
    );
}
