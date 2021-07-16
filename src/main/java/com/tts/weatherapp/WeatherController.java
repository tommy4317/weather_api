package com.tts.weatherapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WeatherController 
{
    @Autowired
    private WeatherService weatherService;
    
    @GetMapping(value="/")
    public String getIndex(Model model) {
    	model.addAttribute("last", weatherService.getLastEntries());
        model.addAttribute("request", new Request("77338"));
        return "index";
    }
    
    @PostMapping(value="/")
    public String postIndex(Request request, Model model)
    {
    	Response data = weatherService.getForecast(request.getZipCode());
    	model.addAttribute("last", weatherService.getLastEntries());
    	model.addAttribute("data", data);
    	return "index";
    }
    
    
}

