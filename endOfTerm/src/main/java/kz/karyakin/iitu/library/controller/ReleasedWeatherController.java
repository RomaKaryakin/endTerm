package kz.karyakin.iitu.library.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.karyakin.iitu.library.entity.ReleasedWeather;
import kz.karyakin.iitu.library.serivce.ReleasedWeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/released")
@Api(value = "Released Weather Management System")
public class ReleasedWeatherController {

    private final ReleasedWeatherService releasedWeatherService;

    public ReleasedWeatherController(ReleasedWeatherService releasedWeatherService) {
        this.releasedWeatherService = releasedWeatherService;
    }

    @GetMapping("/all")
    @ApiOperation(value = "Find all Released Weather", response = List.class)
    public List<ReleasedWeather> findAll(){
        return releasedWeatherService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find Released Weather by id", response = ReleasedWeather.class)
    public ReleasedWeather findById(@ApiParam(value = "ID for getting Released Weather", required = true) @PathVariable Long id){
        return releasedWeatherService.findById(id);
    }

}
