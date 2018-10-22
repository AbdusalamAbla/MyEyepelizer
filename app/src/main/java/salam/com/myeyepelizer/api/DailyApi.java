package salam.com.myeyepelizer.api;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import salam.com.myeyepelizer.model.Daily;

public interface DailyApi {
    @GET("v2/feed?num=2")
    Flowable<Daily> getDaily();
}
