package com.bootcamp.bankdeposit.controller;

import com.bootcamp.bankdeposit.bean.Deposit;
import com.bootcamp.bankdeposit.dto.DepositDto;
import com.bootcamp.bankdeposit.repository.DepositRepository;
import com.bootcamp.bankdeposit.service.DepositService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest(DepositController.class)
class DepositControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private DepositService depositService;

    @Test
    public void addSaveDepositTest(){

        Mono<DepositDto> clientDtoMono=Mono.just(new DepositDto(432.00, "PEN", "001", "007", "4040710018395424", "4040710018394889", "Pepe Gordon",
                "8765432", LocalDateTime.now().toString()));
        when(depositService.saveDeposit(clientDtoMono)).thenReturn(clientDtoMono);

        webTestClient.post().uri("/api/deposit/create")
                .body(Mono.just(clientDtoMono),DepositDto.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void getAllDepositTest(){
        Flux<DepositDto> clientDtoFlux=Flux.just(new DepositDto(432.00, "PEN", "001", "007", "4040710018395424", "4040710018394889", "Pepe Gordon",
                        "8765432", LocalDateTime.now().toString()),
                new DepositDto(438.00, "PEN", "002", "007", "4040710018395455", "4040710018394877", "Antonio yacom",
                        "8765433", LocalDateTime.now().toString()));
        when(depositService.getDeposit()).thenReturn(clientDtoFlux);

        Flux<DepositDto> responseBody=webTestClient.get().uri("/api/deposit")
                .exchange()
                .expectStatus().isOk()
                .returnResult(DepositDto.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(new DepositDto(432.00, "PEN", "001", "007", "4040710018395424", "4040710018394889", "Pepe Gordon",
                        "8765432", LocalDateTime.now().toString()))
                .expectNext(new DepositDto(438.00, "PEN", "002", "007", "4040710018395455", "4040710018394877", "Antonio yacom",
                        "8765433", LocalDateTime.now().toString()));
    }

    @Test
    public void getObtenerDepositIdTest(){
        Mono<DepositDto> DepositDtoMono=Mono.just(new DepositDto(432.00, "PEN", "001", "007", "4040710018395424", "4040710018394889", "Pepe Gordon",
                "8765432", LocalDateTime.now().toString()));
        when(depositService.getDepositById(any())).thenReturn(DepositDtoMono);

        Flux<DepositDto> responseBody = webTestClient.get().uri("/api/deposit/001")
                .exchange()
                .expectStatus().isOk()
                .returnResult(DepositDto.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNextMatches(p->p.getIdClient().equals("001"))
                .verifyComplete();
    }

    @Test
    public void updateClientTest(){
        Mono<DepositDto> depositDtoMono=Mono.just(new DepositDto(432.00, "PEN", "001", "007", "4040710018395424", "4040710018394889", "Pepe Gordon",
                "8765432", LocalDateTime.now().toString()));
        when(depositService.updateDeposit(depositDtoMono,"001")).thenReturn(depositDtoMono);

        webTestClient.put().uri("/api/deposit/001")
                .body(Mono.just(depositDtoMono),DepositDto.class)
                .exchange()
                .expectStatus().isOk();//200
    }

    @Test
    public void deleteClientIdTest(){
        given(depositService.deleteDeposit(any())).willReturn(Mono.empty());
        webTestClient.delete().uri("/api/deposit/001")
                .exchange()
                .expectStatus().isOk();//200
    }

}