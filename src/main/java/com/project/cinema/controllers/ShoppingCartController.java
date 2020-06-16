package com.project.cinema.controllers;

import com.project.cinema.config.AppConfig;
import com.project.cinema.model.ShoppingCart;
import com.project.cinema.model.dto.ShoppingCartRequestDto;
import com.project.cinema.model.dto.ShoppingCartResponseDto;
import com.project.cinema.model.dto.mapper.ShoppingCartMapper;
import com.project.cinema.service.MovieSessionService;
import com.project.cinema.service.ShoppingCartService;
import com.project.cinema.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);
    private ShoppingCartService shoppingCartService = context.getBean(ShoppingCartService.class);
    private MovieSessionService movieSessionService = context.getBean(MovieSessionService.class);
    private UserService userService = context.getBean(UserService.class);
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @PostMapping("/add-movie-session")
    public String addMovieSessionToShoppingCart(@RequestBody @Valid ShoppingCartRequestDto
                                                            shoppingCartRequestDto,
                                                            Authentication authentication) {
        String email = authentication.getName();
        shoppingCartService.addSession(movieSessionService
                .findById(shoppingCartRequestDto.getMovieSessionId()),
                userService.findByEmail(email));
        return "Movie session was added to shopping cart";
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getShoppingCartByUser(Authentication authentication) {
        String email = authentication.getName();
        ShoppingCart shoppingCart = shoppingCartService.getByUser(userService.findByEmail(email));
        return shoppingCartMapper.getShoppingCartDto(shoppingCart);
    }
}
