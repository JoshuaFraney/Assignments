package com.ssa.controller;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;
import org.springframework.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ComponentScan(basePackages="com.ssa")
public class WebController {
	
	
	@RequestMapping("/help")
	  public ModelAndView help(HttpServletRequest request, ModelAndView mv) {
	       HashMap<String, String> helpMessages = new HashMap<String, String>();
	       helpMessages.put("1", "First Message Description");
	       helpMessages.put("2", "Second Message Description");
	       helpMessages.put("3", "Third Message Description");
	       helpMessages.put("4", "Fourth Message Description");
	       helpMessages.put("5", "Fifth Message Description");
	       helpMessages.put("6", "Sixth Message Description");
	       helpMessages.put("7", "Seventh Message Description");
	       helpMessages.put("8", "Eighth Message Description");
	       helpMessages.put("9", "Ninth Message Description");

	       String str = request.getParameter("id");
	       mv.addObject("desc", helpMessages.get(str));
	       
	      mv.setViewName("help");
	      return mv;
	}
	
//	public static final RequestMethod[] GET = null;

//	@RequestMapping(
//			  value = "/help", 
//			  params = { "id", "desc" }, 
//			  method = {RequestMethod.GET, RequestMethod.POST})
//			@ResponseBody
//			public String getId(@RequestParam long id, @RequestParam String desc) {
//			    return "Id =" + id + " Description =" + desc;
//	}
	
//			@ResponseBody
//			public String getDesc(
//			    @RequestParam("desc") String desc) {
//				    return "Description =" + desc;
//			}
			

			
		@RequestMapping("/about")
		public ModelAndView about(HttpServletRequest request, ModelAndView mv) {
			mv.setViewName("about");
			return mv;
		}
//		@RequestMapping("/help")
//		public ModelAndView help(HttpServletRequest request, ModelAndView mv) {
//			if(request.getParameter("id") != null) {
//				mv.addObject("id", request.getParameter("id"));
//			}
//			if(request.getParameter("desc") != null) {
//				mv.addObject("desc", request.getParameter("desc"));
//			}
//			mv.setViewName("help");
//			return mv;
//		}
		
		
	
		
		@RequestMapping("/")
		public ModelAndView home(HttpServletRequest request, ModelAndView mv) {
			if(request.getParameter("name") != null) {
				mv.addObject("name", request.getParameter("name"));
			}
			mv.setViewName("home");
			return mv;
}
}