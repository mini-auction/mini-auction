//package com.mini.auction.auction.adapter.in.web;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.mini.auction.auction.adapter.in.web.dto.CreateAuction;
//import com.mini.auction.auction.application.port.in.AuctionService;
//import com.mini.auction.auction.application.port.out.AuctionPort;
//import com.mini.auction.auction.application.port.out.AuctionTermsMappingLogPort;
//import com.mini.auction.auction.application.port.out.FindTermsPort;
//import com.mini.auction.auction.application.service.AuctionServiceImpl;
//import com.mini.auction.config.jwt.JwtService;
//import com.mini.auction.member.adapter.out.persistence.infrastructure.MemberCustomRepository;
//import com.mini.auction.member.application.port.out.FindMemberPort;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDateTime;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.hamcrest.Matchers.equalTo;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@MockBean(JpaMetamodelMappingContext.class)
//@WebMvcTest(controllers = AuctionController.class)
//class AuctionControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private ObjectMapper objectMapper;
//    @MockBean
//    private AuctionService auctionService;
//    @MockBean
//    private JwtService jwtService;
//
//    @Test
//    @DisplayName("경매등록 성공") // 컨트롤러까지만 진입하고 auctionService 까지 안들어가는 것 같다. ..
//    void createAuction() throws Exception {
//        CreateAuction createAuction = new CreateAuction(
//          "sellerId",
//          "title",
//          "contents",
//            LocalDateTime.of(2023,1,1,0,0,0),
//            LocalDateTime.of(2023,1,1,0,0,0),
//            5000,
//            true,
//          "termsId"
//        );
//
//        mockMvc.perform(post("/auction")
//            .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(createAuction))
////                    .header(HttpHeaders.AUTHORIZATION, "토큰") // 이렇게 토큰 값 넣으면 됨.
//            )
//            .andExpect(status().isOk());
//    }
//
//    @Test
//    @DisplayName("경매등록 실패 - validation failed")
//    void failCreateAuction() throws Exception {
//        CreateAuction createAuction = new CreateAuction(
//            "sellerId",
//            "title",
//            "contents",
//            LocalDateTime.of(2023,1,1,0,0,0),
//            LocalDateTime.of(2023,1,1,0,0,0),
//            5000,
//            true,
//            ""
//        );
//
//        mockMvc.perform(post("/auction")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(createAuction))
//            )
//            .andExpect(status().is4xxClientError());
//    }
//}