package com.kvazars.px500.data.net;

import com.kvazars.px500.data.net.models.PhotoListModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * The 500px webservice api interface
 */
public interface ApiService {
    @GET("photos/search?image_size=600&rpp=100")
    Observable<PhotoListModel> search(@Query("term") String term);
}
