package co.istad.Banking.api.account;

import co.istad.Banking.api.account.web.AccountDto;

import java.util.List;

public interface AccountService {



    List<AccountDto> findAll();


}
