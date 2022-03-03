package ysh.library.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ysh.library.domain.Member;

import java.util.Optional;

// 지가 bean에 등록해줌
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}
