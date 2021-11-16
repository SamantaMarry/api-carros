package com.wswork.teste.samantamarry.apicarros.controller;

import com.wswork.teste.samantamarry.apicarros.domain.Cars;
import com.wswork.teste.samantamarry.apicarros.service.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/cars")
public class CarsController {

    private CarsService carsService;

    @Autowired
    public CarsController(CarsService carsService) {
        this.carsService = carsService;
    }

    @PostMapping
    public void upload(@RequestParam("file") MultipartFile file){
        carsService.uploadExcel(file);

    }

    @GetMapping("/importar/")
    public void criar(Cars cars) throws IOException {
        this.carsService.criar(cars);

    }




}
