package kz.karyakin.iitu.library.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.karyakin.iitu.library.entity.Weather;
import kz.karyakin.iitu.library.serivce.BroadcastersService;
import kz.karyakin.iitu.library.serivce.WeatherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather")
@Api(value = "Weather Management System")
public class WeatherController {

    private final WeatherService weatherService;
    private final BroadcastersService broadcastersService;

    public WeatherController(WeatherService weatherService, BroadcastersService broadcastersService) {
        this.weatherService = weatherService;
        this.broadcastersService = broadcastersService;
    }

    @GetMapping("/all")
    @ApiOperation(value = "Get all weather", response = List.class)
    public List<Weather> showAllWeather() {
        return weatherService.showAllWeather();
    }

    @PostMapping("")
    @ApiOperation(value = "Save new weather to the database", response = Weather.class)
    public Weather addNewWeather(@ApiParam(value = "Weather object to be saved in the database", required = true) @RequestBody Weather weather){
        return weatherService.addWeather(weather);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find weather by id", response = Weather.class)
    public Weather findWeatherById(@ApiParam(value = "ID to find the weather") @PathVariable Long id){
        return weatherService.findWeatherById(id).orElse(null);
    }

    @GetMapping("")
    @ApiOperation(value = "Search weather by title, description or broadcasters name", response = List.class)
    public List<Weather> findWeather(@ApiParam(value = "Search using this string") @RequestParam String search){
        search = search.toLowerCase();
        return weatherService.findWeather(search);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete weather by id", response = String.class)
    public String deleteWeather(@ApiParam(value = "ID for deleting the weather") @PathVariable Long id){
        weatherService.deleteWeather(id);
        return "The weather with id " + id + " is successfully deleted";
    }

}
