package com.example.faceshop.Retrofit;



import com.example.faceshop.Model.UserModel;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IAPI {
    @POST("forgot")
    @FormUrlEncoded
    Observable<UserModel> resetPassword(@Field("key") String key,
                                        @Field("email") String email);
}
