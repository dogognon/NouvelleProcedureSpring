package com.dogognon.sohliou.kone.NouvelleProcedure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class MetierController {

	  // Redirection pour que le HTML 5 mode fonctionne (par opposition au hashtag routing).
    @GetMapping({"{path:(?:(?!api|.).)*}/**"})
    public String redirect() {
        return "forward:/";
    }
    
    @GetMapping("/**/{path:[^\\.]*}")
    public String forward404toAngular(HttpServletRequest request) {
        return "forward:/"; // Redirige vers l'application Angular
    }
}
