package com.scon.project.admin.graph.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/graph")
public class GraphController {
	
	@GetMapping("/moneyGraph")
	public String moneyGraph() {
		return "admin/graph/moneyGraph";
	}
	
	@GetMapping("/studentGraph")
	public String studentGraph() {
		return "admin/graph/studentGraph";
	}

}
