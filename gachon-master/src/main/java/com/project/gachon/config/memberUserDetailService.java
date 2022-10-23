package com.project.gachon.config;

import com.project.gachon.domain.member.Member;
import com.project.gachon.domain.member.MemberRepository;
import com.project.gachon.domain.member.SecurityMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class memberUserDetailService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Member> memberList = memberRepository.findByEmail(username);

        if (memberList.size() == 0) {
            throw new UsernameNotFoundException("User Details not found for the user : " + username);
        }
        return new SecurityMember(memberList.get(0));
    }
}
