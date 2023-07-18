package org.spring.memberpj;

import org.junit.jupiter.api.Test;
import org.spring.memberpj.dto.MemberDto;
import org.spring.memberpj.entity.MemberEntity;
import org.spring.memberpj.repository.MemberRepository;
import org.spring.memberpj.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class MemberTest {


  @Autowired
  private MemberService memberService;

  @Autowired
  private MemberRepository memberRepository;

  @Test
  void insertTest() {

    MemberDto memberDto = new MemberDto();
    memberDto.setName("m3");
    memberDto.setEmail("m3@gami.com");
    memberDto.setPhone("01000000000");

    MemberEntity memberEntity
            = MemberEntity.toMemberEntity(memberDto);
    Long member_id = memberRepository.save(memberEntity).getId();// id
    Optional<MemberEntity> memberEntityOptional
            = memberRepository.findById(member_id);
    if (memberEntityOptional.isPresent()) {
      System.out.println("회원가입 성공");
    } else {
      System.out.println("회원가입 실패");
    }
  }

  @Test
  void updateTest() {

    MemberDto memberDto = new MemberDto();
    memberDto.setId(1L);
    memberDto.setName("m2_수정");
    memberDto.setEmail("m1@gami.com");
    memberDto.setPhone("99999999");

    MemberEntity memberEntity
            = MemberEntity.toMemberUpdateEnity(memberDto);
    Long member_id = memberRepository.save(memberEntity).getId();// id
    Optional<MemberEntity> memberEntityOptional
            = memberRepository.findById(member_id);
    if (memberEntityOptional.isPresent()) {
      System.out.println("회원수정 성공");
    } else {
      System.out.println("회원수정 실패");
    }
  }

  @Test
  void memberDetail() {
    //findById();
    //    select * from member_tb_02 where member_id=1;
    // id에 해당사는 회원를 조회
    MemberDto memberDto = null;
    Optional<MemberEntity> memberEntityOptional
            = memberRepository.findById(3L);
    // memberRepository.findByEmail();
    // Entity -> Dto -> 브라우저에 표시
    if (memberEntityOptional.isPresent()) {  //회원이 존재 O
      System.out.println("회원존재 합니다.");
      // memberEntityOptional.get() -> Entity
      memberDto = MemberDto.toMemberDto(memberEntityOptional.get());
    } else {
      //회원이 존재 X
      System.out.println("회원존재 하지 않습니다.");
    }
    System.out.println(memberDto.getId());
    System.out.println(memberDto.getPhone());
    System.out.println(memberDto.getName());
    System.out.println(memberDto.getName());
    System.out.println(memberDto.getCreateDateTime());
  }

  @Test
  void memberListTest() {

    List<MemberDto> memberDtoList = new ArrayList<>();

    List<MemberEntity> memberEntityList= memberRepository.findAll();

    if(!memberEntityList.isEmpty()){
      // List Entity -> Dto 변환
      for (MemberEntity memberEntity : memberEntityList) {
        MemberDto memberDto = MemberDto.toMemberDto(memberEntity);
        memberDtoList.add(memberDto);
      }
      System.out.println("회원목록");
      for (MemberDto memberDto : memberDtoList) {
        System.out.print("id:" + memberDto.getId());
        System.out.print("name: " + memberDto.getName());
        System.out.print("phone: " + memberDto.getPhone());
        System.out.print("email: " + memberDto.getEmail());
        System.out.println("가입날짜: " + memberDto.getCreateDateTime());
      }
    }else {
        System.out.println("조회할 회원이 없다.");
    }
  }

  @Test
  void deleteTest(){
  // 회원삭제
    //memberRepository.delete(Entity); // 자동으로  id체크 후에 있으면 삭제
     MemberEntity memberEntity= MemberEntity.builder()
            .id(3L)
            .build();

// memberRepository.delete(memberEntity);

//    memberRepository.deleteById(4L);

  }




}
