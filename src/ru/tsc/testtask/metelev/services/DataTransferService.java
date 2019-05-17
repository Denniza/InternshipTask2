package ru.tsc.testtask.metelev.services;

import ru.tsc.testtask.metelev.entities.Line;
import java.util.*;

public class DataTransferService<T extends Line> {

    public LinkedList<T> transferToLinkedList(List<T> list){
        LinkedList<T> result = new LinkedList<>(list);
        result.sort(Comparator.comparing(Line::getId));
        return result;
    }

    public HashMap<Integer, ArrayList<T>> transferToHashMap(List<T> list){
        HashMap<Integer,ArrayList<T>> result = new HashMap<>();
        list.forEach(s->result.putIfAbsent(s.getId(),new ArrayList<>()));
        list.forEach(s->result.get(s.getId()).add(s));
        return result;
    }
}
