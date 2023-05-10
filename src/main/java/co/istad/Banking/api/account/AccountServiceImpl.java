package co.istad.Banking.api.account;

import co.istad.Banking.api.account.web.AccountDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountMapStruct accountMapStruct;

    private final AccountMapper accountMapper;
    @Override
    public List<AccountDto> findAll() {
        List<Account> accounts=accountMapper.select();

        return accountMapStruct.toDto(accounts);
    }
}
