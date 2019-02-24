package com.nazdaq.srvm.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nazdaq.srvm.beans.CommonBean;
import com.nazdaq.srvm.model.Bank;
import com.nazdaq.srvm.model.BranchBank;
import com.nazdaq.srvm.model.BranchNbfi;
import com.nazdaq.srvm.model.District;
import com.nazdaq.srvm.model.Nbfi;
import com.nazdaq.srvm.model.User;
import com.nazdaq.srvm.service.CommonService;
import com.nazdaq.srvm.service.UserService;
import com.nazdaq.srvm.util.Constants;
import com.nazdaq.srvm.util.VmsEntitys;

@RestController
@RequestMapping("v1")
public class ApiController extends SavedRequestAwareAuthenticationSuccessHandler implements Constants{
		
	
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public HashMap<String, Object> getbankList(@RequestParam("type") String type,HttpSession session){
		List<CommonBean> beanList=new ArrayList<>();
		Map<String, Object> map=new HashMap<>();
		map.put("status", "200");
		if(type.toUpperCase().trim().equals(REQ_BANK)) {
			List<Bank> bankList=commonService.getAllObjectList("Bank").stream().map(x->(Bank)x).filter(x->x.getStatus()==1).collect(Collectors.toList());
			
			bankList.forEach(x->{			
				beanList.add(new CommonBean(x.getId(), x.getName()));
			});
			map.put("list", beanList);
			map.put("type", "bank");
		}else if(type.toUpperCase().trim().equals(REQ_NBFI)) {
			List<Nbfi> list=commonService.getAllObjectList("Nbfi").stream().map(x->(Nbfi)x).filter(x->x.getStatus()==1).collect(Collectors.toList());
			
			list.forEach(x->{			
				beanList.add(new CommonBean(x.getId(), x.getName()));
			});
			map.put("list", beanList);
			map.put("type", "nbfi");
		}else if(type.toUpperCase().trim().equals(REQ_BANK_BRANCH)) {
			List<BranchBank> list=commonService.getAllObjectList("BranchBank").stream().map(x->(BranchBank)x).filter(x->x.getStatus()==1).collect(Collectors.toList());
			
			list.forEach(x->{			
				beanList.add(new CommonBean(x.getId(),x.getBank().getName()+"-"+ x.getName()));
			});
			map.put("list", beanList);
			map.put("type", "branch_bank");
		}else if(type.toUpperCase().trim().equals(REQ_NBFI_BRANCH)) {
			List<BranchNbfi> list=commonService.getAllObjectList("BranchNbfi").stream().map(x->(BranchNbfi)x).filter(x->x.getStatus()==1).collect(Collectors.toList());
			
			list.forEach(x->{			
				beanList.add(new CommonBean(x.getId(),x.getNbfi().getName()+"-"+ x.getName()));
			});
			map.put("list", beanList);
			map.put("type", "branch_nbfi");
		}else if(type.toUpperCase().trim().equals(REQ_DISTRICT)) {
			List<District> list=commonService.getAllObjectList("District").stream().map(x->(District)x).filter(x->x.getStatus()==1).collect(Collectors.toList());
			
			list.forEach(x->{			
				beanList.add(new CommonBean(x.getId(), x.getName()));
			});
			map.put("list", beanList);
			map.put("type", "district");
			
		}else {
			map.put("status", "500");
		}
		
		
		return (HashMap<String, Object>) map;
	}
	
	
}
