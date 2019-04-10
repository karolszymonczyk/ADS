public class HeapSort extends SortingAlgorithm{

  //todo time complexity O(n logn) - theta
  //todo space complexity O(1)

  public HeapSort(int n, int array[], String type) {

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

  public void sortAsc(int n, int array[]) {
    for (int i = n / 2 - 1; i >= 0; i--) { //(n / 2 - 1) -> last parent
      createMaxHeap(array, n, i);
    }
    for (int i = n - 1; i > 0; i--) { //todo bez 0
//      System.err.println("swap");
      swaps++;
      swap(array, 0, i);

      createMaxHeap(array, i, 0);
    }
  }

  public void createMaxHeap(int array[], int n, int i) {
    int max = i;
    int l = 2 * i + 1;
    int r = 2 * i + 2;

//    System.err.println("comparison");
    comparisons++;
    if (l < n && array[l] > array[max]) {
      max = l;
    }

//    System.err.println("comparison");
    comparisons++;
    if (r < n && array[r] > array[max]) {
      max = r;
    }

//    System.err.println("comparison");
    comparisons++;
    if (max != i) {
//      System.err.println("swap");
      swaps++;
      swap(array, i, max);

      createMaxHeap(array, n, max);
    }
  }

  public void sortDes(int n, int array[]) {
    for (int i = n / 2 - 1; i >= 0; i--) {
      createMinHeap(array, n, i);
    }
    for (int i = n - 1; i > 0; i--) {
//      System.err.println("swap");
      swaps++;
      swap(array, 0, i);

      createMinHeap(array, i, 0);
    }
  }

  public void createMinHeap(int array[], int n, int i) {
    int min = i;
    int l = 2 * i + 1;
    int r = 2 * i + 2;

//    System.err.println("comparison");
    comparisons++;
    if(l < n && array[l] < array[min]) {
      min = l;
    }

//    System.err.println("comparison");
    comparisons++;
    if(r < n && array[r] < array[min]) {
      min = r;
    }

//    System.err.println("comparison");
    comparisons++;
    if (min != i) {
//      System.err.println("swap");
      swaps++;
      swap(array, i, min);

      createMinHeap(array, n, min);
    }
  }

}
