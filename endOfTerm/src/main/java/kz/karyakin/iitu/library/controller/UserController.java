package kz.karyakin.iitu.library.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.karyakin.iitu.library.entity.Weather;
import kz.karyakin.iitu.library.entity.ReleasedWeather;
import kz.karyakin.iitu.library.entity.User;
import kz.karyakin.iitu.library.serivce.WeatherService;
import kz.karyakin.iitu.library.serivce.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Api(value = "User Management System")
public class UserController {

    private final UserService userService;
    private final WeatherService weatherService;


    public UserController(UserService userService, WeatherService weatherService) {
        this.userService = userService;
        this.weatherService = weatherService;
    }

    @GetMapping("/api/all")
    @ApiOperation(value = "Get all users", response = List.class)
    public List<User> showAllUsers(){
        return userService.showAllUsers();
    }

    @GetMapping("/api/{id}")
    @ApiOperation(value = "Get user by id", response = User.class)
    public User findUserById(@ApiParam(value = "ID to find the user", required = true) @PathVariable Long id){
        return userService.findUserById(id).orElse(null);
    }

    @PutMapping("/api/{id}")
    @ApiOperation(value = "Edit user")
    public User editUser(@ApiParam(value = "User's object to update existing user") @RequestBody User user,
                         @ApiParam(value = "ID to find the existing user") @PathVariable Long id){
        user.setId(id);
        return userService.saveUser(user);
    }

    /*@PostMapping("/released/{userId}/{bookId}")
    @ApiOperation(value = "Released a certain weather to a certain user", response = ReleasedWeather.class)
    public ReleasedWeather releasedWeather(@ApiParam(value = "User's ID for released weather to this user") @PathVariable Long userId,
                                     @ApiParam(value = "Weather's ID for released weather the user") @PathVariable Long weatherId){
        Optional<User> optUser = userService.findUserById(userId);
        Optional<Weather> optWeather = weatherService.findWeatherById(weatherId);
        if(optWeather.isPresent() && optUser.isPresent())
            return userService.releasedWeather(optWeather.get(), optUser.get());
        return null;

    }

    @PutMapping("/return/{userId}/{bookId}")
    @ApiOperation(value = "Return a certain weather by a certain user", response = ReleasedWeather.class)
    public ReleasedWeather returnBook(@ApiParam(value = "User's ID who wants to return the weather") @PathVariable Long userId,
                                      @ApiParam(value = "Weather's ID which wants to return the user") @PathVariable Long weatherId){
        Optional<User> optUser = userService.findUserById(userId);
        Optional<Weather> optWeather = weatherService.findWeatherById(weatherId);
        if(optWeather.isPresent() && optUser.isPresent())
            return userService.returnBook(optWeather.get(), optUser.get());
        return null;
    }*/

    @PostMapping("/register")
    @ApiOperation(value = "Registration of the new user")
    public User createUser(@ApiParam(value = "User's object which will be saved to the database") @RequestBody User user){
        return userService.saveUser(user);
    }

}
