package com.wswork.teste.samantamarry.apicarros.service;

import com.wswork.teste.samantamarry.apicarros.domain.Cars;
import com.wswork.teste.samantamarry.apicarros.domain.Factories;
import com.wswork.teste.samantamarry.apicarros.repository.CarsRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
public class CarsService {

    private final CarsRepository repository;

    @Autowired
    public CarsService(CarsRepository repository) {
        this.repository = repository;
    }

    public void uploadAndSave(MultipartFile file) throws IOException {
        log.info("Iniciando conversão e salvando carros: {}", file);
        List<Cars> carros = this.converterEmObjeto(file);
        this.salvar(carros);
    }

    private List<Cars> converterEmObjeto(MultipartFile file) throws IOException {
        log.info("Convertendo arquivo em objeto: {}", file);

        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        List<Row> rows = (List<Row>) toList(sheet.iterator());
        List<Cars> cars = new ArrayList<>();

        rows.remove(0);

        rows.forEach(row -> {
            List<Cell> cells = (List<Cell>) toList(row.cellIterator());

            Cars car = Cars.builder()
                    .id((int) cells.get(0).getNumericCellValue())
                    .factories(Factories.builder()
                            .id(((int) cells.get(1).getNumericCellValue()))
                            .name(cells.get(2).getStringCellValue())
                            .build())
                    .modal(cells.get(3).getStringCellValue())
                    .year((int) cells.get(4).getNumericCellValue())
                    .fuel(cells.get(5).getStringCellValue())
                    .doors((int) cells.get(6).getNumericCellValue())
                    .cost(BigDecimal.valueOf(cells.get(7).getNumericCellValue()))
                    .color(cells.get(8).getStringCellValue())
                    .build();

            cars.add(car);
        });

        return cars;
    }

    private void salvar(List<Cars> carros) {
        log.info("Salvando lista de carros: {}", carros);
        repository.saveAll(carros);
    }


    public List<?> toList(Iterator<?> iterator) {
        return IteratorUtils.toList(iterator);
    }

}
