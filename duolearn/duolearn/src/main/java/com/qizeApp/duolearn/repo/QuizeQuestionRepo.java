package com.qizeApp.duolearn.repo;

import com.qizeApp.duolearn.entity.Question;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface QuizeQuestionRepo extends JpaRepository<Question,Integer>
{
    @Transactional
    @Modifying
    @Query(value = "delete from question where defficulty_level is null" ,nativeQuery = true)
    public void deleteByNull();

    @Transactional
    @Modifying
    @Query(value = "update question set lang=:langu where lang is null" ,nativeQuery = true)
    public void updateLang(@Param("langu") String langu);

    @Query(value = "select * from question where lang=:catagory ORDER BY RAND() LIMIT :numQ" ,nativeQuery = true)
    List<Question> findByCatagory(@Param("catagory") String catagory, @Param("numQ") Integer numQ);
}
