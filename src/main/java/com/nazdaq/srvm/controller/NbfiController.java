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
import com.nazdaq.srvm.model.Nbfi;
import com.nazdaq.srvm.model.User;
import com.nazdaq.srvm.service.CommonService;
import com.nazdaq.srvm.service.UserService;
import com.nazdaq.srvm.util.Constants;
import com.nazdaq.srvm.util.VmsEntitys;

@Controller
public class NbfiController extends SavedRequestAwareAuthenticationSuccessHandler implements Constants{
		
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommonService commonService;
	
	
	@RequestMapping(value = "/addNbfi", method = RequestMethod.GET)
	public ModelAndView addNbfi(@ModelAttribute("command") Nbfi nbfi, BindingResult result,
			ModelMap model, Principal principal, HttpSession session) {
		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		return new ModelAndView("addNbfi", model);
	}
	
	@RequestMapping(value = "/editNbfi/{id}", method = RequestMethod.GET)
	public ModelAndView editNbfi(@ModelAttribute("command") Nbfi nbfi, BindingResult result,@PathVariable("id") String id,
			ModelMap model, Principal principal, HttpSession session) {
		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		Nbfi NbfiDB=(Nbfi) commonService.getAnObjectByAnyUniqueColumn("Nbfi", "id", id);
		model.put("nbfi", NbfiDB);
		
		return new ModelAndView("editNbfi", model);
	}
	
	
	@RequestMapping(value = "/nbfiList", method = RequestMethod.GET)
	public ModelAndView nbfiList(@ModelAttribute("command") Nbfi nbfi, BindingResult result,
			ModelMap model, Principal principal, HttpSession session) {
		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		List<Nbfi> nbfis=commonService.getAllObjectList("Nbfi").stream().map(x->(Nbfi)x).collect(Collectors.toList());
		model.put("nbfiList", nbfis);
		
		return new ModelAndView("nbfiList", model);
	}
	
	@RequestMapping(value = "/saveNbfi", method = RequestMethod.POST)
	public ModelAndView saveNbfi(@ModelAttribute("command") Nbfi nbfi, BindingResult result,
			ModelMap model, Principal principal, HttpSession session) {
		User loginUser=(User) session.getAttribute("user");
		
		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		if(nbfi.getId()!=null) {
			Nbfi nbfiDB=(Nbfi) commonService.getAnObjectByAnyUniqueColumn("Nbfi", "id", nbfi.getId().toString());
			nbfiDB.setModifiedBy(loginUser);
			nbfiDB.setModifiedDate(new Date());
			nbfiDB.setName(nbfi.getName());
			nbfiDB.setStatus(nbfi.getStatus());
			commonService.saveOrUpdateModelObjectToDB(nbfiDB);			
		}else {
			
			nbfi.setCreatedBy(loginUser);
			nbfi.setCreatedDate(new Date());
			commonService.saveOrUpdateModelObjectToDB(nbfi);
			
		}
		
		return new ModelAndView("redirect:/nbfiList");
	
	}
	
}
