package org.spring.memberpj.service;

import lombok.RequiredArgsConstructor;
import org.spring.memberpj.dto.MemberDto;
import org.spring.memberpj.entity.MemberEntity;
import org.spring.memberpj.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
  // Repository
  private final MemberRepository memberRepository;

  //회원가입
  @Transactional // insert,update,delete
  public int memberInsert(MemberDto memberDto) {
    //Dto -> Entity
    MemberEntity memberEntity
            = MemberEntity.toMemberEntity(memberDto);
    //회원가입
//    memberRepository.save(memberEntity);
    Long member_id = memberRepository.save(memberEntity).getId();// id
    // id가 있는지  테이블의 레코드 조회
    Optional<MemberEntity> memberEntityOptional = memberRepository.findById(member_id);
    // id에 해당하는 레코드가 존재 하변
    if (memberEntityOptional.isPresent()) {
      System.out.println("회원가입 성공");
      return 1;
    } else {
      System.out.println("회원가입 실패");
      return 0;
    }
  }


  //회원수정
  @Transactional
  public int memberUpdate(MemberDto memberDto) {
    //Dto -> Entity
    MemberEntity memberEntity
            = MemberEntity.toMemberUpdateEnity(memberDto);
    //회원수정
//    memberRepository.save(memberEntity);
    Long member_id = memberRepository.save(memberEntity).getId();// id
    // id가 있는지  테이블의 레코드 조회
    Optional<MemberEntity> memberEntityOptional = memberRepository.findById(member_id);
    // id에 해당하는 레코드가 존재 하변
    if (memberEntityOptional.isPresent()) {
      System.out.println("회원수정 성공");
      return 1;
    } else {
      System.out.println("회원수정 실패");
      return 0;
    }
  }

  // 회원상세정보
  public MemberDto memberDetail(Long memberId) {
    MemberDto memberDto = null;

    Optional<MemberEntity> memberEntityOptional
            = memberRepository.findById(memberId);
    if (memberEntityOptional.isPresent()) {
      System.out.println("회원존재 합니다.");
      memberDto = MemberDto.toMemberDto(memberEntityOptional.get());
    } else {
      System.out.println("회원존재 하지 않습니다.");
    }
    return memberDto;
  }


  // 전체회원목록 조회
  public List<MemberDto> memberListDo() {

    List<MemberDto> memberDtoList = new ArrayList<>();
    List<MemberEntity> memberEntityList = memberRepository.findAll();

    if(!memberEntityList.isEmpty()){  // 조회할 회원목록 O
      for (MemberEntity memberEntity : memberEntityList) {
        // Build
/*      MemberDto memberDto = MemberDto.builder()
                            .id(memberEntity.getId())
                            .name(memberEntity.getName())
                            .phone(memberEntity.getPhone())
                            .email(memberEntity.getEmail())
                            .createDateTime(memberEntity.getCreateDateTime())
                            .build();*/
        //Entity-> Dto
        MemberDto memberDto = MemberDto.toMemberDto(memberEntity);
        //Entity-> Dto -> List<Dto>
        memberDtoList.add(memberDto);
      }
    }
    return memberDtoList;

  }


  @Transactional
  public int memberDelete(Long memberId) {
    MemberEntity memberEntity= MemberEntity.builder()
            .id(memberId)
            .build();
    memberRepository.delete(memberEntity);
    Optional<MemberEntity> memberEntityOptional
            = memberRepository.findById(memberEntity.getId());
    if(!memberEntityOptional.isPresent()){
      System.out.println("회원삭제 성공");
      return 1;
    }
    return 0;
//    memberRepository.findById(memberEntity.getId())
//    memberRepository.deleteById(memberEntity.getId());

  }
}
