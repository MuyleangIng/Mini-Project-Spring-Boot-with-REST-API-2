package co.istad.Banking.api.accounttype;

import co.istad.Banking.api.accounttype.web.AccountType;
import co.istad.Banking.api.accounttype.web.AccountTypeDto;
import co.istad.Banking.api.accounttype.web.CreateAccountTypeDto;
import co.istad.Banking.api.accounttype.web.UpdateAccountTypeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class AccountTypeServiceImpl implements  AccountTypeService{

    private final AccountTypeMapStruct accountTypeMapStruct;
    private final AccountTypeMapper accountTypeMapper;
    @Override
    public List<AccountTypeDto> findAll() {
        List<AccountType> accountTypes=accountTypeMapper.select();
        return accountTypeMapStruct.toDto(accountTypes);

    }

    @Override
    public AccountTypeDto createNewAccountType(CreateAccountTypeDto accountTypeDto) {
        AccountType accountType=accountTypeMapStruct.UserDtotoUser(accountTypeDto);
        accountTypeMapper.insert(accountType);
        return this.findAccountTypeById(accountType.getId());

    }


    @Override
    public AccountTypeDto findAccountTypeById(Integer id) {
        AccountType accountType=accountTypeMapper.selectById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("AccountType  %d  Not found",id)));
            return accountTypeMapStruct.UsertoUserDto(accountType);
    }

    @Override
    public Integer deleteById(Integer id) {
        boolean isExisted = accountTypeMapper.existById(id);
        if (isExisted){
        accountTypeMapper.deleteById(id);
        return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("AccountType With %d not found",id));
    }

    @Override
    public AccountTypeDto updateAccountTypeById(Integer id, UpdateAccountTypeDto updateAccountTypeDto) {
        if (accountTypeMapper.existById(id)){
            AccountType accountType =accountTypeMapStruct.UpdateUserDtoToUser(updateAccountTypeDto);
            accountType.setId(id);
            accountTypeMapper.updateById(accountType);
            return this.findAccountTypeById(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("AccountType With %d not found",id));    }

}
