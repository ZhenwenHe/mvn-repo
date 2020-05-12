package cn.edu.cug.cs.exam.io;

import cn.edu.cug.cs.exam.filters.AnswerFilter;
import cn.edu.cug.cs.exam.filters.PaperFilter;
import cn.edu.cug.cs.gtl.protos.Paper;
import cn.edu.cug.cs.gtl.protos.QuestionType;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AnswerExtractorTest {

    String filterFile = "dat/paper_filters.txt";
    String scFilterFile = "dat/singlechoice_filters.txt";
    String saFilterFile = "dat/shortanswer_filters.txt";
    String psFilterFile = "dat/problemsolving_filters.txt";
    String syFilterFile = "dat/synthesized_filters.txt";
    String answerFilterFile = "dat/answer_filters.txt";
    String answerFile = "dat/answer_template.doc";
    String paperFile = "dat/paper_template.doc";


    @Test
    public void parseText() {
        try {
            //read filters
            PaperFilter filter= new PaperFilter(filterFile);
            filter.addQuestionFilter(scFilterFile, QuestionType.QT_SINGLE_CHOICE);
            filter.addQuestionFilter(saFilterFile, QuestionType.QT_SHORT_ANSWER);
            filter.addQuestionFilter(psFilterFile, QuestionType.QT_PROBLEM_SOLVING);
            filter.addQuestionFilter(syFilterFile, QuestionType.QT_SYNTHESIZED);
            filter.setAnswerFilter(answerFilterFile);

            AnswerExtractor extractor = new AnswerExtractor(filter);
            //read paper
            ArrayList<String> ss = extractor.parseText(answerFile);
            for(String s:ss)
                System.out.println(s);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void parseAnswers() {
        try {
            //read filters
            PaperFilter filter= new PaperFilter(filterFile);
            filter.addQuestionFilter(scFilterFile, QuestionType.QT_SINGLE_CHOICE);
            filter.addQuestionFilter(saFilterFile, QuestionType.QT_SHORT_ANSWER);
            filter.addQuestionFilter(psFilterFile, QuestionType.QT_PROBLEM_SOLVING);
            filter.addQuestionFilter(syFilterFile, QuestionType.QT_SYNTHESIZED);
            filter.setAnswerFilter(answerFilterFile);

            PaperExtractor  paperExtractor=new PaperExtractor(filter);
            Paper questionPaper = paperExtractor.parsePaper(paperFile);
            AnswerExtractor extractor = new AnswerExtractor(filter);
            //read paper
            Paper paper = extractor.parseAnswers(questionPaper,answerFile);

            System.out.println("ok");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}