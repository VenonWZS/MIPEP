package com.bjfu.it.mipep.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class GeneralController {

    @GetMapping("/index")
    public String index(){return "index";}


    @GetMapping("/adminindex")
    public String adminindex(){return "adminindex";}
    @GetMapping("/testindex")
    public String testindex(){return "oidindex";}
    @GetMapping("/testlayer")
    public String testilayer(){return "layertest";}






}
