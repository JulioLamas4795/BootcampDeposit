package com.bootcamp.bankdeposit.service;

import com.bootcamp.bankdeposit.bean.Deposit;
import com.bootcamp.bankdeposit.dto.DepositDto;
import com.bootcamp.bankdeposit.repository.DepositRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

class DepositServiceImplTest {

    @Mock
    private DepositRepository depositRepository;
    @InjectMocks
    private DepositServiceImpl service;

    private Flux<Deposit> fluxDto;

    private Mono<DepositDto> depositDtoMono;
    private Mono<DepositDto> depositeCreated;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        fluxDto = Flux.just(new Deposit(432.00, "PEN", "001", "007", "4040710018395424", "4040710018394889", "Pepe Gordon",
                "8765432", LocalDateTime.now().toString()));

        depositDtoMono = Mono.just(new DepositDto(432.00, "PEN", "001", "007", "4040710018395424", "4040710018394889", "Pepe Gordon",
                "8765432", LocalDateTime.now().toString()));
    }

    @Test
    void getDeposit() {
        Mockito.when(depositRepository.findAll()).thenReturn(fluxDto);

        Assertions.assertNotNull(service.getDeposit());
    }


}