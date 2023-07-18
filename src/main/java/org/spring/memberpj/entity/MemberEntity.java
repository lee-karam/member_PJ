package org.spring.memberpj.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.spring.memberpj.dto.MemberDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder      // Builder -> 생성자 패턴 중 하나
@NoArgsConstructor  //기본생성자
@AllArgsConstructor //모든 필드 생성자
@Getter
@Setter
@Entity
@Table(name = "member_tb_02")
public class MemberEntity { // member_entity

  @Id // 기본키
  @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
  @Column(name = "member_id")  // 칼럼명
  private Long id;
          // 테이블 이름,   not null
  @Column(name = "member_name",nullable = false)
  private String name;

  @Column(name = "member_phone",nullable = false)
  private String phone;

  @Column(name = "member_email", nullable = false, unique = true)
  private String email;

  // 생성, 수정 시간
  @CreationTimestamp //생성 시간 자동
  @Column(updatable = false)  // 수정할 때 X
  private LocalDateTime createDateTime;

  @UpdateTimestamp  // 수정 시간 자동
  @Column(insertable = false) // 처음 생성 시간 X
  private LocalDateTime updateDateTime;

  // Dto -> Entity

  // 1. 회원가입
  public static MemberEntity toMemberEntity(MemberDto memberDto){
    MemberEntity memberEntity=new MemberEntity();
    memberEntity.setEmail(memberDto.getEmail());
    memberEntity.setName(memberDto.getName());
    memberEntity.setPhone(memberDto.getPhone());
    return memberEntity;
  }

  // 2. 회원수정 ->id를 추가
  public static MemberEntity toMemberUpdateEnity(MemberDto memberDto){
    MemberEntity memberEntity=new MemberEntity();
    memberEntity.setId(memberDto.getId());
    memberEntity.setEmail(memberDto.getEmail());
    memberEntity.setName(memberDto.getName());
    memberEntity.setPhone(memberDto.getPhone());

    return memberEntity;
  }

  // 3. 회원목록조히

  // 4. 회원 상세 조회









}
