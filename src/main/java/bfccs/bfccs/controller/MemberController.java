package bfccs.bfccs.controller;

import bfccs.bfccs.domain.Member;
import bfccs.bfccs.service.EventService;
import bfccs.bfccs.service.MemberService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final EventService eventService;
    private final MemberService memberService;

    @GetMapping("/members")
    public ResponseEntity<List<Member>> getMembers() {
        List<Member> memberList = memberService.getMembers();

        return new ResponseEntity<>(memberList, HttpStatus.OK);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<Member> getMember(Long id) {
        Member member = this.memberService.getMember(id);

        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PostMapping("/members")
    @ResponseBody
    public CreateMemberResponse saveMember(@RequestBody @Valid CreateMemberRequest request) {

        Member member = new Member();
        member.setName(request.getName());
        member.setSn(request.getSn());
        member.setTeam(request.getTeam());

        Long id = memberService.join(member);

        return new CreateMemberResponse(id);
    }

    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

    @Data
    static class CreateMemberRequest {
        @NotEmpty
        private String name;
        @NotEmpty
        private String sn;
        @NotEmpty
        private String team;
    }
}
