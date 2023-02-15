package bfccs.bfccs.repository;

import bfccs.bfccs.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

        public void save(Member member) {
            em.persist(member);
        }

        public Member findOne(Long id) {
            return em.find(Member.class, id);
        }

        public List<Member> findAll() {
            return em.createQuery("select m from Member m", Member.class)
                    .getResultList();
        }

        public List<Member> findByName(String name) {
        return em.createQuery("select m from Member where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }


}
