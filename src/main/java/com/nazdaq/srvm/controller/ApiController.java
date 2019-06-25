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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nazdaq.srvm.beans.CommonBean;
import com.nazdaq.srvm.model.Bank;
import com.nazdaq.srvm.model.BankApplicant;
import com.nazdaq.srvm.model.BranchBank;
import com.nazdaq.srvm.model.BranchNbfi;
import com.nazdaq.srvm.model.Building;
import com.nazdaq.srvm.model.CustomerApplicant;
import com.nazdaq.srvm.model.District;
import com.nazdaq.srvm.model.Flat;
import com.nazdaq.srvm.model.Land;
import com.nazdaq.srvm.model.Machinary;
import com.nazdaq.srvm.model.Nbfi;

import com.nazdaq.srvm.model.NbfiApplicant;
import com.nazdaq.srvm.service.CommonService;
import com.nazdaq.srvm.util.Constants;
import com.nazdaq.srvm.util.SendEmail;
import com.nazdaq.srvm.util.SendSms;


@RestController
@RequestMapping("v1")
public class ApiController extends SavedRequestAwareAuthenticationSuccessHandler implements Constants {

	@Autowired
	private CommonService commonService;


	@Autowired
	private JavaMailSender mailSender;

	/*
	 * @Value("${cc.email.addresss}") String ccEmailAddresss;
	 */


	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public HashMap<String, Object> getbankList(@RequestParam("type") String type, HttpSession session) {
		List<CommonBean> beanList = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("status", "200");
		if (type.toUpperCase().trim().equals(REQ_BANK)) {
			List<Bank> bankList = commonService.getAllObjectList("Bank").stream().map(x -> (Bank) x)
					.filter(x -> x.getStatus() == 1).collect(Collectors.toList());

			bankList.forEach(x -> {
				beanList.add(new CommonBean(x.getId(), x.getName()));
			});
			map.put("list", beanList);
			map.put("type", "bank");
		} else if (type.toUpperCase().trim().equals(REQ_NBFI)) {
			List<Nbfi> list = commonService.getAllObjectList("Nbfi").stream().map(x -> (Nbfi) x)
					.filter(x -> x.getStatus() == 1).collect(Collectors.toList());

			list.forEach(x -> {
				beanList.add(new CommonBean(x.getId(), x.getName()));
			});
			map.put("list", beanList);
			map.put("type", "nbfi");
		} else if (type.toUpperCase().trim().equals(REQ_BANK_BRANCH)) {
			List<BranchBank> list = commonService.getAllObjectList("BranchBank").stream().map(x -> (BranchBank) x)
					.filter(x -> x.getStatus() == 1).collect(Collectors.toList());

			list.forEach(x -> {
				beanList.add(new CommonBean(x.getId(), x.getBank().getName() + "-" + x.getName()));
			});
			map.put("list", beanList);
			map.put("type", "branch_bank");
		} else if (type.toUpperCase().trim().equals(REQ_NBFI_BRANCH)) {
			List<BranchNbfi> list = commonService.getAllObjectList("BranchNbfi").stream().map(x -> (BranchNbfi) x)
					.filter(x -> x.getStatus() == 1).collect(Collectors.toList());

			list.forEach(x -> {
				beanList.add(new CommonBean(x.getId(), x.getNbfi().getName() + "-" + x.getName()));
			});
			map.put("list", beanList);
			map.put("type", "branch_nbfi");
		} else if (type.toUpperCase().trim().equals(REQ_DISTRICT)) {
			List<District> list = commonService.getAllObjectList("District").stream().map(x -> (District) x)
					.filter(x -> x.getStatus() == 1).collect(Collectors.toList());

			list.forEach(x -> {
				beanList.add(new CommonBean(x.getId(), x.getName()));
			});
			map.put("list", beanList);
			map.put("type", "district");

		} else {
			map.put("status", "500");
		}

		return (HashMap<String, Object>) map;
	}

