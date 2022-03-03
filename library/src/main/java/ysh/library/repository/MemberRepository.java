package ysh.library.repository;


import ysh.library.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원 저장
    Optional<Member> findById(Long id); // 찾기
    Optional<Member> findByName(String name);
    List<Member> findAll(); // 리스트 반환
}
