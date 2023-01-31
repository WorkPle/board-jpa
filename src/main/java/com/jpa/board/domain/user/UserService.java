package com.jpa.board.domain.user;

import com.jpa.board.domain.entities.User;
import com.jpa.board.dtos.UserDto.*;
import com.jpa.board.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);

        return users.stream()
                .map(UserResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public UserResponse getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("해당 유저를 찾을 수 없습니다."));

        return UserResponse.from(user);
    }

    public UserResponse createUser(UserRequest request) {
        User user = userRepository.save(request.toEntity());

        return UserResponse.from(user);
    }
}
