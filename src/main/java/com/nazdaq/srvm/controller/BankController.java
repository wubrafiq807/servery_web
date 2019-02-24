package com.nazdaq.srvm.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nazdaq.srvm.model.Bank;
import com.nazdaq.srvm.model.User;
import com.nazdaq.srvm.service.CommonService;
import com.nazdaq.srvm.service.UserService;
import com.nazdaq.srvm.util.Constants;
import com.nazdaq.srvm.util.VmsEntitys;

@Controller
public class BankController extends SavedRequestAwareAuthenticationSuccessHandler implements Constants, VmsEntitys {
		
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommonService commonService;
	
	
	@RequestMapping(value = "/addBank", method = RequestMethod.GET)
	public ModelAndView addBank(@ModelAttribute("command") Bank bank, BindingResult result,
			ModelMap model, Principal principal, HttpSession session) {
		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		return new ModelAndView("addBank", model);
	}
	
	@RequestMapping(value = "/editBank/{id}", method = RequestMethod.GET)
	public ModelAndView editBank(@ModelAttribute("command") Bank bank, BindingResult result,@PathVariable("id") String id,
			ModelMap model, Principal principal, HttpSession session) {
		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		Bank bankDB=(Bank) commonService.getAnObjectByAnyUniqueColumn("Bank", "id", id);
		model.put("bank", bankDB);
		
		return new ModelAndView("editBank", model);
	}
	
	
	@RequestMapping(value = "/bankList", method = RequestMethod.GET)
	public ModelAndView bankList(@ModelAttribute("command") Bank bank, BindingResult result,
			ModelMap model, Principal principal, HttpSession session) {
		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		List<Bank> banks=commonService.getAllObjectList("Bank").stream().map(x->(Bank)x).collect(Collectors.toList());
		model.put("bankList", banks);
		
		return new ModelAndView("bankList", model);
	}
	
	@RequestMapping(value = "/saveBank", method = RequestMethod.POST)
	public ModelAndView saveBank(@ModelAttribute("command") Bank bank, BindingResult result,
			ModelMap model, Principal principal, HttpSession session) {
		User loginUser=(User) session.getAttribute("user");
		
		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		if(bank.getId()!=null) {
			Bank bankDB=(Bank) commonService.getAnObjectByAnyUniqueColumn("Bank", "id", bank.getId().toString());
			bankDB.setModifiedBy(loginUser);
			bankDB.setModifiedDate(new Date());
			bankDB.setName(bank.getName());
			bankDB.setStatus(bank.getStatus());
			commonService.saveOrUpdateModelObjectToDB(bankDB);			
		}else {
			
			bank.setCreatedBy(loginUser);
			bank.setCreatedDate(new Date());
			commonService.saveOrUpdateModelObjectToDB(bank);
			
		}
		
		return new ModelAndView("redirect:/bankList");
	
	}
	
}
