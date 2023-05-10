package co.istad.Banking.api.account;

import org.apache.ibatis.jdbc.SQL;

public class AccountProvider {


    private static final String tableName="accounts";


    public String buildSelectSql(){
        return  new SQL(){{
            SELECT("*");
            FROM(tableName);
        }}.toString();
    }


}
