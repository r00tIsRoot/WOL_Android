package com.isroot.wol_android;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IpTimeInterface {
    @GET("/cgi-bin/wol_apply.cgi?")
    Call<String> turnOnPC(
            @Query("act") String page,
            @Query("mac") String mac
    );
}
