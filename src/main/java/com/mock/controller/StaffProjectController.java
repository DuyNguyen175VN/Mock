package com.mock.controller;


import com.mock.repositoty.StaffProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StaffProjectController {
    @Autowired
    StaffProjectRepository staffProjectRepository;


}
