import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class MergeSort_3_Ways {
    public void run(int[] arr) {
        // creating duplicate of given array
        int[] newArray = new int[arr.length];

        // copying elements of given array into
        // duplicate array
        for (int i = 0; i < newArray.length; i++)
            newArray[i] = arr[i];

        // sort function
        mergeSort3WayRec(newArray, 0, arr.length, arr);

        // copy back elements of duplicate array
        // to given array
        for (int i = 0; i < newArray.length; i++)
            arr[i] = newArray[i];
    }

    /* Performing the merge sort algorithm on the
       given array of values in the rangeof indices
       [low, high).  low is minimum index, high is
       maximum index (exclusive) */
    public static void mergeSort3WayRec(int[] arr, int low, int high, int[] dArr) {
        // If array size is 1 then do nothing
        if (high - low < 2) return;

        // Splitting array into 3 parts
        int mid1 = low + ((high - low) / 3);
        int mid2 = low + 2 * ((high - low) / 3) + 1;

        // Sorting 3 arrays recursively
        mergeSort3WayRec(dArr, low, mid1, arr);
        mergeSort3WayRec(dArr, mid1, mid2, arr);
        mergeSort3WayRec(dArr, mid2, high, arr);

        // Merging the sorted arrays
        merge(dArr, low, mid1, mid2, high, arr);
    }

    /* Merge the sorted ranges [low, mid1), [mid1,
       mid2) and [mid2, high) mid1 is first midpoint
       index in overall range to merge mid2 is second
       midpoint index in overall range to merge*/
    public static void merge(int[] arr, int low, int mid1, int mid2, int high, int[] dArr) {
        int i = low, j = mid1, k = mid2, l = low;

        // choose smaller of the smallest in the three ranges
        while ((i < mid1) && (j < mid2) && (k < high)) {
            if (arr[i] < arr[j]/* arr[i].compareTo(arr[j]) < 0 */) {
                if (arr[i] < arr[k] /* y[i].compareTo(arr[k]) < 0*/) dArr[l++] = arr[i++];

                else dArr[l++] = arr[k++];
            } else {
                if (arr[j] < arr[k] /*arr[j].compareTo(arr[k]) < 0*/) dArr[l++] = arr[j++];
                else dArr[l++] = arr[k++];
            }
        }

        // case where first and second ranges have
        // remaining values
        while ((i < mid1) && (j < mid2)) {
            if (arr[i] < arr[j]/*arr[i].compareTo(arr[j]) < 0*/) dArr[l++] = arr[i++];
            else dArr[l++] = arr[j++];
        }

        // case where second and third ranges have
        // remaining values
        while ((j < mid2) && (k < high)) {
            if (arr[j] < arr[k]/*arr[j].compareTo(arr[k]) < 0*/) dArr[l++] = arr[j++];

            else dArr[l++] = arr[k++];
        }

        // case where first and third ranges have
        // remaining values
        while ((i < mid1) && (k < high)) {
            if (arr[i] < arr[k]/*arr[i].compareTo(arr[k]) < 0*/) dArr[l++] = arr[i++];
            else dArr[l++] = arr[k++];
        }

        // copy remaining values from the first range
        while (i < mid1) dArr[l++] = arr[i++];

        // copy remaining values from the second range
        while (j < mid2) dArr[l++] = arr[j++];

        // copy remaining values from the third range
        while (k < high) dArr[l++] = arr[k++];
    }

    public void exp() {
        int[] results = new int[6];
        int[] array0 = Main.generateRandomArray(100);
        int[] array1 = Main.generateRandomArray(1000);
        int[] array2 = Main.generateRandomArray(5000);
        int[] array3 = Main.generateRandomArray(10000);
        int[] array4 = Main.generateRandomArray(50000);
        int[] array5 = Main.generateRandomArray(100000);
        int[][] arr = {array0, array1, array2, array3, array4, array5};
        for (int i = 0; i < arr.length; i++) {
            long startTime = System.currentTimeMillis();
            this.run(arr[i]);
            long endTime = System.currentTimeMillis();
            long executionTime = (endTime - startTime);
            System.out.println("Execution Time of the algorithm: for array with " + arr[i].length + " elements (3 Way-Merge Sort) : " + executionTime + " milliseconds");
            results[i] = (int) executionTime;
        }
        // here we will write the results of the execution time in a text file and use it when we will
        // draw the plot with python
        WriteResultsToFile(results);
        drawHistogramPy();
        System.out.println("---------------------------------------");
    }

    private void WriteResultsToFile(int[] results) {
        try {
            FileWriter fileWriter = new FileWriter("results5.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int element : results) {
                bufferedWriter.write(Integer.toString(element));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    // this method will draw plot(line)
    private void drawHistogramPy() {
        try {
            PrintWriter writer = new PrintWriter("histogram5.py", StandardCharsets.UTF_8);
            writer.println("import matplotlib.pyplot as plt");
            writer.println("with open('results5.txt', 'r') as file:");
            writer.println("    x_values = [int(line.strip()) for line in file]");
            writer.println("y_values = [100,1000,5000,10000,50000,100000]");
            writer.println("plt.plot(y_values, x_values, marker='o', linestyle='-', color='r')");
            writer.println("plt.xlabel('Input size')");
            writer.println("plt.ylabel('Execution Time(milli seconds)')");
            writer.println("plt.title('3 Way-Merge sort')");
            writer.println("plt.legend()");
            writer.println("plt.show()");
            writer.close();
            // Execute the Python script
            ProcessBuilder pb = new ProcessBuilder("python", "histogram5.py");
            pb.start();
        } catch (IOException ignored) {
            System.out.println("Exception here ");
        }
    }
}
