package com.mini.auction.member.adapter.in.web;

import com.mini.auction.ApiTest;
import com.mini.auction.member.adapter.in.web.dto.RegistrationInfoReq;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;

class AccountControllerTest extends ApiTest {

    @Test
    void registrationMember() {

        final var request  = new RegistrationInfoReq(
            "test_name",
            "testpassword12!@",
            "test_email@dwd.com",
            "010-2123-4213"
        );

        final var response = registrationMember(request);

        assertEquals(response.statusCode(), HttpStatus.OK.value());
    }

    private ExtractableResponse<Response> registrationMember(RegistrationInfoReq request) {
        return RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .when()
            .post("/account/registration")
            .then().log().all().extract();
    }

}