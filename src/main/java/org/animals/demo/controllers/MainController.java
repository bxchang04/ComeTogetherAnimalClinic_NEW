package org.animals.demo.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MainController {

	@Autowired
	private JavaMailSender jms;
	
//	@RequestMapping("/index")
//	public String getIndex(@Valid @ModelAttribute("contact") Contact contact, final BindingResult bindingresult) {
//		System.out.println(bindingresult.getErrorCount() + "Errors");
//		if(bindingresult.hasErrors()) {
//			return "index";
//		} else if(!bindingresult.hasErrors()){			
//			return "redirect:/message";		
//		}
//		
//		return "index";
//	}
	
	@RequestMapping("/")
	public String getIndex() {
		return "index";
	}
	
	@PostMapping("/index")
	public String submitForm(HttpServletRequest request) {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String number = request.getParameter("number");
		String message = request.getParameter("message");
		
		SimpleMailMessage m = new SimpleMailMessage();
		m.setFrom(email);
		m.setTo("JoseSnow317@gmail.com");
		
		String subject = name + "has sent a message.";
		String mailContent = "Sender Name: " + name + "\n";
		mailContent += "Sender E-Mail: " + email + "\n";
		mailContent += "Sender Phone-Number: " + number + "\n";
		mailContent += "Sender Message: " + message + "\n";
		
		m.setSubject(subject);
		m.setText(mailContent);
		
		jms.send(m);
		
		return "message";
	}

}
