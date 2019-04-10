public class SelectionSort extends SortingAlgorithm{

  //todo time complexity O(n^2) - theta
  //todo space complexity O(1)

  SelectionSort(int n, int array[], String type) {

    comparisons = 0;
    swaps = 0;

    if (type.equals("--asc")) {
      startTime = System.nanoTime();
      sortAsc(n, array);
      endTime = System.nanoTime();
    } else {
      startTime = System.nanoTime();
      sortDes(n, array);
      endTime = System.nanoTime();
    }
  }

  private void sortAsc(int n, int array[]) {

    for (int i = 0; i < n - 1; i++) {
      int minKeyIdx = i;
      for (int j = i + 1; j < n; j++) {
//        System.err.println("comparison");
        comparisons++;
        if (array[j] < array[minKeyIdx]) {
          minKeyIdx = j;
        }
      }

//      System.err.println("swap");
      swaps++;
      swap(array, minKeyIdx, i);
    }

  }

  private void sortDes(int n, int array[]) {

    for (int i = 0; i < n - 1; i++) {
      int maxKeyIdx = i;
      for (int j = i + 1; j < n; j++) {
//        System.err.println("comparison");
        comparisons++;
        if (array[j] > array[maxKeyIdx]) {
          maxKeyIdx = j;
        }
      }

//      System.err.println("swap");
      swaps++;
      swap(array, maxKeyIdx, i);
    }

  }

}
