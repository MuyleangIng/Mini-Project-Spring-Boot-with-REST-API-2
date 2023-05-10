package co.istad.Banking.api.account;


import co.istad.Banking.api.account.web.AccountDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapStruct {

    //account => accountDto
    List<AccountDto> toDto ( List<Account> accounts);

    //accountDto => account
//    List<Account> toAccount(List<AccountDto> accountDto);

}
