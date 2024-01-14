package com.qizeApp.duolearn.conntroller;

import com.qizeApp.duolearn.entity.Question;
import com.qizeApp.duolearn.entity.Quiz;
import com.qizeApp.duolearn.dto.QuizWrapper;
import com.qizeApp.duolearn.dto.Response;
import com.qizeApp.duolearn.repo.QuizRepo;
import com.qizeApp.duolearn.repo.QuizeQuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/create")
public class QuizController {

    @Autowired
    QuizRepo quizRepo;

    @Autowired
    QuizeQuestionRepo quizeQuestionRepo;

    @PostMapping("/quiz/{catagory}/{numQ}/{title}")
    public String createQuiz(@PathVariable String catagory,@PathVariable Integer numQ,@PathVariable String title)
    {
        List<Question> questions=quizeQuestionRepo.findByCatagory(catagory,numQ);
        Quiz quiz=new Quiz();
        quiz.setQuests(questions);
        quiz.setTitle(title);
        quizRepo.save(quiz);

        return "Success";
    }

    @GetMapping("/getQuiz/{Quizid}")
    public ResponseEntity<List<QuizWrapper>> createQuiz(@PathVariable("Quizid") Integer numQ) {
        Optional<Quiz> quiz=quizRepo.findById(numQ);
        List<Question> questions=quiz.get().getQuests();
        List<QuizWrapper> qw=new ArrayList<>();
        for (Question q:questions) {
            QuizWrapper qwo=new QuizWrapper(q.getId(),q.getQuestion(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            qw.add(qwo);
        }
        return new ResponseEntity<>(qw,HttpStatus.OK);
    }

    @PostMapping(value = "/submit/{id}",consumes = {"application/json"})
    public ResponseEntity<String> checkResult(@PathVariable("id") Integer id,@RequestBody List<Response> responses)
    {
        Quiz quiz=quizRepo.findById(id).get();
        List<Question> ques=quiz.getQuests();
        int i=0;
        int res=0;
        for(Response rs:responses)
        {
            if(rs.getResponse().equalsIgnoreCase(ques.get(i).getAnswer()))
            {
                res++;
            }
            i++;

        }
        return new ResponseEntity<>(res+" is your score",HttpStatus.OK);
    }
    
}
