package com.test.hello.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.test.hello.security.KdJdbcDaoImpl;

@RestController
public class HelloController {
	@RequestMapping(value="/loginSuccess",method=RequestMethod.GET)
    public ModelAndView test(HttpServletRequest req,HttpServletResponse res){
		ModelAndView mav = new ModelAndView("home");
    	return mav;
    }
	
	
	@RequestMapping(value="/",method=RequestMethod.GET)
    public ModelAndView test2(HttpServletRequest req,HttpServletResponse res){
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("SPRING_SECURITY_LAST_EXCEPTION", "sss");
    	return mav;
    }
	
	@RequestMapping(value="/checkcode", method = RequestMethod.GET,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public String sendpass(HttpServletRequest request,@RequestParam String username){
		Random r = new Random();
		int code = r.nextInt(99999);
		KdJdbcDaoImpl.userpass.put(username, code+"");
		Map<String, String> result = new HashMap<String, String>();
		result.put("checkcode", code+"");
		//return new ResponseEntity<Map<String, String>>(result, HttpStatus.OK);
		return code+"";
	}
	
	
	@RequestMapping(value="/role/user", method = RequestMethod.GET,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public String role_user(){
		return "role_user";
	}

	@RequestMapping(value="/role/admin", method = RequestMethod.GET,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public String role_admin(){
		return "role_admin";
	}

	@RequestMapping(value="/role/manager", method = RequestMethod.GET,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public String role_manager(){
		return "role_manager";
	}
}
