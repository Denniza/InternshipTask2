package ru.tsc.testtask.metelev.services;

import ru.tsc.testtask.metelev.entities.Line;
import ru.tsc.testtask.metelev.entities.ResultLine;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewService {

    public void showList(List<Line> list) {
        System.out.println("Размер списка:" + list.size());
        for (Line line:list) {
            System.out.println(line);
        }
    }

    public void showResult(List<ResultLine> list){
        System.out.println("Размер списка:"+ list.size());
        for(ResultLine line:list){
            System.out.printf("%s : %s %s \n", line.getId(),line.getValue(),line.getSecondValue());
        }
    }

    public void showResult(Map<Integer,ArrayList<ResultLine>> map){
        System.out.println("Результат HashMap");
        map.forEach((s,a)-> a.forEach(t-> System.out.println(t.getId()+ " : " + t.getValue() + t.getSecondValue())));
    }
}
