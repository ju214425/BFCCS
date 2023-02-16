package bfccs.bfccs.controller;

import bfccs.bfccs.domain.Event;
import bfccs.bfccs.domain.Member;
import bfccs.bfccs.service.EventService;
import bfccs.bfccs.service.MemberService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@Controller
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final MemberService memberService;

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getEventListByPeriod (@RequestParam("startDate") @DateTimeFormat(iso = DATE) LocalDate startDate,
                                                @RequestParam("endDate") @DateTimeFormat(iso = DATE) LocalDate endDate) {

        List<Event> eventList = eventService.getEvent(startDate, endDate);

        return new ResponseEntity<>(eventList, HttpStatus.OK);
    }

    @PostMapping("/events")
    @ResponseBody
    public CreateEventResponse createEvent(@RequestBody @Valid CreateEventRequest request) {

        Event event = new Event();
        event.setTitle(request.getTitle());
        event.setStartDate(request.getStartDate());
        event.setEndDate(request.getEndDate());

        Member member = memberService.getMember(request.getMemberId());

        event.setMember(member);


        Long id = eventService.create(event);

        return new CreateEventResponse(id);
    }

    @Data
    static class CreateEventResponse {
        private Long id;

        public CreateEventResponse(Long id) {
            this.id = id;
        }
    }

    @Data
    static class CreateEventRequest {
        @NotNull
        private Long memberId;
        @NotEmpty
        private String title;
        @NotNull
        private LocalDateTime startDate;
        @NotNull
        private LocalDateTime endDate;
    }
}
