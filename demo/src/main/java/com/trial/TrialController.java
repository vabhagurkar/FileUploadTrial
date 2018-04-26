package com.trial;

import java.io.ByteArrayInputStream;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TrialController {
	@Autowired
	ServletContext context;

	@RequestMapping(value="/upload", headers=("content-type=multipart/*"), method=RequestMethod.POST)
	public ModelAndView upload(@RequestParam("file") MultipartFile inputFile) {
		
		if (!inputFile.isEmpty()) {
		try {
			ByteArrayInputStream stream = new   ByteArrayInputStream(inputFile.getBytes());
			String myString = IOUtils.toString(stream, "UTF-8");
			myString = myString.replaceAll("and", "or");
		   //return new ResponseEntity<String>(myString, headers, HttpStatus.OK);
		   return new ModelAndView("display.jsp", "myString", myString);
		}catch (Exception e) {    
		    return new ModelAndView("display.jsp", "myString", "");
		   }
		}else {
			 return new ModelAndView("display.jsp", "myString", "");
		}
		
	}
}