package pkg.base.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class HomeController {

	@GetMapping("/dashboard")
	public String securedDashboard(Model model) {
		model.addAttribute("time", new Date());
		return "dashboard";
	}

}
