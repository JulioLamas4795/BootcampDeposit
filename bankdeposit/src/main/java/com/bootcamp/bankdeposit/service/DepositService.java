package com.bootcamp.bankdeposit.service;

import com.bootcamp.bankdeposit.bean.Deposit;
import com.bootcamp.bankdeposit.dto.AccountDto;
import com.bootcamp.bankdeposit.dto.DepositDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DepositService {

    Flux<DepositDto> getDeposit();

    Mono<DepositDto> getDepositById(String id);

    Flux<Deposit> findAllByIdClient(String idClient);

    //Mono<DepositDto> getDepositByName(String name);

    //Mono<DepositDto> getDepositByDepositNumber(String depositNumber);

    Mono<AccountDto> doTransfer(DepositDto depositDto);

    Mono<DepositDto> saveDeposit(Mono<DepositDto>  depositDtoMono);

    Mono<DepositDto> updateDeposit(Mono<DepositDto> depositDtoMono, String id);

    Mono<Void> deleteDeposit(String id);

}
