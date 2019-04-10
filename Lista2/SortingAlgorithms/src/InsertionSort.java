public class InsertionSort extends SortingAlgorithm{

  //todo time complexity O(n^2) - theta
  //todo space complexity O(1)

  InsertionSort(int n, int array[], String type) {

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

    for (int i = 1; i < n; i++) {
      int key = array[i];
      int j = i - 1;

//      System.err.println("comparison");
      comparisons++;
      while (j >= 0 && array[j] > key) {
//        System.err.println("swap");
        swaps++;
        array[j + 1] = array[j];
        j--;
//        System.err.println("comparison");
        comparisons++;
      }
      swaps++;
      array[j + 1] = key;
    }

  }

  private void sortDes(int n, int array[]) {

    for (int i = 1; i < n; i++) {
      int key = array[i];
      int j = i - 1;

//      System.err.println("comparison");
      comparisons++;
      while (j >= 0 && array[j] < key) {
//        System.err.println("swap");
        swaps++;
        array[j + 1] = array[j];
        j--;
//        System.err.println("comparison");
        comparisons++;
      }
      array[j + 1] = key;
    }

  }

}
