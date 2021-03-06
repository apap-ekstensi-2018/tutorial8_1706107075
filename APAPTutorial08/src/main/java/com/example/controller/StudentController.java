package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.StudentModel;
import com.example.service.StudentService;

@Controller
public class StudentController
{
    @Autowired
    StudentService studentDAO;


//    @RequestMapping("/")
//    public String index (Model model)
//    {
//    		model.addAttribute("title", "Index");
//        return "index";
////    		return "index2";
//    }


    @RequestMapping("/student/add")
    public String add (Model model)
    {
    		model.addAttribute("title", "Add Student");
        return "form-add";
    }


    @RequestMapping("/student/add/submit")
    public String addSubmit (
            @RequestParam(value = "npm", required = false) String npm,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gpa", required = false) double gpa)
    {
        StudentModel student = new StudentModel (npm, name, gpa);
        studentDAO.addStudent (student);

        return "success-add";
    }


    @RequestMapping("/student/view")
    public String view (Model model,
            @RequestParam(value = "npm", required = false) String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            model.addAttribute ("student", student);
            model.addAttribute ("title","View Student By NPM");
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            model.addAttribute ("title","Student Not Found");
            return "not-found";
        }
    }


    @RequestMapping("/student/view/{npm}")
    public String viewPath (Model model,
            @PathVariable(value = "npm") String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            model.addAttribute ("student", student);
            model.addAttribute ("title","View Student By NPM");
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            model.addAttribute ("title","Student Not Found");
            return "not-found";
        }
    }


    @RequestMapping("/student/viewall")
    public String view (Model model)
    {
        List<StudentModel> students = studentDAO.selectAllStudents ();
        model.addAttribute ("students", students);
        model.addAttribute("title", "View All Student");
        return "viewall";
    }


    @RequestMapping("/student/delete/{npm}")
    public String delete (Model model, @PathVariable(value = "npm") String npm)
    {
    		StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            studentDAO.deleteStudent (npm);
            model.addAttribute("title", "Delete");
            return "delete";
        } else {
            model.addAttribute ("npm", npm);
            model.addAttribute ("title","Student Not Found");
            return "not-found";
        }
    }
    
    @RequestMapping("/student/update/{npm}")
    public String updatePath (Model model,
            @PathVariable(value = "npm") String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            model.addAttribute ("student", student);
            model.addAttribute("title", "Update Student");
            return "form-update";
        } else {
            model.addAttribute ("npm", npm);
            model.addAttribute ("title","Student Not Found");
            return "not-found";
        }
    }
    
//    @RequestMapping(value="/student/update/submit", method= RequestMethod.POST)
//    public String updateSubmit (
//            @RequestParam(value = "npm", required = false) String npm,
//            @RequestParam(value = "name", required = false) String name,
//            @RequestParam(value = "gpa", required = false) double gpa)
//    {
//        StudentModel student = new StudentModel (npm, name, gpa);
//        studentDAO.updateStudent (student);
//
//        return "success-update";
//    }
    
    @RequestMapping(value="/student/update/submit", method= RequestMethod.POST)
    public String updateSubmit(@ModelAttribute StudentModel student) {
    		studentDAO.updateStudent (student);
    		return "success-update";
    }

}
