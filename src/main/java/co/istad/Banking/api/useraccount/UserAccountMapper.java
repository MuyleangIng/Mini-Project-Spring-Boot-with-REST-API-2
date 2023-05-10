package co.istad.Banking.api.useraccount;


import co.istad.Banking.api.useraccount.web.UserAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserAccountMapper {

    @Select("SELECT * FROM  user_accounts")
    List<UserAccount> getAllUserAccount();

}
