package bfccs.bfccs.service;

import bfccs.bfccs.domain.Event;
import bfccs.bfccs.domain.Member;
import bfccs.bfccs.repository.EventRepository;
import bfccs.bfccs.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final MemberRepository memberRepository;

    public List<Event> getEvent(LocalDate startDate, LocalDate endDate) {
        return eventRepository.findAllByYearAndMonth(startDate, endDate);
    }
}
