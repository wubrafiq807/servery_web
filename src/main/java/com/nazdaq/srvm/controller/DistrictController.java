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
import com.nazdaq.srvm.model.District;
import com.nazdaq.srvm.model.User;
import com.nazdaq.srvm.service.CommonService;
import com.nazdaq.srvm.service.UserService;
import com.nazdaq.srvm.util.Constants;
import com.nazdaq.srvm.util.VmsEntitys;

@Controller
public class DistrictController extends SavedRequestAwareAuthenticationSuccessHandler implements Constants, VmsEntitys {
		
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(value = "/addDistrict", method = RequestMethod.GET)
	public ModelAndView addDistrict(@ModelAttribute("command") District district, BindingResult result,
			ModelMap model, Principal principal, HttpSession session) {
		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		return new ModelAndView("addDistrict", model);
	}
	
	@RequestMapping(value = "/editDistrict/{id}", method = RequestMethod.GET)
	public ModelAndView editDistrict(@ModelAttribute("command") District district, BindingResult result,@PathVariable("id") String id,
			ModelMap model, Principal principal, HttpSession session) {
		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		District districtDB=(District) commonService.getAnObjectByAnyUniqueColumn("District", "id", id);
		model.put("district", districtDB);
		
		return new ModelAndView("editDistrict", model);
	}
	
	
	@RequestMapping(value = "/districtList", method = RequestMethod.GET)
	public ModelAndView districtList(@ModelAttribute("command") District district, BindingResult result,
			ModelMap model, Principal principal, HttpSession session) {
		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		List<District> districtList=commonService.getAllObjectList("District").stream().map(x->(District)x).collect(Collectors.toList());
		model.put("districtList", districtList);
		
		return new ModelAndView("districtList", model);
	}
	
	@RequestMapping(value = "/saveDistrict", method = RequestMethod.POST)
	public ModelAndView saveDistrict(@ModelAttribute("command") District district, BindingResult result,
			ModelMap model, Principal principal, HttpSession session) {
		User loginUser=(User) session.getAttribute("user");
		
		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		if(district.getId()!=null) {
			District districtDB=(District) commonService.getAnObjectByAnyUniqueColumn("District", "id", district.getId().toString());
			districtDB.setModifiedBy(loginUser);
			districtDB.setModifiedDate(new Date());
			districtDB.setName(district.getName());
			districtDB.setStatus(district.getStatus());
			commonService.saveOrUpdateModelObjectToDB(districtDB);			
		}else {
			
			district.setCreatedBy(loginUser);
			district.setCreatedDate(new Date());
			commonService.saveOrUpdateModelObjectToDB(district);
			
		}
		
		return new ModelAndView("redirect:/districtList");
	
	}
	
	
}
