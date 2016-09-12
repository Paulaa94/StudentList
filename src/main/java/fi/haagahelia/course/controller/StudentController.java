package fi.haagahelia.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.haagahelia.course.model.Student;
import fi.haagahelia.course.repository.StudentRepository;

@Controller
public class StudentController {
	@Autowired
    private StudentRepository repository; 
	
	@RequestMapping("/")
	public String index(Model model) {
		 List<Student> students = (List<Student>) repository.findAll();
		
		model.addAttribute("students", students);
		
    	return "index";
    }

    @RequestMapping(value = "add")
    public String addStudent(Model model){
    	model.addAttribute("student", new Student());
        return "addStudent";
    }	
	
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Student student){
        repository.save(student);
    	return "redirect:/";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable("id") Long studentId, Model model) {
    	repository.delete(studentId);
        return "redirect:/";
    }    
}
