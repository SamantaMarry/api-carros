package com.wswork.teste.samantamarry.apicarros.controller;

import com.wswork.teste.samantamarry.apicarros.service.CarsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/cars")
public class CarsController {

    private final CarsService carsService;

    @Autowired
    public CarsController(CarsService carsService) {
        this.carsService = carsService;
    }

    @PostMapping
    public void upload(@RequestParam("file") MultipartFile file) {
        try {
            this.carsService.uploadAndSave(file);
        } catch (Exception e) {
            log.error("Erro ao converter e salvar carros do arquivo: {}", file);
        }
    }

}
