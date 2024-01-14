package com.qizeApp.duolearn.conntroller;

import com.qizeApp.duolearn.entity.Question;
import com.qizeApp.duolearn.repo.QuizeQuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuizQuestionController
{
    @Autowired
    QuizeQuestionRepo repo;

    @PostMapping("/save")
    public Question getAll(@RequestBody Question question)
    {
         return repo.save(question);
    }

    @GetMapping("/getAll")
    public List<Question> getAll()
    {
        return repo.findAll();
    }

    @DeleteMapping("/deleteNull")
    public void deleteBynull()
    {
         repo.deleteByNull();
    }

    @PutMapping("upadte/{lang}")
    public void updateLang(@PathVariable("lang") String lang)
    {
        repo.updateLang(lang);
    }

    @GetMapping("/getAllPage/{pag}/{size}")
    public Page<Question> getPage(@PathVariable Integer pag,@PathVariable Integer size)
    {
        Pageable page= PageRequest.of(pag,size, Sort.Direction.ASC,"question");
        return  repo.findAll(page);
    }


}
