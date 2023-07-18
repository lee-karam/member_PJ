package org.spring.memberpj.repository;

import org.spring.memberpj.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  // 자동으로 설정
public interface MemberRepository extends JpaRepository<MemberEntity,Long> {


}

