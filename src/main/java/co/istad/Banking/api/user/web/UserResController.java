package co.istad.Banking.api.user.web;


import co.istad.Banking.api.user.UserService;
import co.istad.Banking.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserResController {

    private final UserService userService;

    @GetMapping("/{id}")
    public BaseRest<?> findUserById(@PathVariable Integer id){
        UserDto userDto =userService.findUserById(id)   ;
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("User has been found SuccessFully")
                .data(userDto)
                .build();
    }

    @GetMapping("/{studentId}/student-card-id")
    public BaseRest<?> findUserByStudentId(@PathVariable String studentId){
        UserDto userDto = userService.findUserByStudentCardId(studentId);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("User has been SuccessFully")
                .data(userDto)
                .build();
    }


    @GetMapping
    public BaseRest<?> findAllUser
            (
                    @RequestParam(name = "page",required = false,defaultValue = "1") int page ,
                    @RequestParam(name = "limit",required = false,defaultValue = "10") int limit,
                    @RequestParam(name = "name",required = false,defaultValue = "") String name
                    )

    {
        PageInfo<UserDto> userDtoPageInfo = userService.findAllUsers(page,limit,name);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("User has been get SuccessFully")
                .data(userDtoPageInfo)
                .build();
    }

    @PostMapping
    public  BaseRest<?> createNewUser(@RequestBody @Valid CreateUserDto createUserDto) {
        UserDto userDto= userService.createNewUser(createUserDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("User has been Created SuccessFully")
                .data(userDto)
                .build();
    }




    @DeleteMapping("/{id}")
    public BaseRest<?> deleteUserById(@PathVariable Integer id){
        Integer deleteId=userService.deleteUserById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("User Has Been Deleted SuccessFully")
                .data(deleteId)
                .build();
    }


    @PutMapping("/{id}/is-deleted")
    public BaseRest<?> updateIsDeletedStatusById(@PathVariable Integer id,@RequestBody isDeletedDto isDeletedDto ){
        Integer deleteId=userService.updateIsDeletedStatus(id, isDeletedDto.status());
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("User Has Been Update Status SuccessFully")
                .data(deleteId)
                .build();
    }

    @PutMapping("/{id}")
    public BaseRest<?> updateUserById(@PathVariable("id") Integer id,@RequestBody UpdateUserDto updateUserDto ){
        UserDto userDto= userService.updateUserById(id,updateUserDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("User Has Been Updated  SuccessFully")
                .data(userDto)
                .build();
    }

}
