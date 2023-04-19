package com.galvanize.springwebdemo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/math")

public class MathController {

    @GetMapping("/pi")
    public String getPi() {
        return "3.141592653589793";
    }

    @PostMapping("/fibonacci")
    public String getFibonacci() {
        return "1, 1, 2, 3, 5, 8, 13, 21, 34";
    }
}



