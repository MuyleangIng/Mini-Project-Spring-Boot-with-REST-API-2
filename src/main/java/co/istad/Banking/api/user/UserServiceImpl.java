package co.istad.Banking.api.user;

import co.istad.Banking.api.user.web.CreateUserDto;
import co.istad.Banking.api.user.web.UpdateUserDto;
import co.istad.Banking.api.user.web.UserDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.logging.Filter;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserMapStruct userMapStruct;

    @Override
    public UserDto createNewUser(CreateUserDto createUserDto) {
        User user = userMapStruct.createUserDtoToUser(createUserDto);
        userMapper.insert(user);
        log.info("User id =" + user.getId());
        System.out.println(user);
//      return userMapStruct.userToUserDto(user);
        return this.findUserById(user.getId());
    }

    @Override
    public UserDto findUserById(Integer id) {
        User user = userMapper.selectById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with %d is not found", id)));
        return userMapStruct.userToUserDto(user);
    }


    //Page
    @Override
    public PageInfo<UserDto> findAllUsers(int page, int limit,String name) {
        //Call repo

       PageInfo<User> userPageInfo = PageHelper.startPage(page,limit)
                .doSelectPageInfo(() -> userMapper.select(name));
        return userMapStruct.userPageInfoToUserDtoPageInfo(userPageInfo);
    }

    @Override
    public Integer deleteUserById(Integer id) {
        boolean isExisted =userMapper.existById(id);
        if (isExisted){
            //Delete
            userMapper.deleteById(id);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User with %d is not found", id));
    }


    @Override
    public Integer updateIsDeletedStatus(Integer id ,boolean status) {
        boolean isExisted =userMapper.existById(id);
        if (isExisted){
            userMapper.updateIsDeletedById(id,status);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with %d is not found", id));
    }

    @Override
    public UserDto updateUserById(int id, UpdateUserDto updateUserDto) {
        if (userMapper.existById(id)){
            User user = userMapStruct.updateUserDtoToUser(updateUserDto);
            user.setId(id);
            userMapper.updateById(user);
            return this.findUserById(id);

        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with %d is not found",id));


    }


    @Override
    public UserDto findUserByStudentCardId(String studentId) {
        User user = userMapper.selectUserByStudentId(studentId.toUpperCase()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with %s is not found", studentId)));
        System.out.println(user.getName());
        return userMapStruct.userToUserDto(user);
    }


}