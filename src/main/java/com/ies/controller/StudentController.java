package com.ies.controller;

import com.ies.domain.Student;
import com.ies.service.StudentService;
import com.ies.utils.DataGridView;
import com.ies.utils.ResultObj;
import com.ies.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 学生管理控制器
 */
@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("loadAllStudent")
    public DataGridView loadAllStudent(StudentVo studentVo){
        return studentService.queryAllStudent(studentVo);
    }

    @RequestMapping("addStudent")
    public ResultObj addStudent(StudentVo studentVo){
        try{
            if(studentVo.getId()!=null){
                studentVo.setId(null);
            }
            String pwd = DigestUtils.md5DigestAsHex(studentVo.getPassword().getBytes());
            studentVo.setPassword(pwd);

            List<Student> byname = studentService.getByname(studentVo);
            if(byname.size()>=1){
                return ResultObj.USER_ERROR;
            }
            studentService.addStudent(studentVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            return ResultObj.ADD_ERROR;
        }
    }
    @RequestMapping("updateStudent")
    public ResultObj updateStudent(StudentVo studentvo){
        try{
            String pwd = DigestUtils.md5DigestAsHex(studentvo.getPassword().getBytes());
            studentvo.setPassword(pwd);
            studentService.updateStudent(studentvo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            return ResultObj.UPDATE_ERROR;
        }
    }
    @RequestMapping("deleteStudent")
    public ResultObj deleteStudent(StudentVo studentvo){
        try{
            studentService.deleteStudent(studentvo.getId());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }
    @RequestMapping("deleteBatchStudent")
    public ResultObj deleteBatchStudent(StudentVo studentvo){
        try{
            studentService.deleteBatchRole(studentvo.getIds());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }
}
