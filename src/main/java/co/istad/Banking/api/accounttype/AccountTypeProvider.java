package co.istad.Banking.api.accounttype;

import org.apache.ibatis.jdbc.SQL;


public class AccountTypeProvider {
    private static final String tableName="account_types";

    public String buildSelect(){
        return new SQL() {{
            //ToDO
            SELECT("*");
            FROM("account_types");
        }}.toString();
    }



    public String buildInsertSql(){
        return new SQL(){{
            INSERT_INTO(tableName);
            VALUES("name","#{a.name}");
        }}.toString();
    }


    public String buildSelectByIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("id=#{id}");

        }}.toString();
    }


    public String buildDeletedByIdSql(){
        return new SQL(){{
            DELETE_FROM(tableName);
            WHERE("id=#{id}");
        }}.toString();
    }


    public String buildUpdateByIdSql(){
        return new SQL(){{
            UPDATE(tableName);
            SET("name=#{a.name}");
            WHERE("id=#{a.id}");
        }}.toString();
    }







}
