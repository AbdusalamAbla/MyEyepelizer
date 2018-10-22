package salam.com.myeyepelizer.api;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import salam.com.myeyepelizer.model.SearchResult;

public interface SearchApi {
  @GET("v3/queries/hot")
    Flowable<List<String>> getTrendingTag();
   @GET("v1/search?num=10")
  Flowable<SearchResult> query(@Query("query") String key,@Query("start") int start);
}
