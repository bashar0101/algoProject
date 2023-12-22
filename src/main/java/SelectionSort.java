import java.nio.charset.StandardCharsets;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SelectionSort {
    public void run(int arr[]) {
        int min;
        for (int i = 0; i < arr.length - 1; i++) {
            min = i;
            for (int j = i + 1; j < arr.length - 1; j++) {
                if (arr[j] < arr[min]) min = j;
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
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
            System.out.println("Execution Time of the algorithm: for array with " + arr[i].length + " elements (Selection Sort) : " + executionTime + " milliseconds");
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
            FileWriter fileWriter = new FileWriter("results1.txt");
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
            PrintWriter writer = new PrintWriter("histogram1.py", StandardCharsets.UTF_8);
            writer.println("import matplotlib.pyplot as plt");
            writer.println("with open('results1.txt', 'r') as file:");
            writer.println("    x_values = [int(line.strip()) for line in file]");
            writer.println("y_values = [100,1000,5000,10000,50000,100000]");
            writer.println("plt.plot(y_values, x_values, marker='o', linestyle='-', color='r')");
            writer.println("plt.xlabel('Input size')");
            writer.println("plt.ylabel('Execution Time(milli seconds)')");
            writer.println("plt.title('Selection sort')");
            writer.println("plt.legend()");
            writer.println("plt.show()");
            writer.close();
            // Execute the Python script
            ProcessBuilder pb = new ProcessBuilder("python", "histogram1.py");
            pb.start();
        } catch (IOException ignored) {
            System.out.println("Exception here ");
        }
    }

}