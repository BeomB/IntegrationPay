package com.beom.controller;

import com.beom.domain.Member;
import com.beom.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {


    private final TestService testService;

    @PostMapping("/test/create")
    public ResponseEntity<Member> create(@RequestBody Member member) {
        testService.create(member);
        return ResponseEntity.ok(member);
    }

    @GetMapping("/test/read/{userId}")
    public ResponseEntity<Member> read(@PathVariable String userId) {
        Member member = testService.read(userId);
        return ResponseEntity.ok(member);
    }

    @GetMapping("/hello")
    public String hello()
    {
        return "hello";
    }

    @PutMapping("/test/update/{userId}")
    public ResponseEntity<Member> read(@PathVariable String userId, @RequestBody Member member) {
        Member targetMember = testService.update(userId, member);
        return ResponseEntity.ok(targetMember);
    }

    @DeleteMapping("test/delete/{userId}")
    public ResponseEntity<Member> delete(@PathVariable String userId) {
        Member targetMember = testService.delete(userId);
        return ResponseEntity.ok(targetMember);
    }
}
