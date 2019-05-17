package ru.tsc.testtask.metelev.services;

import ru.tsc.testtask.metelev.entities.Line;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DownloadDataService {

    public ArrayList<Line> downloadData(String path) {
        ArrayList<Line> resultList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)))){
            String line;
            while ((line = reader.readLine()) != null) {
                resultList.add(dataValidation(line));
            }
        }catch (IOException e) {
            System.out.println("Ошибка чтения данных из файла");
        }
        return resultList;
    }

    private Line dataValidation(String line){
        int lineId=0;
        try{
            lineId = Integer.parseInt(line.split(",")[0]);
            if (lineId<0) throw new NumberFormatException();
        } catch (NumberFormatException e){
            System.out.println("Некорректные данные Id");
        }
        return new Line(lineId,line.split(",")[1]);
    }
}
