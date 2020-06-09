package com.project.cinema;

import com.project.cinema.config.AppConfig;
import com.project.cinema.exceptions.AuthenticationException;
import com.project.cinema.model.CinemaHall;
import com.project.cinema.model.Movie;
import com.project.cinema.model.MovieSession;
import com.project.cinema.model.User;
import com.project.cinema.security.AuthenticationService;
import com.project.cinema.service.CinemaHallService;
import com.project.cinema.service.MovieService;
import com.project.cinema.service.MovieSessionService;
import com.project.cinema.service.OrderService;
import com.project.cinema.service.ShoppingCartService;
import com.project.cinema.service.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) throws AuthenticationException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        MovieService movieService = context.getBean(MovieService.class);
        movie = movieService.add(movie);
        movieService.getAll().forEach(System.out::println);

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(250);
        CinemaHallService cinemaHallService =
                context.getBean(CinemaHallService.class);
        cinemaHall = cinemaHallService.add(cinemaHall);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        movieSession.setShowTime(LocalDateTime.of(LocalDate.now(),
                LocalTime.of(16, 30)));
        MovieSessionService movieSessionService = context.getBean(MovieSessionService.class);
        movieSessionService.add(movieSession);
        movieSessionService.findAvailableSessions(movie.getId(),
                LocalDate.now()).forEach(System.out::println);

        User user = new User();
        user.setEmail("test_email@gmail.com");
        user.setPassword("1234");
        AuthenticationService authenticationService = context.getBean(AuthenticationService.class);
        user = authenticationService.register(user.getEmail(), user.getPassword());
        UserService userService = context.getBean(UserService.class);
        System.out.println("Check the findByEmail method: "
                + userService.findByEmail(user.getEmail()));
        System.out.println("Check the login method: "
                + authenticationService.login("test_email@gmail.com", "1234"));

        ShoppingCartService shoppingCartService =
                context.getBean(ShoppingCartService.class);
        shoppingCartService.registerNewShoppingCart(user);
        System.out.println("Check the findByUser method after registered shopping cart: "
                + shoppingCartService.getByUser(user));
        shoppingCartService.addSession(movieSession, user);
        System.out.println("Check the findByUser method after added movie session: "
                + shoppingCartService.getByUser(user));

        OrderService orderService = context.getBean(OrderService.class);
        orderService.completeOrder(shoppingCartService.getByUser(user).getTickets(), user);
        shoppingCartService.clear(shoppingCartService.getByUser(user));
        System.out.println("Check the findByUser method after clear shopping cart: "
                + shoppingCartService.getByUser(user));
        System.out.println("Check the complete order: " + orderService.getOrderHistory(user));
    }
}
