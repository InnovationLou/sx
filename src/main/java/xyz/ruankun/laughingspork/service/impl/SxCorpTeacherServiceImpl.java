package xyz.ruankun.laughingspork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.ruankun.laughingspork.entity.SxCorpTeacher;
import xyz.ruankun.laughingspork.entity.SxIdentifyForm;
import xyz.ruankun.laughingspork.entity.SxStudent;
import xyz.ruankun.laughingspork.repository.SxIdentifyFormRepository;
import xyz.ruankun.laughingspork.repository.SxStudentRepository;
import xyz.ruankun.laughingspork.service.SxCorpTeacherService;
import xyz.ruankun.laughingspork.repository.SxCorpTeacherRepository;

import java.util.Date;
import java.util.List;

@Service
public class SxCorpTeacherServiceImpl implements SxCorpTeacherService {
    @Autowired
    private SxCorpTeacherRepository resp;
    @Autowired
    private SxStudentRepository sxStudentRepository;
    @Autowired
    private SxIdentifyFormRepository sxIdentifyFormRepository;

    @Override
    public void save(SxCorpTeacher sxCorpTeacher) {
        resp.save(sxCorpTeacher);
    }

    @Override
    public List<SxStudent> getOwnedStudentsList(SxCorpTeacher sxCorpTeacher) {
        return sxStudentRepository.findByCorpTeacherNo(sxCorpTeacher.getAccount());//select * from sx_student where corp_teacher_no=?
    }

    @Override
    public SxIdentifyForm getIdentifyFormByStuId(String stuNo) {
        return sxIdentifyFormRepository.findByStuNo(stuNo);
    }

    @Override
    public SxIdentifyForm operateIdentifyForm(String stuNo, String teacherOpinion, String teacherScore, String teacherGrade) {
        //校外老师意见，校外老师打分，评定等级,操作日期
        SxIdentifyForm oldForm = sxIdentifyFormRepository.findByStuNo(stuNo);
        oldForm.setCorpTeacherOpinion(teacherOpinion);
        oldForm.setCorpTeacherScore(teacherScore);
        oldForm.setCorpTeacherGrade(teacherGrade);
        oldForm.setCTODate((java.sql.Date) new Date());
        oldForm.setCTGDate((java.sql.Date) new Date());
        sxIdentifyFormRepository.save(oldForm);
        return sxIdentifyFormRepository.findByStuNo(stuNo);
    }


    @Override
    public SxCorpTeacher findByAccount(String account) {
        return resp.findByAccount(account);
    }
}
