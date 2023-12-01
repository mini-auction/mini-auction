package com.mini.auction.member.adapter.in.web;

import com.mini.auction.ApiTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class AccountControllerTest extends ApiTest {

    @Test
    void registrationMember() {

        final var request  = AccountSteps.createRegistartionInfo();

        final var response = AccountSteps.registrationMember(request);

        assertEquals(response.statusCode(), HttpStatus.OK.value());
    }


    @Test
    void login() {

        AccountSteps.registrationMember(AccountSteps.createRegistartionInfo());

        final var request = AccountSteps.longinReq();

        ExtractableResponse<Response> response = AccountSteps.login(request);

        assertEquals(response.statusCode(), HttpStatus.OK.value());
        assertFalse(response.header(HttpHeaders.AUTHORIZATION).isEmpty());
    }

}