package bfccs.bfccs.controller;

import bfccs.bfccs.domain.Event;
import bfccs.bfccs.service.EventService;
import bfccs.bfccs.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@Controller
@RequiredArgsConstructor
public class EventContoller {

    private final EventService eventService;
    private final MemberService memberService;

    @GetMapping("/schedules")
    public ResponseEntity<List<Event>> getEventListByPeriod (@RequestParam("startDate") @DateTimeFormat(iso = DATE) LocalDate startDate,
                                                @RequestParam("endDate") @DateTimeFormat(iso = DATE) LocalDate endDate) {

        List<Event> eventList = eventService.getEvent(startDate, endDate);

        return new ResponseEntity<>(eventList, HttpStatus.OK);
    }

}
