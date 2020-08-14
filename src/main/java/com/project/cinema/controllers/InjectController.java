package com.project.cinema.controllers;

import com.project.cinema.model.Role;
import com.project.cinema.model.User;
import com.project.cinema.service.RoleService;
import com.project.cinema.service.ShoppingCartService;
import com.project.cinema.service.UserService;
import javax.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class InjectController {
    private final RoleService roleService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public InjectController(RoleService roleService,
                            ShoppingCartService shoppingCartService,
                            UserService userService,
                            PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void injectData() {
        injectRolesToDB();
        injectUsersToDB();
    }

    private void injectRolesToDB() {
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        roleService.add(userRole);
    }

    private void injectUsersToDB() {
        User bob = new User();
        bob.setEmail("bob@gmail.com");
        bob.setPassword(passwordEncoder.encode("1234"));
        bob.setRole(roleService.getRoleByName("USER"));
        userService.add(bob);
        shoppingCartService.registerNewShoppingCart(bob);

        User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setPassword(passwordEncoder.encode("1234"));
        admin.setRole(roleService.getRoleByName("ADMIN"));
        userService.add(admin);
        shoppingCartService.registerNewShoppingCart(admin);
    }
}
