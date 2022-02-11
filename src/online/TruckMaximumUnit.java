package online;

import java.util.Arrays;

public class TruckMaximumUnit {

    public static int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int unitCount = 0;
        for (int[] boxType : boxTypes) {
            int numberOfBoxes = Math.min(truckSize, boxType[0]);
            int numberOfUnitsPerBox = boxType[1];
            unitCount += numberOfBoxes * numberOfUnitsPerBox;
            truckSize = truckSize - numberOfBoxes;
            if (truckSize == 0) break;
        }
        return unitCount;
    }

    public static void main(String[] args) {
        int[][] array = {
                {5, 10},
                {2, 5},
                {4, 7},
                {3, 9}
        };

        System.out.println(maximumUnits(array, 10));
    }
}
