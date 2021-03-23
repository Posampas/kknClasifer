package pl.kkn;

import pl.kkn.Case.Case;

public class Distance implements Comparable<Distance> {
    private Case to;
    private double distance;


    public Distance(Case from, Case to) {
        this.to = to;
        distance = calculateDistance(from.getProperties() ,to.getProperties());
    }


    private double calculateDistance(double[] from, double[] to){
        double distance =0;

        for (int i = 0; i < from.length ; i++) {
            distance += (from[i] - to[i])*(from[i] - to[i]);
        }
        return  distance;
    }


    @Override
    public int compareTo(Distance o) {
        double dist = distance - o.distance;
        if (dist > 0)
            return -1;
        else if (dist < 0)
            return 1;
        else
            return 0;
    }

    public String getResult() {
        return to.getResult();

    }

    public boolean isZero() {
        return  distance ==0;
    }
}
