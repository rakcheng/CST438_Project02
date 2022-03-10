package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Users;
import com.example.service.UserService;

@Controller
public class UserController {
	
	 @Autowired
	    private UserService userService;

	    // display list of users
	    @GetMapping("/")
	    public String viewHomePage(Model model) {
	        return findPaginated(1, "firstName", "asc", model);
	    }
	    @GetMapping("/showNewUserForm")
	    public String showNewEmployeeForm(Model model) {
	        // create model attribute to bind form data
	    	Users user = new Users();
	        model.addAttribute("user", user);
	        return "new_employee";
	    }

	    @PostMapping("/saveUser")
	    public String saveUser(@ModelAttribute("employee") Users user) {
	        // save user to database
	        userService.saveUsers(user);
	        return "redirect:/";
	    }
	    @GetMapping("/showFormForUpdate/{id}")
	    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

	        // get user from the service
	        Users user = userService.getUsersById(id);

	        // set user as a model attribute to pre-populate the form
	        model.addAttribute("user", user);
	        return "update_user";
	    }
	    @GetMapping("/deleteUser/{id}")
	    public String deleteUser(@PathVariable(value = "id") long id) {

	        // call delete employee method 
	        this.userService.deleteUserById(id);
	        return "redirect:/";
	    }


	    @GetMapping("/page/{pageNo}")
	    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
	        @RequestParam("sortField") String sortField,
	        @RequestParam("sortDir") String sortDir,
	        Model model) {
	        int pageSize = 5;

	        Page < Users > page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
	        List < Users > listUsers = page.getContent();
	        
	        model.addAttribute("currentPage", pageNo);
	        model.addAttribute("totalPages", page.getTotalPages());
	        model.addAttribute("totalItems", page.getTotalElements());

	        model.addAttribute("sortField", sortField);
	        model.addAttribute("sortDir", sortDir);
	        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

	        model.addAttribute("listUsers", listUsers);
	        return "index";
	    }

}
