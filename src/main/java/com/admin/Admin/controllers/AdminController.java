package com.admin.Admin.controllers;


import com.admin.Admin.models.User;
import com.admin.Admin.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/admin")
public class AdminController {

    @Qualifier("adminServiceImpl")

    @Autowired
    private AdminService adminService;

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return adminService.save(user);
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable String id) {
        return adminService.getUserByID(id);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        return adminService.updateUser(id, updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable String id) {
        adminService.deleteUser(id);
    }
}
