package com.example.app_dari.Login;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

        @SerializedName("status")
        public int resultCode;

        @SerializedName("name")
        public String name;

        public int getResultCode() {
            return resultCode;
        }

        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String token) {
            this.name = name;
        }

}