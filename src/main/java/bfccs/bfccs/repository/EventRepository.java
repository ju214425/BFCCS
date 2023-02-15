package bfccs.bfccs.repository;

import bfccs.bfccs.domain.Event;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public class EventRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Event event) {
        em.persist(event);
    }

    public Event findOne(Long id) {
        return em.find(Event.class, id);
    }

    public List<Event> findAll() {
        return em.createQuery("select e from Event e", Event.class)
                .getResultList();
    }

    public List<Event> findAllByYearAndMonth(LocalDate startDate, LocalDate endDate) {
        int startYear = startDate.getYear();
        int startMonth = startDate.getMonthValue();
        int startDay = startDate.getDayOfMonth();
        int endYear = endDate.getYear();
        int endMonth = endDate.getMonthValue();
        int endDay = endDate.getDayOfMonth();

        String jpql = "select e from Event e"
        +   " where (YEAR(e.startDate) <= :startYear and MONTH(e.startDate) <= :startMonth and DAY(e.startDate) <= :startDay)"
        +   " and (YEAR(e.endDate) >= :endYear and MONTH(e.endDate) >= :endMonth and DAY(e.endDate) >= :endDay)";



        return em.createQuery(jpql, Event.class)
                .setParameter("startYear", startYear)
                .setParameter("startMonth", startMonth)
                .setParameter("startDay", startDay)
                .setParameter("endYear", endYear)
                .setParameter("endMonth", endMonth)
                .setParameter("endDay", endDay)
                .getResultList();
    }

}
