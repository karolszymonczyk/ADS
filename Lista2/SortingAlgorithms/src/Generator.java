import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Generator {

  private static PrintWriter pwS;
  private static PrintWriter pwI;
  private static PrintWriter pwQ;
  private static PrintWriter pwH;
  private static PrintWriter pwM;
  private static String type = "--asc";

  static void generate(String filename, int k) {
    try {
      String workingDir = System.getProperty("user.dir");
      String dest = workingDir + "\\stats\\" + filename;
      pwS = new PrintWriter(dest + "Select");
      pwI = new PrintWriter(dest + "Insert");
      pwQ = new PrintWriter(dest + "Quick");
      pwH = new PrintWriter(dest + "Heap");
      pwM = new PrintWriter(dest + "ModifiedQuick");

      generateArrays(k);

      pwS.close();
      pwI.close();
      pwQ.close();
      pwH.close();
      pwM.close();
    } catch (IOException | NumberFormatException e) {
      e.printStackTrace();
    }
  }

  private static void generateArrays(int k) {
    for (int i = 100; i <= 10000; i = i + 100) {
      for (int j = 0; j < k; j++) {
        int[] array = getRandomArray(i);
        int[] arrayCopy;
        arrayCopy = array.clone();
        sortArrayWithSelect(i, arrayCopy);
        arrayCopy = array.clone();
        sortArrayWithInsert(i, arrayCopy);
        arrayCopy = array.clone();
        sortArrayWithHeap(i, arrayCopy);
        arrayCopy = array.clone();
        sortArrayWithQuick(i, arrayCopy);
        arrayCopy = array.clone();
        sortArrayWithMquick(i, arrayCopy);
      }
    }
  }

  private static void sortArrayWithSelect(int n, int[] array) {
    SelectionSort selectionSort = new SelectionSort(n, array, type);
    int comparisons = selectionSort.getComparisons();
    int swaps = selectionSort.getSwaps();
    long timeNano = selectionSort.getTimeNano();
    pwS.println(n+";"+comparisons+";"+swaps+";"+timeNano);
  }

  private static void sortArrayWithInsert(int n, int[] array) {
    InsertionSort insertionSort = new InsertionSort(n, array, type);
    int comparisons = insertionSort.getComparisons();
    int swaps = insertionSort.getSwaps();
    long timeNano = insertionSort.getTimeNano();
    pwI.println(n+";"+comparisons+";"+swaps+";"+timeNano);
  }

  private static void sortArrayWithHeap(int n, int[] array) {
    HeapSort heapSort = new HeapSort(n, array, type);
    int comparisons = heapSort.getComparisons();
    int swaps = heapSort.getSwaps();
    long timeNano = heapSort.getTimeNano();
    pwH.println(n+";"+comparisons+";"+swaps+";"+timeNano);
  }

  private static void sortArrayWithQuick(int n, int[] array) {
    QuickSort quickSort = new QuickSort(n, array, type);
    int comparisons = quickSort.getComparisons();
    int swaps = quickSort.getSwaps();
    long timeNano = quickSort.getTimeNano();
    pwQ.println(n+";"+comparisons+";"+swaps+";"+timeNano);
  }

  private static void sortArrayWithMquick(int n, int[] array) {
    ModifiedQuickSort modifiedQuickSort = new ModifiedQuickSort(n, array, type);
    int comparisons = modifiedQuickSort.getComparisons();
    int swaps = modifiedQuickSort.getSwaps();
    long timeNano = modifiedQuickSort.getTimeNano();
    pwM.println(n+";"+comparisons+";"+swaps+";"+timeNano);
  }

  private static int[] getRandomArray(int size){
    int[] randomArray = new int[size];
    for(int i=0; i<size; i++){
      randomArray[i] = new Random().nextInt();
    }
    return randomArray;
  }
}
