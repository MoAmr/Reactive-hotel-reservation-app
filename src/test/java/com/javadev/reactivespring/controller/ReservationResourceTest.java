package com.javadev.reactivespring.controller;

import com.javadev.reactivespring.model.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static com.javadev.reactivespring.controller.ReservationResource.ROOM_V_1_RESERVATION;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReservationResourceTest {

    @Autowired
    private ApplicationContext context;
    private WebTestClient webTestClient;
    private Reservation reservation;

    @BeforeEach
    void setUp() {

        webTestClient = WebTestClient
                .bindToApplicationContext(this.context)
                .build();

        reservation = new Reservation(123l, LocalDate.now(),
                LocalDate.now().plus(10, ChronoUnit.DAYS),
                150);
    }

    @Test
    void getAllReservations() {

        webTestClient.get()
                .uri(ROOM_V_1_RESERVATION)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Reservation.class);
    }

    @Test
    void createReservation() {

        webTestClient.post()
                .uri(ROOM_V_1_RESERVATION)
                .body(Mono.just(reservation), Reservation.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody(Reservation.class);
    }
}