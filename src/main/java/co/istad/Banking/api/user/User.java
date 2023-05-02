package co.istad.Banking.api.user;


import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class User {

    private Integer id;
    private String name;
    private String gender;
    private  String oneSignalId;
    private String studentCardId;
    private Boolean isStudent;
    private Boolean isDeleted;

}
