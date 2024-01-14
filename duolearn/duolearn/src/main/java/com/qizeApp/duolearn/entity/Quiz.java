package com.qizeApp.duolearn.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Entity
@Data
@Setter
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String title;
    @ManyToMany
    List<Question> quests;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuests(List<Question> quests) {
        this.quests = quests;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Question> getQuests() {
        return quests;
    }
}
