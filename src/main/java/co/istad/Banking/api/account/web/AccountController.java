package co.istad.Banking.api.account.web;


import co.istad.Banking.api.account.AccountService;
import co.istad.Banking.base.BaseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/allaccounts")
    public BaseRest<?> findAll(){

        var accountDtoList = accountService.findAll();

        return BaseRest.builder().status(true)
                .code(HttpStatus.OK.value())
                .message("Account have been found")
                .timestamp(LocalDateTime.now())
                .data(accountDtoList)
                .build();

    }


}
