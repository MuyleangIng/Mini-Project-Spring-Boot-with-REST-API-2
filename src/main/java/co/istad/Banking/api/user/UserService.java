package co.istad.Banking.api.user;

import co.istad.Banking.api.user.web.CreateUserDto;
import co.istad.Banking.api.user.web.UpdateUserDto;
import co.istad.Banking.api.user.web.UserDto;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.logging.Filter;

public interface UserService {

    UserDto createNewUser(CreateUserDto createUserDto);
    UserDto findUserById(Integer id);

    PageInfo<UserDto> findAllUsers(int page, int limit,String name);
    Integer deleteUserById(Integer id);
    Integer updateIsDeletedStatus(Integer id , boolean status);

    UserDto updateUserById(int id, UpdateUserDto updateUserDto);


    UserDto findUserByStudentCardId(String studentId);
}