	@RequestMapping(value = "/store", method = RequestMethod.POST)
	public String store(HttpServletRequest request) {

		SendEmail sendEmail = new SendEmail();

		try {
			// ======================= START: Email Process ================================
			String mailtitle = "NEW Test Mail";

			String mailBody = "<h1>Dear Sir,</h1> <br> <h3>Greetings This is a test mail</h3>";

			sendEmail.sendmailToUser(mailSender, "smhoque100@gmail.com", mailtitle, mailBody, "", "", "");

			// ======================= END: Email PROCESS ==================================

			// ======================= START: Sms Process ==================================
			
			SendSms sms = new SendSms();
			//sms.sendSmsTo("+8801840261938", "Survey test sms");
			
			// ======================= END: Sms Process ====================================

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
			System.out.print("landName: " + landName + ", landArea: " + landArea + ", landMouza: " + landMouza
					+ ", landDaq: " + landDaq + ", district: " + district);
			
			// Save Bank Applicant
			
/*			String bankInfo[] = bankNbfiAddress.split("-");
			
			BranchBank branchBank = (BranchBank) commonService.getAnObjectByAnyUniqueColumn("BranchBank", "name", bankInfo[1]);
			
						
			BankApplicant bankApplicant = new BankApplicant();
			
			bankApplicant.setEmail(email);
			bankApplicant.setName(name);
			bankApplicant.setPhone(phone);
			bankApplicant.setBranchBank(branchBank);
			
			commonService.saveOrUpdateModelObjectToDB(bankApplicant);*/
			
			
	
			
			 // Bank/NBFI/CUSTOMER Info Saving
			
				if(appType.equals("BANK")) {
					saveBankInfo(bankNbfiAddress, email, name, phone);
					
				}else if(appType.equals("NBFI")) {
					saveNbfiInfo(bankNbfiAddress, email, name, phone);
				}else {
					saveCustomerInfo(bankNbfiAddress, email, name, phone);
				}
			
			
			// Save land data
			
			District landDistrict = (District) commonService.getAnObjectByAnyUniqueColumn("District", "name", district);

			Land land = new Land();
			
			land.setArea(Double.parseDouble(landArea));
			land.setDaq(landDaq);
			land.setDistrict(landDistrict);
			land.setMouza(landMouza);
			land.setName(landName);
			
			commonService.saveOrUpdateModelObjectToDB(land);
			
			
			
			
		}
		if (type.equals("BUILDING_POST")) {
			System.out.print("buildingName: " + buildingName + ", buildingArea: " + buildingArea + ", buildingAddress: "
					+ buildingAddress + ", buildingNumberOfFloor: " + buildingNumberOfFloor);
									
			
			 // Bank/NBFI/CUSTOMER Info Saving
				if(appType.equals("BANK")) {
					saveBankInfo(bankNbfiAddress, email, name, phone);
					
				}else if(appType.equals("NBFI")) {
					saveNbfiInfo(bankNbfiAddress, email, name, phone);
				}else {
					saveCustomerInfo(bankNbfiAddress, email, name, phone);
				}
			
           // save building info			
			Building building = new Building();
			
			building.setAddress(buildingAddress);
			building.setArea(Double.parseDouble(buildingArea));
			building.setName(buildingName);
			building.setNumOfFloor(Integer.parseInt(buildingNumberOfFloor));
			
			commonService.saveOrUpdateModelObjectToDB(building);
			
			
		}
		if (type.equals("FLAT_POST")) {
			System.out.print("flatName: " + flatName + ", flatArea: " + flatArea + ", flatLocation: " + flatLocation
					+ ", flatRoadNo: " + flatRoadNo + ", flatBuildingName: " + flatBuildingName + ", flatNo: "
					+ flatNo);
			
			   // Bank/NBFI/CUSTOMER Info Saving
			
			if(appType.equals("BANK")) {
				saveBankInfo(bankNbfiAddress, email, name, phone);
				
			}else if(appType.equals("NBFI")) {
				saveNbfiInfo(bankNbfiAddress, email, name, phone);
			}else {
				saveCustomerInfo(bankNbfiAddress, email, name, phone);
			}
				
				// save flat info
				
				Flat flat = new Flat();
				flat.setArea(Double.parseDouble(flatArea));
				flat.setBuildingName(flatBuildingName);
				flat.setFlatNo(flatNo);
				flat.setLocation(flatLocation);
				flat.setName(flatName);
				flat.setRoadNumber(flatRoadNo);

				commonService.saveOrUpdateModelObjectToDB(flat);
			
			
		}
		if (type.equals("MACHINERY_POST")) {
			System.out.print("machineryName: " + machineryName + ", machineryModel: " + machineryModel
					+ ", machinerySpecification: " + machinerySpecification);
			
			 // Bank/NBFI/CUSTOMER Info Saving
			if(appType.equals("BANK")) {
				saveBankInfo(bankNbfiAddress, email, name, phone);
				
			}else if(appType.equals("NBFI")) {
				saveNbfiInfo(bankNbfiAddress, email, name, phone);
			}else {
				saveCustomerInfo(bankNbfiAddress, email, name, phone);
			}
			// save machinery info
			
			Machinary machinary = new Machinary();
			
			machinary.setModel(machineryModel);
			machinary.setName(machineryName);

			machinary.setSpecification(machinerySpecification);
			
			commonService.saveOrUpdateModelObjectToDB(machinary);
			
		}

		System.out.println(" name: " + name + " phone: " + phone + " email: " + email + " bankNbfiAddress: "
				+ bankNbfiAddress + " appType: " + appType);



		return "Insert Successfully!";
	}
	
	
	
	public void saveBankInfo(String bankNbfiAddress, String email, String name, String phone) {
		// Save Bank Applicant
		
		String bankInfo[] = bankNbfiAddress.split("-");
		
		BranchBank branchBank = (BranchBank) commonService.getAnObjectByAnyUniqueColumn("BranchBank", "name", bankInfo[1]);
		
					
		BankApplicant bankApplicant = new BankApplicant();
		
		bankApplicant.setEmail(email);
		bankApplicant.setName(name);
		bankApplicant.setPhone(phone);
		bankApplicant.setBranchBank(branchBank);
		
		commonService.saveOrUpdateModelObjectToDB(bankApplicant);
	}
	

	public void saveNbfiInfo(String bankNbfiAddress, String email, String name, String phone) {
		// Save Nbfi Applicant
		
		String NbfiInfo[] = bankNbfiAddress.split("-");
		
		BranchNbfi branchNbfi = (BranchNbfi) commonService.getAnObjectByAnyUniqueColumn("BranchNbfi", "name", NbfiInfo[1]);
		
					
		NbfiApplicant nbfiApplicant = new NbfiApplicant();
		
		nbfiApplicant.setEmail(email);
		nbfiApplicant.setName(name);
		nbfiApplicant.setPhone(phone);
		nbfiApplicant.setBranchNbfi(branchNbfi);
		
		commonService.saveOrUpdateModelObjectToDB(nbfiApplicant);
	}

	

	
	public void saveCustomerInfo(String bankNbfiAddress, String email, String name, String phone) {
		// Save Customer Applicant
			
					
		CustomerApplicant customerApplicant = new CustomerApplicant();
		
		customerApplicant.setEmail(email);
		customerApplicant.setName(name);
		customerApplicant.setPhone(phone);
		customerApplicant.setAddress(bankNbfiAddress);
		
		commonService.saveOrUpdateModelObjectToDB(customerApplicant);
	}
}
