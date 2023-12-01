package com.mini.auction.member.adapter.in.web;

import com.mini.auction.member.adapter.in.web.dto.LoginReq;
import com.mini.auction.member.adapter.in.web.dto.RegistrationInfoReq;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class AccountSteps {

    public static RegistrationInfoReq createRegistartionInfo(){
        return new RegistrationInfoReq(
            "test_name",
            "testpassword12!@",
            "test_email@dwd.com",
            "010-2123-4213"
        );
    }

    public static ExtractableResponse<Response> registrationMember(RegistrationInfoReq request) {
        return RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .when()
            .post("/account/registration")
            .then().log().all().extract();
    }


    public static LoginReq longinReq() {
        return new LoginReq(
            "test_email@dwd.com",
            "testpassword12!@"
        );
    }

    public static ExtractableResponse<Response> login(LoginReq request) {
        return RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .when()
            .post("/account/login")
            .then().log().all()
            .extract();
    }
}