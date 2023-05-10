package co.istad.Banking.api.useraccount.web;

import co.istad.Banking.api.account.Account;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserAccount {

    private Integer id;

    private Integer userId;

    private Integer accountId;

    private LocalDateTime createdAt;

    private  boolean is_disabled;

    private List<Account> accounts;


}
