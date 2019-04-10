public class QuickSort extends SortingAlgorithm{

  //todo time complexity O(n logn) - theta
  //todo space complexity O(logn)

  QuickSort(int n, int array[], String type) {

    comparisons = 0;
    swaps = 0;

    if (type.equals("--asc")) {
      startTime = System.nanoTime();
      sortAsc(array, 0, n - 1);
      endTime = System.nanoTime();
    } else {
      startTime = System.nanoTime();
      sortDes(array, 0, n - 1);
      endTime = System.nanoTime();
    }
  }

  private void sortAsc(int array[], int low, int high) {

//    System.err.println("comparison");
    comparisons++;
    if (low < high) {
      int pi = partitionAsc(array, low, high);

      sortAsc(array, low, pi - 1);
      sortAsc(array, pi + 1, high);
    }
  }

  private int partitionAsc(int array[], int low, int high) {
    int pivot = array[high];
    int i = (low - 1);

    for (int j = low; j < high; j++) {
//      System.err.println("comparison");
      comparisons++;
      if (array[j] <= pivot) {
        i++;
//        System.err.println("swap");
        swaps++;
        swap(array, i, j);
      }
    }
//    System.err.println("swap");
    swaps++;
    swap(array, i + 1, high);
    return (i + 1);
  }

  private void sortDes(int array[], int low, int high) {

//    System.err.println("comparison");
    comparisons++;
    if (low < high) {
      int pi = partitionDesc(array, low, high);

      sortDes(array, low, pi - 1);
      sortDes(array, pi + 1, high);
    }
  }

  private int partitionDesc(int array[], int low, int high) {
    int pivot = array[high];
    int i = (low - 1);

    for (int j = low; j < high; j++) {
//      System.err.println("comparison");
      comparisons++;
      if (array[j] >= pivot) {
        i++;
//        System.err.println("swap");
        swaps++;
        swap(array, i, j);
      }
    }
//    System.err.println("swap");
    swaps++;
    swap(array, i+1, high);
    return (i + 1);
  }

}
