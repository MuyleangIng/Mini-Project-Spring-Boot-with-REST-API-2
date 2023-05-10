package co.istad.Banking.api.account;


import co.istad.Banking.api.accounttype.AccountTypeProvider;
import co.istad.Banking.api.accounttype.web.AccountType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AccountMapper {


    @SelectProvider(type = AccountProvider.class,method = "buildSelectSql")
    @Results(id = "accountResult",value = {
            @Result(column ="account_type",property="accountType", one =@One(select = "getAccountType"))
    })
    List<Account> select();


    @Select("SELECT * FROM  account_types WHERE id = #{account_type}")
    AccountType getAccountType(Integer accountType);




}
