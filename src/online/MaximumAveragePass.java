package online;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MaximumAveragePass {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        // Max heap compared by first value in decreasing order.
        PriorityQueue<double[]> heap = new PriorityQueue<>(
                Comparator.comparingDouble( o -> -o[0])
        );

        for(int[] c : classes) {
            int pass = c[0];
            int total = c[1];
            double delta = (double) (pass + 1) / (total + 1) - (double) (pass) / (total);
            heap.add(new double[] {delta, pass, total});
        }

        while (extraStudents > 0) {
            double[] currentClass = heap.poll();
            double pass = currentClass[1];
            double total = currentClass[2];
            pass++;
            total++;
            double newDelta = (double) (pass + 1) / (total + 1) - (double) (pass) / (total);
            heap.add(new double[] { newDelta, pass, total});
            extraStudents--;
        }

        double ratio = 0.0d;
        while (!heap.isEmpty()){
            double[] currentClass = heap.poll();
            double pass = currentClass[1];
            double total = currentClass[2];
            ratio = ratio + (pass/ total);
        }
        return ratio/classes.length;
    }
}
