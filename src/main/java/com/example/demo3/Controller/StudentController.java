package com.example.demo3.Controller;

import com.example.demo3.Entity.Student;
import com.example.demo3.Repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "student")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentRepository studentRepository;

    //查询所有学生
    @RequestMapping(value = "/students",method = RequestMethod.GET)
    public Object queryAllStudent(){
        Map<String, Object> map = new HashMap<>();
        List<Student> studentList = studentRepository.findAll();
        try{
            map.put("status","查询成功");
            map.put("list",studentList);
        }catch (Exception e){
            map.put("status","failure");
            map.put("errMsg",e.getMessage());
        }finally {
        }
        return map;
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public Object addStudent(Student student){
        Map<String, Object> map = new HashMap<>();
        Student save = studentRepository.save(student);
        try{
            if (save != null) {
                map.put("status","创建成功");
                map.put("student",student);
            }else{
                map.put("status","failure");
                map.put("errMsg","用户创建失败");
            }
        }catch(Exception e){
            map.put("status","failure");
            map.put("errMsg",e.getMessage());
        }finally {
        }
        return map;
    }

    @RequestMapping(value = "/findById",method = RequestMethod.GET)
    public Object findStudentById(@RequestParam(value = "id") Long id){
        Map<String, Object> map = new HashMap<String, Object>();
        Optional<Student> student = studentRepository.findById(id);
        try{
            if (student != null) {
                map.put("status","ok");
                map.put("student",student);
            }else {
                map.put("status","failure");
                map.put("reeMsg","用户不存在");
            }
        }catch(Exception e){
            map.put("status","failure");
            map.put("errMsg",e.getMessage());
        }finally {
        }
        return map;
    }

    //删除一个学生
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String deleteStudent(@PathVariable Long id){
        Map<String, Object> map = new HashMap<>();
        studentRepository.deleteById(id);
        String s = id + "删除成功";
        return s;
    }
}
