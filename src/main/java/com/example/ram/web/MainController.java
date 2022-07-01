package com.example.ram.web;

import com.example.ram.service.MessageService;
import com.example.ram.service.UserService;
import com.example.ram.web.dto.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class MainController {

	private final UserService userService;

	private final MessageService messageService;

	private static String primaryUser;

	private static String secondaryUser;

	public MainController(UserService userService, MessageService messageService) {
		super();
		this.userService = userService;
		this.messageService = messageService;
	}

	public static String getPrimaryUser() {
		return primaryUser;
	}

	public static void setPrimaryUser(String primaryUser) {
		MainController.primaryUser = primaryUser;
	}

	public static String getSecondaryUser() {
		return secondaryUser;
	}

	public static void setSecondaryUser(String secondaryUser) {
		MainController.secondaryUser = secondaryUser;
	}

	public static void resetUsers() {
		setPrimaryUser(null);
		setSecondaryUser(null);
	}

	@ModelAttribute("userRegistrationDto")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}

	@ModelAttribute("messageRegistrationDto")
	public MessageRegistrationDto messageRegistrationDto() {
		return new MessageRegistrationDto();
	}

	@GetMapping("/registration")
	public String showRegistrationForm(Model model) {
		model.addAttribute("userRegistrationDto", new UserRegistrationDto());
		resetUsers();
		return "registration";
	}

	@GetMapping("/")
	public String redirect() {
		resetUsers();
		return "customErrorPage";
	}

	@PostMapping("/registration")
	public String registerUserAccount(UserRegistrationDto userRegistrationDto) {
		userService.save(userRegistrationDto);
		resetUsers();
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String login() {
		resetUsers();
		return "login";
	}

	@PostMapping("/login")
	public String loginPass (UserRegistrationDto userRegistrationDto, Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(userService.authenticateUser(userRegistrationDto.getEmail(), userRegistrationDto.getPassword())) {
			request.authenticate(response);
			model.addAttribute("user", userService.loadUser(userRegistrationDto.getEmail()));
			model.addAttribute("message", userService.findUsersToMessage(userRegistrationDto.getEmail()));
			resetUsers();
			return "index";
		}
		else {
			resetUsers();
			return "redirect:/login";
		}
	}

	@GetMapping("/logout")
	public String logout (HttpServletRequest request, ModelAndView modelAndView, Model model) throws ServletException {
		request.getSession(true).invalidate();
		request.logout();
		for(Cookie cookie : request.getCookies()) {
			cookie.setMaxAge(0);
		}
		modelAndView.clear();
		request.getSession(false);
		model.addAttribute("user", new UserRegistrationDto());
		model.addAttribute("user", null);
		resetUsers();
		return "redirect:/login";
	}

	@PostMapping("/index")
	public String messagePage(UserRegistrationDto userRegistrationDto, Model model) {
		model.addAttribute("user", messageService.displayUsersToMessage(userRegistrationDto));
		model.addAttribute("messages", messageService.displayMessages(messageService.viewAllMessages(getPrimaryUser()), getSecondaryUser()));
		return "trial";
	}

	@PostMapping("/trial")
	public String sendMessage(MessageRegistrationDto messageRegistrationDto, HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
		messageService.sendMessage(getPrimaryUser(), getSecondaryUser(), messageRegistrationDto);
		request.authenticate(response);
		model.addAttribute("user", userService.loadUser(primaryUser));
		Users users = userService.findUsersToMessage(primaryUser);
		model.addAttribute("message", users);
		resetUsers();
		return "index";
	}
}
