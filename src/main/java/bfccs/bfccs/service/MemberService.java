package bfccs.bfccs.service;

import bfccs.bfccs.domain.Member;
import bfccs.bfccs.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        memberRepository.save(member);

        return member.getId();
    }

    /**
     *  전체 회원 조회
     */
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    /**
     * 단일 회원 조회
     */
    public Member getMember(Long id) {
        return memberRepository.findOne(id);
    }

}
