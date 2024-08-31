package com.example.myspringapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

	/** 登録/更新/削除完了後のリダイレクト先URL */
	private static final String REDIRECT_URL = "redirect:/users/";

	/** HTMLパス */
	private static final String PATH_LIST = "user/list";
	private static final String PATH_CREATE = "user/create";
	private static final String PATH_UPDATE = "user/update";

	/** Modelの属性名 */
	private static final String MODEL_ATTRIBUTE_NAME = "user";

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public String list(Model model) {
		model.addAttribute(MODEL_ATTRIBUTE_NAME, userRepository.findAll());
		return PATH_LIST;
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("user", new User());
		return "/user/create";
	}

	@PostMapping("/create")
	public String create(@Validated @ModelAttribute(MODEL_ATTRIBUTE_NAME) User user,
			BindingResult result) {

		if (result.hasErrors()) {
			return PATH_CREATE;
		}

		userRepository.save(user);
		return REDIRECT_URL;
	}

	@GetMapping("/{id}/update")
	public String update(@PathVariable("id") Integer id, Model model) {
		model.addAttribute(MODEL_ATTRIBUTE_NAME, userRepository.findById(id));
		return PATH_UPDATE;
	}

	@PostMapping("/{id}/update")
	public String update(@Validated @ModelAttribute(MODEL_ATTRIBUTE_NAME) User user,
			BindingResult result) {

		if (result.hasErrors()) {
			return PATH_UPDATE;
		}

		userRepository.save(user);
		return REDIRECT_URL;
	}

	@GetMapping("/{id}/delete")
	public String list(@PathVariable("id") Integer id) {
		userRepository.deleteById(id);
		return REDIRECT_URL;
	}
}
