package com.example.app_dari;

import com.example.app_dari.Interest.Its_Response;
import com.example.app_dari.Interest.Its_Request;
import com.example.app_dari.Login.LoginActivity;
import com.example.app_dari.Login.LoginRequest;
import com.example.app_dari.Login.LoginResponse;
import com.example.app_dari.Login.LoginResponse;
import com.example.app_dari.Signup.SignupRequest;
import com.example.app_dari.Signup.SignupResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface initMyApi {


    @POST("/api/token")
    Call<LoginResponse> getLoginResponse(@Body LoginRequest loginRequest);

    @GET("/api/token/test")
    Call<LoginResponse> getLogin(@Header("authorization") String token);

    @POST("/api/auth/sign-up")
    Call<SignupResponse> getSignupResponse(@Body SignupRequest signupRequest);

    @PUT("/user/{id}/profile/interest")
    Call<Its_Response> getIts_Response(@Path("id") String id , @Body Its_Request its_resquest);

}
