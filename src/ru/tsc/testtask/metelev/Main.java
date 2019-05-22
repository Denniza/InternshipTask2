package ru.tsc.testtask.metelev;

import ru.tsc.testtask.metelev.entities.Line;
import ru.tsc.testtask.metelev.entities.ResultLine;
import ru.tsc.testtask.metelev.services.DataTransferService;
import ru.tsc.testtask.metelev.services.DownloadDataService;
import ru.tsc.testtask.metelev.services.SqlInnerJoinService;
import ru.tsc.testtask.metelev.services.ViewService;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<Line> firstList;
        ArrayList<Line> secondList;
        SqlInnerJoinService innerJoin = new SqlInnerJoinService();
        DataTransferService<Line> dataTransferService = new DataTransferService<>();
        DownloadDataService downloadDataService = new DownloadDataService();
        ViewService viewService = new ViewService();
        while(true) {
            try {
                if (args.length != 2) throw new Exception("Введите пути к двум файлам");
                if (args[0] == null || args[1] == null) throw new Exception("введите корректный путь к файлу");
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        firstList = downloadDataService.downloadData(args[0]);
        secondList = downloadDataService.downloadData(args[1]);
        List<ResultLine> resultArrayList = innerJoin.innerJoin(firstList,secondList);
        LinkedList<Line> firstLinkedList = dataTransferService.transferToLinkedList(firstList);
        LinkedList<Line> secondLinkedList = dataTransferService.transferToLinkedList(secondList);
        List<ResultLine> resultLinkedList = innerJoin.innerJoin(firstLinkedList,secondLinkedList);
        HashMap<Integer, ArrayList<Line>> firstMap = dataTransferService.transferToHashMap(firstLinkedList);
        HashMap<Integer, ArrayList<Line>> secondMap = dataTransferService.transferToHashMap(secondLinkedList);
        HashMap<Integer,ArrayList<ResultLine>> resultMap = innerJoin.innerJoin(firstMap,secondMap);
        System.out.println("Изначальные списки:");
        viewService.showList(firstLinkedList);
        viewService.showList(secondList);
        System.out.println("Результат ArrayList");
        viewService.showResult(resultArrayList);
        System.out.println("Результат LinkedList");
        viewService.showResult(resultLinkedList);
        viewService.showResult(resultMap);
        }
    }

