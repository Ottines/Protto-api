package com.protto.api.domain.user.controller;

import com.protto.api.domain.user.model.dao.UserCreateRequest;
import com.protto.api.domain.user.model.dao.UserVO;
import com.protto.api.domain.user.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;


@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Long> create(@RequestBody @Valid UserCreateRequest userCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userCreateRequest));
    }

    @GetMapping()
    public ResponseEntity<List<UserVO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserVO> findById(@NonNull @PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

}
