package com.wswork.teste.samantamarry.apicarros.service;

import com.wswork.teste.samantamarry.apicarros.domain.Cars;
import com.wswork.teste.samantamarry.apicarros.domain.Factories;
import com.wswork.teste.samantamarry.apicarros.repository.CarsRepository;
import lombok.Cleanup;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class CarsService{

    private CarsRepository repository;

    @Autowired
    public CarsService(CarsRepository repository) {
        this.repository = repository;
    }

    // variáveis

    @Value("${contato.cars.raiz}")
    private String raiz;
    @Value("${contato.cars.diretorio.excel}")
    private String diretorioArquivo;
    private List<Cars> cars = new ArrayList<Cars>();


    // Métodos
    public void uploadExcel(MultipartFile file) {
        this.salvar(this.diretorioArquivo, file);


    }

    private void salvar(String diretorioCars, MultipartFile file) {
        Path diretorioPath = Paths.get(this.raiz, diretorioCars);
        Path arquivoPath = diretorioPath.resolve(file.getOriginalFilename());

        try {
            Files.createDirectories(diretorioPath);
            file.transferTo(arquivoPath.toFile());
        }catch (IOException e){

            throw new RuntimeException("Problems trying to save");

        }
    }


    public List<Cars> criar(Cars cars) throws IOException {

        // Recuperando o arquivo
        @Cleanup FileInputStream file = new FileInputStream("src/main/resources/cars/cars.xlsx");
        Workbook workbook = new XSSFWorkbook(file);

        // setando as abas
        Sheet sheet = workbook.getSheetAt(0);

        //setando as linhas
        List<Row> rows = (List<Row>) toList(sheet.iterator());

        // removendo cabeçalho
        rows.remove(0);

        rows.forEach(row ->{
                //Setando as celulas
                List <Cell> cells = (List<Cell>) toList(row.cellIterator());

                //Atribui os valores para classe cars
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
                        .cost(new BigDecimal(cells.get(7).getNumericCellValue()))
                        .color(cells.get(8).getStringCellValue())
                        .build();

                this.cars.add(car);

                });

        return repository.saveAll(this.cars);
    }

    public List<?> toList (Iterator<?> iterator){
        return IteratorUtils.toList(iterator);
    }



}