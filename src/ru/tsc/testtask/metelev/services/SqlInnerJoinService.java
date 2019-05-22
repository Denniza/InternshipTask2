package ru.tsc.testtask.metelev.services;

import ru.tsc.testtask.metelev.entities.Line;
import ru.tsc.testtask.metelev.entities.ResultLine;
import java.util.*;

public class SqlInnerJoinService {

    public ArrayList<ResultLine> innerJoin(ArrayList<Line> firstList, ArrayList<Line> secondList){
        ArrayList<ResultLine> result = new ArrayList<>();
        firstList.forEach(s->secondList.forEach(a->{
            if(s.getId()==a.getId())
                result.add(new ResultLine(s,a));
        }));
        return result;
    }

    public LinkedList<ResultLine> innerJoin (LinkedList<Line> firstList, LinkedList<Line> secondList) {
        LinkedList<ResultLine> result = new LinkedList<>();
        ListIterator<Line> iteratorSecond = secondList.listIterator();
        ListIterator<Line> iteratorFirst = firstList.listIterator();
        int iteratorStepCount = 1;
        Line secondListLine;
        Line firstListLine;
        while (iteratorFirst.hasNext()) {
            firstListLine = iteratorFirst.next();
            while (iteratorSecond.hasNext()) {
                secondListLine = iteratorSecond.next();
                if (firstListLine.getId() < secondListLine.getId()) {
                    iteratorBack(iteratorSecond,iteratorStepCount);
                    iteratorStepCount=1;
                    break;
                } else if (firstListLine.getId() == secondListLine.getId()) {
                    result.add(new ResultLine(firstListLine, secondListLine));
                    iteratorStepCount++;
                    if(!iteratorSecond.hasNext()) {
                        iteratorBack(iteratorSecond, iteratorStepCount);
                        iteratorStepCount = 1;
                        break;
                    }
                }
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
        for(Map.Entry<Integer,ArrayList<Line>> entry:firstMap.entrySet()){
            int id = entry.getKey();
            if(!secondMap.containsKey(id)) continue;
            ArrayList<Line> firstList = entry.getValue();
            result.putIfAbsent(id,new ArrayList<>());
            firstList.forEach(s->secondMap.get(id)
                    .forEach(a->result.get(id).add(new ResultLine(s,a))));
        }
        return result;
    }
}
