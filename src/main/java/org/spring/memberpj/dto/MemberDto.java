package org.spring.memberpj.dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.spring.memberpj.entity.MemberEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
@Builder      // Builder -> 생성자 패턴 중 하나
@NoArgsConstructor  //기본생성자
@AllArgsConstructor //모든 필드 생성자
@Getter
@Setter
public class MemberDto {

  private Long id;

  private String name;

  private String phone;

  private String email;

  private LocalDateTime createDateTime;

  private LocalDateTime updateDateTime;

  //  Entity -> Dto

  // 1. 회원목록조회

  // 2. 회원 상세 조회
  public  static MemberDto toMemberDto(MemberEntity memberEntity){
    MemberDto memberDto=new MemberDto();
    memberDto.setId(memberEntity.getId());
    memberDto.setEmail(memberEntity.getEmail());
    memberDto.setPhone(memberEntity.getPhone());
    memberDto.setName(memberEntity.getName());
    memberDto.setCreateDateTime(memberEntity.getCreateDateTime());
    return memberDto;
  }





}
