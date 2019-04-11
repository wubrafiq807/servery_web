package com.nazdaq.srvm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nazdaq.srvm.beans.CommonBean;
import com.nazdaq.srvm.model.Bank;
import com.nazdaq.srvm.model.BranchBank;
import com.nazdaq.srvm.model.BranchNbfi;
import com.nazdaq.srvm.model.District;
import com.nazdaq.srvm.model.Nbfi;
import com.nazdaq.srvm.service.CommonService;
import com.nazdaq.srvm.util.Constants;
import com.nazdaq.srvm.util.SendEmail;

@RestController
@RequestMapping("v1")
public class ApiController extends SavedRequestAwareAuthenticationSuccessHandler implements Constants{
		
	
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private JavaMailSender mailSender;

	/*@Value("${cc.email.addresss}")
	String ccEmailAddresss;*/
	
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
	
	@RequestMapping(value="/store", method=RequestMethod.POST)
	public String store(HttpServletRequest request) {
				
		SendEmail sendEmail = new SendEmail();
					
		try {

			String mailtitle = "NEW Test Mail"; 
									
			String mailBody = "<h1>Hello Dear, <br>I am Jehad</h1>";

			sendEmail.sendmailToUser(mailSender, "smhoque100@gmail.com", mailtitle, mailBody,"", "", "");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		String landName = request.getParameter("landName");
		String landArea = request.getParameter("landArea");
		String landMouza = request.getParameter("landMouza");
		String landDaq = request.getParameter("landDaq");
		String district = request.getParameter("district");
		
		String buildingName = request.getParameter("buildingName");
		String buildingArea = request.getParameter("buildingArea");
		String buildingAddress = request.getParameter("buildingAddress");
		String buildingNumberOfFloor = request.getParameter("buildingNumberOfFloor");
		
		String flatName = request.getParameter("flatName");
		String flatArea = request.getParameter("flatArea");
		String flatLocation = request.getParameter("flatLocation");
		String flatRoadNo = request.getParameter("flatRoadNo");
		String flatBuildingName = request.getParameter("flatBuildingName");
		String flatNo = request.getParameter("flatNo");
		
		String machineryName = request.getParameter("machineryName");
		String machineryModel = request.getParameter("machineryModel");
		String machinerySpecification = request.getParameter("machinerySpecification");
		
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String bankNbfiAddress = request.getParameter("bankNbfiAddress");
		String appType = request.getParameter("appType");
		String type = request.getParameter("type");
		
			if (type.equals("LAND_POST")) {
				System.out.print("landName: "+landName+", landArea: "+landArea+", landMouza: "+landMouza+", landDaq: "+landDaq+", district: "+district);
			}
			if (type.equals("BUILDING_POST")) {
				System.out.print("buildingName: "+buildingName+", buildingArea: "+buildingArea+", buildingAddress: "+buildingAddress+", buildingNumberOfFloor: "+buildingNumberOfFloor);
			}
			if (type.equals("FLAT_POST")) {
				System.out.print("flatName: "+flatName+", flatArea: "+flatArea+", flatLocation: "+flatLocation+", flatRoadNo: "+flatRoadNo+", flatBuildingName: "+flatBuildingName+", flatNo: "+flatNo);
			}
			if (type.equals("MACHINERY_POST")) {
				System.out.print("machineryName: "+machineryName+", machineryModel: "+machineryModel+", machinerySpecification: "+machinerySpecification);
			}
			
			System.out.println(" name: "+ name+" phone: "+phone+" email: "+email+" bankNbfiAddress: "+bankNbfiAddress+" appType: "+appType);
		
			
		return "Insert Successfully!";
	}
	
}
