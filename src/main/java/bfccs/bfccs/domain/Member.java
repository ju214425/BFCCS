package bfccs.bfccs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String sn;
    private String team;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Event> events = new ArrayList<>();

    //일정 추가 메소드
    public void addEvent(Event event) {
        this.events.add(event);
        event.setMember(this);
    }
}