package net.goeasyway.uploadimage.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mi on 2017/12/15.
 */

public class Ret {

    public static final String BASE_URL ="";
    Retrofit retrofit=new Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
