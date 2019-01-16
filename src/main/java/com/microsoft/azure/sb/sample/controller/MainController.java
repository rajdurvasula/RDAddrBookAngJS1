package com.microsoft.azure.sb.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.microsoft.azure.sb.sample.data.ContactRepository;

@Controller
public class MainController {

    @Autowired
    private ContactRepository contactRepo;

    @Value("${welcome.message}")
    private String message;

    @RequestMapping(value = { "/" }, method=RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("message", message);
        return "index";
    } 
}
