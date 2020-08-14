package com.project.cinema.controllers;

import com.project.cinema.model.ShoppingCart;
import com.project.cinema.model.dto.ShoppingCartRequestDto;
import com.project.cinema.model.dto.ShoppingCartResponseDto;
import com.project.cinema.model.dto.mapper.ShoppingCartMapper;
import com.project.cinema.service.MovieSessionService;
import com.project.cinema.service.ShoppingCartService;
import com.project.cinema.service.UserService;
import javax.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final MovieSessionService movieSessionService;
    private final UserService userService;
    private final ShoppingCartMapper shoppingCartMapper;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  MovieSessionService movieSessionService,
                                  UserService userService, ShoppingCartMapper
                                          shoppingCartMapper) {
        this.shoppingCartService = shoppingCartService;
        this.movieSessionService = movieSessionService;
        this.userService = userService;
        this.shoppingCartMapper = shoppingCartMapper;
    }

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
