package co.istad.Banking.api.accounttype;


import co.istad.Banking.api.accounttype.web.AccountType;
import co.istad.Banking.api.accounttype.web.AccountTypeDto;
import co.istad.Banking.api.accounttype.web.CreateAccountTypeDto;
import co.istad.Banking.api.accounttype.web.UpdateAccountTypeDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring") //org.mapstruct
public interface AccountTypeMapStruct {

    List<AccountTypeDto> toDto (List<AccountType> model);

    AccountType UserDtotoUser(CreateAccountTypeDto createAccountTypeDto);

    AccountTypeDto UsertoUserDto(AccountType accountType);

    AccountType UpdateUserDtoToUser(UpdateAccountTypeDto updateAccountTypeDto);

}
