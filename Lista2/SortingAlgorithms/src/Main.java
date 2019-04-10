import java.util.Scanner;

public class Main {

  //todo java Main --type select --desc
  //todo 5
  //todo 9 1 -7 1000 4

  private static SortingAlgorithm algorithm;

  private static int comparisons = 0;
  private static int swaps = 0;
  private static long timeNano = 0;

  public static void main(String[] args) {

    String type;
    int n;
    int array[];

    if (args.length <= 2) {
      System.out.println("Wrong input! --type <type> --desc/asc");
      return;
    }

    if (args[0].equals("--stat")) {
      String filename = args[1];
      int k = Integer.parseInt(args[2]);
      Generator.generate(filename, k);
      System.out.println("File is ready!");
    } else if (args[0].equals("--type") && args[2].equals("--desc") || args[2].equals("--asc")) {

      Scanner scanner = new Scanner(System.in);
      type = args[2];

      n = scanner.nextInt();
      array = new int[n];
      scanner.nextLine();

      Scanner numScanner = new Scanner(scanner.nextLine());
      for (int i = 0; i < n; i++) {
        if (numScanner.hasNextInt()) {
          array[i] = numScanner.nextInt();
        } else {
          System.out.println("You didn't provide enough numbers");
          return;
        }
      }

      switch (args[1]) {
        case "select":

          algorithm = new SelectionSort(n, array, type);
          getInfo(algorithm);
          break;
        case "insert":

          algorithm = new InsertionSort(n, array, type);
          getInfo(algorithm);
          break;
        case "heap":

          algorithm = new HeapSort(n, array, type);
          getInfo(algorithm);
          break;
        case "quick":

          algorithm = new QuickSort(n, array, type);
          getInfo(algorithm);
          break;
        case "mquick":

          algorithm = new ModifiedQuickSort(n, array, type);
          getInfo(algorithm);
          break;
        default:
          System.out.println("Wrong input! --type <type> --desc/asc");
          break;
      }

      printInfo(comparisons, swaps, timeNano, array, type, n);
      //sprawdzić czy długość podanej tablicy jest zgodna z podaną długością XD
      //i czy wgl inty podaje do tablicy
    } else {
      System.out.println("Wrong input! --type <type> --desc/asc");
    }
  }

  private static void printInfo(int comparisons, int swaps, long timeNano, int array[], String type, int n) {
    System.err.println();
    System.err.println("comparisons : " + comparisons);
    System.err.println("swaps : " + swaps);
    System.err.println("execution time in nanoseconds : " + timeNano);
    System.err.println("execution time in milliseconds : " + timeNano / 1000000);
    if (!checkArray(array, type)) {
      System.out.println("Array is not sorted properly!");
    }
    System.out.println("Amount of sorted elements : " + n);
    printArray(array);
    System.out.println();
  }

  private static void getInfo(SortingAlgorithm algorithm) {
    comparisons = algorithm.getComparisons();
    swaps = algorithm.getSwaps();
    timeNano = algorithm.getTimeNano();
  }

  private static boolean checkArray(int array[], String type) {
    if (type.equals("--asc")) {
      for (int i = 1; i < array.length; i++) {
        if (array[i] < array[i - 1]) {
          return false;
        }
      }
    } else {
      for (int i = 1; i < array.length; i++) {
        if (array[i] > array[i - 1]) {
          return false;
        }
      }
    }
    return true;
  }

  private static void printArray(int array[]) {
    System.out.println();
    for (int anArray : array) {
      System.out.print(anArray + " ");
    }
    System.out.println();
  }
}
