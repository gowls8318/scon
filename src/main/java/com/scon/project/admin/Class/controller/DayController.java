package com.scon.project.admin.Class.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j // log찍어보기
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class DayController {

	// 요일 checkbox
	@GetMapping("insertClass")
	public ModelAndView insertClass(ModelAndView mv) {

		// key,value로 checkbox가져오기
		Map<Integer, String> dayMap = new LinkedHashMap<>(); // map은 무작위, linked는 순서존재
		dayMap.put(1, "월");
		dayMap.put(2, "화");
		dayMap.put(3, "수");
		dayMap.put(4, "목");
		dayMap.put(5, "금");
		dayMap.put(6, "토");
		dayMap.put(7, "일");

		mv.addObject("dayMap", dayMap);

		mv.setViewName("/admin/insertRegist");

		return mv;

	}

}
