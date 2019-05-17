package ru.tsc.testtask.metelev.services;

import ru.tsc.testtask.metelev.entities.Line;
import ru.tsc.testtask.metelev.entities.ResultLine;
import java.util.*;

public class SqlInnerJoinService {

    public ArrayList<ResultLine> innerJoin(ArrayList<Line> firstList, ArrayList<Line> secondList) {
        HashMap<Integer, ArrayList<Line>> map = new HashMap<>();
        ArrayList<ResultLine> result = new ArrayList<>();
        for(Line line:secondList){
            map.putIfAbsent(line.getId(),new ArrayList<>());
            map.get(line.getId()).add(line);
        }
        for(Line line:firstList){
            if(map.get(line.getId())==null) continue;
            for(Line secondLine:map.get(line.getId())){
                result.add(new ResultLine(line,secondLine));
            }
        }
        return result;
    }

    public ArrayList<ResultLine> secondInnerJoin(ArrayList<Line> firstList, ArrayList<Line> secondList){
        ArrayList<ResultLine> result = new ArrayList<>();
        for(Line line:firstList){
            for(Line secondLine:secondList){
                if(line.getId()==secondLine.getId()){
                    result.add(new ResultLine(line,secondLine));
                }
            }
        }
        return result;
    }

    public LinkedList<ResultLine> innerJoin (LinkedList<Line> firstList, LinkedList<Line> secondList) {
        LinkedList<ResultLine> result = new LinkedList<>();
        ListIterator<Line> iterator = secondList.listIterator();
        ListIterator<Line> iteratorFirst = firstList.listIterator();
        int iteratorStepCount=1;
        mark:
        while (iteratorFirst.hasNext()){
            Line firstListLine = iteratorFirst.next();
            Line secondListLine;
            while (true) {
                if(!iterator.hasNext()) { //если следующее значение null, то отводим итератор назад
                    iteratorBack(iterator,iteratorStepCount-1);
                    iteratorStepCount=1;
                    continue mark;
                }
                secondListLine = iterator.next();
                if ((firstListLine.getId() < secondListLine.getId())) {
                    if(iteratorStepCount!=1){
                        iteratorBack(iterator,iteratorStepCount);
                        iteratorStepCount=1;
                    }
                    continue mark;
                }
                if(firstListLine.getId() > secondListLine.getId()) {
                    continue;
                }
                result.add(new ResultLine(firstListLine,secondListLine));
                iteratorStepCount++;
            }
        }
        return result;
    }

    private void iteratorBack(ListIterator iterator, int IteratorStepCount){
        for(int i=0;i<IteratorStepCount;i++){
            iterator.previous();
        }
    }

    public HashMap<Integer,ArrayList<ResultLine>> innerJoin (HashMap<Integer,ArrayList<Line>> firstMap,
                                                             HashMap<Integer,ArrayList<Line>> secondMap){
        HashMap<Integer,ArrayList<ResultLine>> result = new HashMap<>();
        for(Map.Entry entry:firstMap.entrySet()){
            int id = (int)entry.getKey();
            if(!secondMap.containsKey(id)) continue;
            ArrayList<Line> firstList =(ArrayList<Line>) entry.getValue();
            result.putIfAbsent(id,new ArrayList<>());
            for(Line line:firstList){
                for(Line secondLine:secondMap.get(id)){
                    result.get(id).add(new ResultLine(line,secondLine));
                }
            }
        }
        return result;
    }
}
