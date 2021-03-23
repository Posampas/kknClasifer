package pl.kkn;

import pl.kkn.Case.Case;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Classifier {
    private DataSet dataSet;
    private int parameterK = 2;
    private PriorityQueue<Distance> nearestNeighbours = new PriorityQueue<>();

    public Classifier(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    public boolean setParameterK(int parameterK) {
        if (parameterK < 1 || parameterK > dataSet.getCases().size())
            return false;
        else
            this.parameterK = parameterK;
        return true;

    }

    public String classify(Case toClassify) {
        findNearestNeighbours(toClassify);
        return determineResult();

    }

    private String determineResult() {
        Map<String, Integer> votes = new HashMap<>();
        for (Distance d : nearestNeighbours) {
            String result = d.getResult();
            if (votes.containsKey(result)) {
                votes.put(result, votes.get(result) + 1);
            } else {
                votes.put(result, 1);
            }
        }

        String result = "";
        int count = 0;
        for (String s : votes.keySet()) {
            int tmp = votes.get(s);
            if (count < tmp) {
                result = s;
                count = tmp;
            }

        }
        return result;
    }

    private void findNearestNeighbours(Case toClassify) {
        nearestNeighbours.clear();

        for (Case c : dataSet.getCases()) {
            Distance d = new Distance(toClassify, c);

            if (d.isZero()) {
                nearestNeighbours.clear();
                nearestNeighbours.add(d);
                return;
            }

            nearestNeighbours.add(d);
            if (nearestNeighbours.size() > parameterK) {
                nearestNeighbours.poll();
            }
        }
    }

}
