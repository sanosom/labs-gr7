package co.edu.udea.compumovil.lab4weather;

import co.edu.udea.compumovil.lab4weather.POJO.Model;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by santiago on 4/28/16.
 */
public interface RestInterface {

    @GET("/weather")
    void getWheatherReport(@Query("q") String place, @Query("appid") String appId, @Query("lang") String lang, Callback<Model> cb);

}
