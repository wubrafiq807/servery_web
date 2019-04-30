package com.nazdaq.srvm.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nazdaq.srvm.model.Building;
import com.nazdaq.srvm.model.Flat;
import com.nazdaq.srvm.model.Land;
import com.nazdaq.srvm.model.Machinary;
import com.nazdaq.srvm.model.Nbfi;
import com.nazdaq.srvm.service.CommonService;
import com.nazdaq.srvm.service.UserService;

@Controller
public class PropertyController {
	@Autowired
	private UserService userService;

	@Autowired
	private CommonService commonService;

	@RequestMapping(value = "/land", method = RequestMethod.GET)
	public ModelAndView land(ModelMap model, Principal principal, HttpSession session) {
		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}

		List<Land> lands = commonService.getAllObjectList("Land").stream().map(x -> (Land) x)
				.collect(Collectors.toList());
		model.put("landList", lands);

		return new ModelAndView("landList", model);
	}
	
	@RequestMapping(value = "/building", method = RequestMethod.GET)
	public ModelAndView building(ModelMap model, Principal principal, HttpSession session) {
		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}

		List<Building> buildings = commonService.getAllObjectList("Building").stream().map(x -> (Building) x)
				.collect(Collectors.toList());
		model.put("buildingList", buildings);

		return new ModelAndView("buildingList", model);
	}
	
	@RequestMapping(value = "/flat", method = RequestMethod.GET)
	public ModelAndView flat(ModelMap model, Principal principal, HttpSession session) {
		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}

		List<Flat> flats = commonService.getAllObjectList("Flat").stream().map(x -> (Flat) x)
				.collect(Collectors.toList());
		model.put("flatList", flats);

		return new ModelAndView("flatList", model);
	}
	
	
	
	@RequestMapping(value = "/machinary", method = RequestMethod.GET)
	public ModelAndView machinary(ModelMap model, Principal principal, HttpSession session) {
		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}

		List<Machinary> machinaries = commonService.getAllObjectList("Machinary").stream().map(x -> (Machinary) x)
				.collect(Collectors.toList());
		model.put("machinaryList", machinaries);

		return new ModelAndView("machinaryList", model);
	}
	
	
	
	
	
	
	
}
