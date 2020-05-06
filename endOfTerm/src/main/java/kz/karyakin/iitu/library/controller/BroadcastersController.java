package kz.karyakin.iitu.library.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.karyakin.iitu.library.entity.Broadcasters;
import kz.karyakin.iitu.library.serivce.BroadcastersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/broadcasters")
@Api(value = "Broadcasters Management System")
public class BroadcastersController {

    private final BroadcastersService broadcastersService;

    public BroadcastersController(BroadcastersService broadcastersService) {
        this.broadcastersService = broadcastersService;
    }

    @GetMapping("api/all")
    @ApiOperation(value = "Get all broadcasters", response = List.class, httpMethod = "GET")
    public List<Broadcasters> showAllBroadcasters() {
        return broadcastersService.showAllBroadcasters();
    }

    @GetMapping("api/{id}")
    @ApiOperation(value = "Get Broadcasters by id", response = Broadcasters.class)
    public Broadcasters getAuthorById(@ApiParam(value = "ID to get Broadcasters by id", required = true) @PathVariable Long id){
        return broadcastersService.getBroadcastersById(id).orElse(null);
    }

}
