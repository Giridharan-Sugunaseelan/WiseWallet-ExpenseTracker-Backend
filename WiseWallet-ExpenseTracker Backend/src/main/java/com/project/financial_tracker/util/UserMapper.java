package com.project.financial_tracker.util;

import com.project.financial_tracker.dto.UserDto;
import com.project.financial_tracker.model.User;

public class UserMapper {

    //add id to dto if any issue comes
    public static UserDto mapToDto(User user){
        return new UserDto(user.getFirstName(),
                            user.getLastName(),
                            user.getEmail(),
                            user.getBalance());
    }

    public static User mapToEntity(UserDto dto){
        return new User(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getEmail(),
                dto.getBalance()
        );
    }
}
