import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Main {
    public static SelectionSort ss = new SelectionSort();
    public static InsertionSort is = new InsertionSort();
    public static ShellSort shs = new ShellSort();
    public static MergeSort ms = new MergeSort();
    public static MergeSort_3_Ways ms3 = new MergeSort_3_Ways();
    public static QuickSortLomuto qsl = new QuickSortLomuto();
    public static QuickSortHoare qsh = new QuickSortHoare();
    public static HeapSort hs = new HeapSort();
    static int[] results = new int[8];

    public static void main(String[] args) throws InterruptedException {
        System.out.println("---------------------------------------");
        ss.exp();
        is.exp();
        shs.exp();
        ms.exp();
        ms3.exp();
        qsl.exp();
        qsh.exp();
        hs.exp();

        //we will sort the results of each algorithm in this array
        int[] array = generateRandomArray(100000);
        int[] arrayForSelectionSort = array.clone();
        int[] arrayForInsertionSort = array.clone();
        int[] arrayForShellSort = array.clone();
        int[] arrayForMergeSort = array.clone();
        int[] arrayFor3MergeSort = array.clone();
        int[] arrayquickSortLomuto = array.clone();
        int[] arrayquickSortHora = array.clone();
        int[] arrayForHeap = array.clone();

        // here we will compare with all the algorithms
        System.out.println("The Execution time for all the algorithms with the same inputs : ");
//        Selection Sort
        long startTime1 = System.currentTimeMillis();
        ss.run(arrayForSelectionSort);
        long endTime1 = System.currentTimeMillis();
        long executionTime1 = (endTime1 - startTime1);
        System.out.println("Execution Time of the algorithm: for array with " + array.length + " elements (Selection Sort) : " + executionTime1 + " milliseconds");
        results[0] = (int) executionTime1;
//        Insertion Sort
        long startTime2 = System.currentTimeMillis();
        is.run(arrayForInsertionSort);
        long endTime2 = System.currentTimeMillis();
        long executionTime2 = (endTime2 - startTime2);
        System.out.println("Execution Time of the algorithm: for array with " + array.length + " elements (Insertion Sort) : " + executionTime2 + " milliseconds");
        results[1] = (int) executionTime2;
//        Shell Sort
        long startTime3 = System.currentTimeMillis();
        shs.run(arrayForShellSort);
        long endTime3 = System.currentTimeMillis();
        long executionTime3 = (endTime3 - startTime3);
        System.out.println("Execution Time of the algorithm: for array with " + array.length + " elements (Shell Sort) : " + executionTime3 + " milliseconds");
        results[2] = (int) executionTime3;
//        Merge Sort
        long startTime4 = System.currentTimeMillis();
        ms.run(arrayForMergeSort);
        long endTime4 = System.currentTimeMillis();
        long executionTime4 = (endTime4 - startTime4);
        System.out.println("Execution Time of the algorithm: for array with " + array.length + " elements (Merge Sort) : " + executionTime4 + " milliseconds");
        results[3] = (int) executionTime4;
//        Merge Sort 3 ways
        long startTime5 = System.currentTimeMillis();
        ms3.run(arrayFor3MergeSort);
        long endTime5 = System.currentTimeMillis();
        long executionTime5 = (endTime5 - startTime5);
        System.out.println("Execution Time of the algorithm: for array with " + array.length + " elements (3 Ways Merge Sort) : " + executionTime5 + " milliseconds");
        results[4] = (int) executionTime5;
//        Quick sort Lomuto partiniong
        long startTime6 = System.currentTimeMillis();
        qsl.run(arrayquickSortLomuto);
        long endTime6 = System.currentTimeMillis();
        long executionTime6 = (endTime6 - startTime6);
        System.out.println("Execution Time of the algorithm: for array with " + array.length + " elements (Quick Sort Lomuto partitioning) : " + executionTime6 + " milliseconds");
        results[5] = (int) executionTime6;
//        Quick sort Hoare partiniong
        long startTime7 = System.currentTimeMillis();
        qsh.run(arrayquickSortHora);
        long endTime7 = System.currentTimeMillis();
        long executionTime7 = (endTime7 - startTime7);
        System.out.println("Execution Time of the algorithm: for array with " + array.length + " elements (Quick Sort Hoare partitioning) : " + executionTime7 + " milliseconds");
        results[6] = (int) executionTime7;
//        Heap Sort
        long startTime8 = System.currentTimeMillis();
        hs.run(arrayForHeap);
        long endTime8 = System.currentTimeMillis();
        long executionTime8 = (endTime8 - startTime8);
        System.out.println("Execution Time of the algorithm: for array with " + array.length + " elements (Heap Sort) : " + executionTime8 + " milliseconds");
        results[7] = (int) executionTime8;
        System.out.println("--------------------------");
        WriteResultsToFile(results);
        drawHistogramPy();
    }

    public static int[] generateRandomArray(int size) {
        int[] randomArray = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            randomArray[i] = random.nextInt((1000 - (-1000)) + 1) + (-1000); // Generates a random integer
        }
        return randomArray;
    }

    static void WriteResultsToFile(int[] results) {
        try {
            FileWriter fileWriter = new FileWriter("results9.txt");
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
    static void drawHistogramPy() {
        try {
            PrintWriter writer = new PrintWriter("histogram9.py", StandardCharsets.UTF_8);
            writer.println("import matplotlib.pyplot as plt");
            writer.println("with open('results9.txt', 'r') as file:");
            writer.println("    x_values = [int(line.strip()) for line in file]");
            writer.println("y_values = ['selection sort','Insertion Sort','shell sort','Merge Sort','3-way Merge Sort','Quick Sort Lomuto','Quick Sort with Hoare','heap sort']");
            writer.println("plt.plot(y_values, x_values, marker='o', linestyle='-', color='r')");
            writer.println("plt.xlabel('Algorithm name')");
            writer.println("plt.ylabel('Execution Time(milli seconds)')");
            writer.println("plt.title('All Algorithms in put size (100000)')");
            writer.println("plt.legend()");
            writer.println("plt.show()");
            writer.close();
            // Execute the Python script
            ProcessBuilder pb = new ProcessBuilder("python", "histogram9.py");
            pb.start();
        } catch (IOException ignored) {
            System.out.println("Exception here ");
        }
    }
}
