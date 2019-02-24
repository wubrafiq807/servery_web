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
import com.nazdaq.srvm.model.BranchBank;
import com.nazdaq.srvm.model.BranchNbfi;
import com.nazdaq.srvm.model.Nbfi;
import com.nazdaq.srvm.model.User;
import com.nazdaq.srvm.service.CommonService;
import com.nazdaq.srvm.service.UserService;
import com.nazdaq.srvm.util.Constants;
import com.nazdaq.srvm.util.VmsEntitys;

@Controller
public class BranchNbfiController extends SavedRequestAwareAuthenticationSuccessHandler implements Constants, VmsEntitys {
		
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(value = "/addNbfiBranch", method = RequestMethod.GET)
	public ModelAndView addNbfiBranch(@ModelAttribute("command") BranchNbfi branchNbfi,ModelMap model, Principal principal, HttpSession session) {
		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		List<Nbfi> nbfiList=commonService.getObjectListByAnyColumn("Nbfi", "status", "1").stream().map(x->(Nbfi)x).collect(Collectors.toList());
		model.put("nbfiList", nbfiList);
		return new ModelAndView("addNbfiBranch", model);
	}
	
	@RequestMapping(value = "/editNbfiBranch/{id}", method = RequestMethod.GET)
	public ModelAndView editNbfiBranch(@ModelAttribute("command") BranchNbfi branchNbfi, BindingResult result,@PathVariable("id") String id,
			ModelMap model, Principal principal, HttpSession session) {
		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		BranchNbfi branchNbfiDb=(BranchNbfi) commonService.getAnObjectByAnyUniqueColumn("BranchNbfi", "id", id);
		model.put("branchNbfi", branchNbfiDb);
		
		List<Nbfi> nbfiList=commonService.getObjectListByAnyColumn("Nbfi", "status", "1").stream().map(x->(Nbfi)x).collect(Collectors.toList());
		model.put("nbfiList", nbfiList);
		
		return new ModelAndView("editNbfiBranch", model);
	}
	
	
	@RequestMapping(value = "/branchNbfiList", method = RequestMethod.GET)
	public ModelAndView branchNbfiList(@ModelAttribute("command")  BranchNbfi branchNbfi, BindingResult result,
			ModelMap model, Principal principal, HttpSession session) {
		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		List<BranchNbfi> branchNbfis=commonService.getAllObjectList("BranchNbfi").stream().map(x->(BranchNbfi)x).collect(Collectors.toList());
		model.put("branchNbfiList", branchNbfis);
		
		return new ModelAndView("branchNbfiList", model);
	}
	
	@RequestMapping(value = "/saveBranchNbfi", method = RequestMethod.POST)
	public ModelAndView saveBranchNbfi(@ModelAttribute("command") BranchNbfi branchNbfi, BindingResult result,
			ModelMap model, Principal principal, HttpSession session) {
		User loginUser=(User) session.getAttribute("user");
		Nbfi nbfi=(Nbfi) commonService.getAnObjectByAnyUniqueColumn("Nbfi", "id", branchNbfi.getNbfiId().toString());
		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		if(branchNbfi.getId()!=null) {
			BranchNbfi branchDB=(BranchNbfi) commonService.getAnObjectByAnyUniqueColumn("BranchNbfi", "id", branchNbfi.getId().toString());
			branchDB.setModifiedBy(loginUser);
			branchDB.setModifiedDate(new Date());
			branchDB.setName(branchNbfi.getName());
			branchDB.setStatus(branchNbfi.getStatus());
			branchDB.setAddress(branchNbfi.getAddress());
			branchDB.setNbfi(nbfi);
			commonService.saveOrUpdateModelObjectToDB(branchDB);			
		}else {
			
			branchNbfi.setCreatedBy(loginUser);
			branchNbfi.setCreatedDate(new Date());
			branchNbfi.setNbfi(nbfi);
			commonService.saveOrUpdateModelObjectToDB(branchNbfi);
			
		}
		
		return new ModelAndView("redirect:/branchNbfiList");
	
	}
	
	
}
