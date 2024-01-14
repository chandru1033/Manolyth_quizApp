package com.qizeApp.duolearn.repo;

import com.qizeApp.duolearn.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatusCode;

public interface QuizRepo extends JpaRepository<Quiz,Integer> {


}
