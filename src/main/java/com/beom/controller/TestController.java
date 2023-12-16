package com.beom.controller;

import com.beom.domain.Member;
import com.beom.service.TestService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TestController {


    private final TestService testService;

    @PostMapping("/test/create")
    @ResponseBody
    public ResponseEntity<Member> create(@RequestBody Member member) {
        testService.create(member);
        return ResponseEntity.ok(member);
    }

    @GetMapping("/test/read/{userId}")
    @ResponseBody
    public ResponseEntity<Member> read(@PathVariable String userId) {
        Member member = testService.read(userId);
        return ResponseEntity.ok(member);
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello()
    {
        return "hello";
    }

    @PutMapping("/test/update/{userId}")
    @ResponseBody
    public ResponseEntity<Member> update(@PathVariable String userId, @RequestBody Member member) {
        Member targetMember = testService.update(userId, member);
        return ResponseEntity.ok(targetMember);
    }

    @DeleteMapping("test/delete/{userId}")
    @ResponseBody
    public ResponseEntity<Member> delete(@PathVariable String userId) {
        Member targetMember = testService.delete(userId);
        return ResponseEntity.ok(targetMember);
    }


    @GetMapping("/step1")
    public String step1() {
        log.info("STEP 1 -> mode : {}");
        return "/logtest/step1";
    }


    @PostMapping("/step2")
    public String step2(HttpServletResponse response, Model model, @RequestParam("PayMethod") String paymethod, @RequestParam("GoodsCount") String goodsCount, @RequestParam("GoodsName") String goodsName, @RequestParam("Amount") String amount) {
        String traceId = response.getHeader("traceId");
        model.addAttribute("traceId", traceId);
        model.addAttribute("PayMethod",paymethod);
        model.addAttribute("GoodsCount",goodsCount);
        model.addAttribute("GoodsName",goodsName);
        model.addAttribute("Amount",amount);
        log.info("STEP 2 -> mode : {}", model.toString());
        return "/logtest/step2";
    }

    @PostMapping("/step3")
    public String step3(HttpServletResponse response, Model model, @RequestParam("PayMethod") String paymethod, @RequestParam("GoodsCount") String goodsCount, @RequestParam("GoodsName") String goodsName, @RequestParam("Amount") String amount) {
        String traceId = response.getHeader("traceId");
        model.addAttribute("traceId", traceId);
        model.addAttribute("PayMethod",paymethod);
        model.addAttribute("GoodsCount",goodsCount);
        model.addAttribute("GoodsName",goodsName);
        model.addAttribute("Amount",amount);
        log.info("STEP 3 -> mode : {}", model.toString());
        return "/logtest/step3";
    }
}
