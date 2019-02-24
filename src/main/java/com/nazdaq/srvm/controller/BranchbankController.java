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
import com.nazdaq.srvm.model.User;
import com.nazdaq.srvm.service.CommonService;
import com.nazdaq.srvm.service.UserService;
import com.nazdaq.srvm.util.Constants;
import com.nazdaq.srvm.util.VmsEntitys;

@Controller
public class BranchbankController extends SavedRequestAwareAuthenticationSuccessHandler implements Constants, VmsEntitys {
		
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(value = "/addBankBranch", method = RequestMethod.GET)
	public ModelAndView addBankBranch(@ModelAttribute("command") BranchBank branchBank,ModelMap model, Principal principal, HttpSession session) {
		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		List<Bank> bankList=commonService.getObjectListByAnyColumn("Bank", "status", "1").stream().map(x->(Bank)x).collect(Collectors.toList());
		model.put("bankList", bankList);
		return new ModelAndView("addBankBranch", model);
	}
	
	@RequestMapping(value = "/editBankBranch/{id}", method = RequestMethod.GET)
	public ModelAndView editBankBranch(@ModelAttribute("command") BranchBank branchBank, BindingResult result,@PathVariable("id") String id,
			ModelMap model, Principal principal, HttpSession session) {
		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		BranchBank branchBankDb=(BranchBank) commonService.getAnObjectByAnyUniqueColumn("BranchBank", "id", id);
		model.put("branchBank", branchBankDb);
		List<Bank> bankList=commonService.getObjectListByAnyColumn("Bank", "status", "1").stream().map(x->(Bank)x).collect(Collectors.toList());
		model.put("bankList", bankList);
		
		return new ModelAndView("editBankBranch", model);
	}
	
	
	@RequestMapping(value = "/branchBankList", method = RequestMethod.GET)
	public ModelAndView branchBankList(@ModelAttribute("command")  BranchBank branchBank, BindingResult result,
			ModelMap model, Principal principal, HttpSession session) {
		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		List<BranchBank> branchBanks=commonService.getAllObjectList("BranchBank").stream().map(x->(BranchBank)x).collect(Collectors.toList());
		model.put("branchBankList", branchBanks);
		
		return new ModelAndView("branchBankList", model);
	}
	
	@RequestMapping(value = "/saveBranchBank", method = RequestMethod.POST)
	public ModelAndView saveBranchBank(@ModelAttribute("command") BranchBank branchBank, BindingResult result,
			ModelMap model, Principal principal, HttpSession session) {
		User loginUser=(User) session.getAttribute("user");
		Bank bank=(Bank) commonService.getAnObjectByAnyUniqueColumn("Bank", "id", branchBank.getBankId().toString());
		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		if(branchBank.getId()!=null) {
			BranchBank bankDB=(BranchBank) commonService.getAnObjectByAnyUniqueColumn("BranchBank", "id", branchBank.getId().toString());
			bankDB.setModifiedBy(loginUser);
			bankDB.setModifiedDate(new Date());
			bankDB.setName(branchBank.getName());
			bankDB.setStatus(branchBank.getStatus());
			bankDB.setAddress(branchBank.getAddress());
			bankDB.setBank(bank);
			commonService.saveOrUpdateModelObjectToDB(bankDB);			
		}else {
			
			branchBank.setCreatedBy(loginUser);
			branchBank.setCreatedDate(new Date());
			branchBank.setBank(bank);
			commonService.saveOrUpdateModelObjectToDB(branchBank);
			
		}
		
		return new ModelAndView("redirect:/branchBankList");
	
	}
	
	
}
