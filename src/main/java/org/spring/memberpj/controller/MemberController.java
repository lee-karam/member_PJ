package org.spring.memberpj.controller;

import lombok.RequiredArgsConstructor;
import org.spring.memberpj.dto.MemberDto;
import org.spring.memberpj.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
  //1
//  @Autowired
//  private MemberService memberService;

  //2.
//  private MemberService memberService;
//  private MemberController(MemberService memberService){
//    this.memberService=memberService;
//  }

  //3. @RequiredArgsConstructor
  private final MemberService memberService;

  @GetMapping({"", "/index"})
  public String index() {
    return "member/index";
  }

  @GetMapping("/join") // 회원가입페이지로 이동
  public String join() {
    return "member/join";
  }

  @PostMapping("/join") // 회원가입 실행 // name,email,phone
  public String joinOk(@ModelAttribute MemberDto memberDto) {

    int rs = memberService.memberInsert(memberDto);

    if (rs != 1) {
      System.out.println("회원가입 실패");
      return "redirect:member/index";
    } else {
      System.out.println("회원가입 성공");
      // 회원목록페이지로 이동
      return "redirect:member/memberList";
    }

  }


  // 회원수정  // id,name,email,phone
  @PostMapping("/update")
  public String updateOk(@ModelAttribute MemberDto memberDto) {
    int rs = memberService.memberUpdate(memberDto);

    if (rs != 1) {
      System.out.println("회원수정 실패");
      return "redirect:member/index";
    } else {
      System.out.println("회원수정 성공");
      // 회원목록페이지로 이동
      return "redirect:member/memberList";
    }

  }

  // 회원상세 보기
  @GetMapping("/detail/{member_id}")  // detail/1
  public String detail(@PathVariable("member_id") Long member_id,
                       Model model) {

    MemberDto member = memberService.memberDetail(member_id);

    if (member != null) {
      model.addAttribute("member", member);
      return "member/detail";
    } else {
      // 조회 회원이 없다.
      return "redirect:member/memberList";
    }


  }


}
