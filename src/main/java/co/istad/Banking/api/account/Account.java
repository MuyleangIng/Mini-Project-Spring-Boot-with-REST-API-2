package co.istad.Banking.api.account;


import co.istad.Banking.api.accounttype.web.AccountType;
import co.istad.Banking.api.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {


    private Integer id;
    private String accountNo;
    private String accountName;
    private String profile;
    private Integer pin;
    private String password;
    private String phoneNumber;
    private Integer transferLimit;
    private AccountType  accountType;




}
