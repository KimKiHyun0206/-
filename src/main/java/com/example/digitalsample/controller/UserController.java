package com.example.digitalsample.controller;

import com.example.digitalsample.common.dto.PageDto;
import com.example.digitalsample.common.dto.ResponseDto;
import com.example.digitalsample.dto.request.UserRegisterRequest;
import com.example.digitalsample.dto.request.UserUpdateRequest;
import com.example.digitalsample.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest request) {
        var response = userService.register(request);
        return ResponseDto.created(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        var response = userService.get(id);
        return ResponseDto.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        var response = userService.getAll(pageable);
        return PageDto.ok(response);
    }

    @PutMapping("/{id}") // @PatchMapping
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody UserUpdateRequest request
    ) {
        var response = userService.update(id, request);
        return ResponseDto.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        userService.delete(id);
        return ResponseDto.noContent();
    }

}
