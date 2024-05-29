package org.example.traccer2.controller;

import org.example.traccer2.payloat.ReqEditUser;
import org.example.traccer2.payloat.ReqFilterUser;
import org.example.traccer2.payloat.ReqUser;
import org.example.traccer2.payloat.Respons;
import org.example.traccer2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/save")
    public HttpEntity<?> save(@RequestBody ReqUser reqUser){
        Respons respons=userService.save(reqUser);
        return ResponseEntity.ok(respons);
    }

    @PostMapping("/edit")
    public HttpEntity<?> edit(@RequestBody ReqEditUser reqEditUser){
        Respons respons=userService.editUser(reqEditUser);
        return ResponseEntity.ok(respons);
    }

    @GetMapping("/delete")
    public HttpEntity<?> delete(@RequestParam Integer id){
        Respons respons=userService.delete(id);
        return ResponseEntity.ok(respons);
    }

    @GetMapping("/get")
    public HttpEntity<?> get(@RequestParam Integer id){
        Respons respons=userService.getUser(id);
        return ResponseEntity.ok(respons);
    }

    @PostMapping("/filter")
    public HttpEntity<?> filter(@RequestBody ReqFilterUser reqFilterUser){
        Respons respons=userService.getFilter(reqFilterUser);
        return ResponseEntity.ok(respons);
    }

    @GetMapping("/getAll")
    public HttpEntity<?> getAll(){
        Respons respons=userService.getAllUser();
        return ResponseEntity.ok(respons);
    }


}
