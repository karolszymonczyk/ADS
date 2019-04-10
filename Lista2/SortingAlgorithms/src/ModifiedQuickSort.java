public class ModifiedQuickSort extends SortingAlgorithm{

  ModifiedQuickSort(int n, int array[], String type) {

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
    if(array.length <= 16) {
      insertionSortAsc(low, high, array);
    }

//    System.err.println("comparison");
    comparisons++;
    if (low < high) {
      int pi = partitionAsc(array, low, high);

      if((pi-1) - low + 1 > 16) {
        sortAsc(array, low, pi - 1);
      } else {
        insertionSortAsc(low,pi-1,array);
      }

      if(high - (pi + 1) + 1 > 16) {
        sortAsc(array, pi + 1, high);
      } else {
        insertionSortAsc(pi + 1,high,array);
      }
    }
  }

  private int partitionAsc(int array[], int low, int high) {
    int median = calcMedian(low, high, array);
//    System.err.println("swap");
    swaps++;
    swap(array, median, high);
    int pivot = array[high];
    int i = (low - 1);

    for (int j = low; j < high; j++) {
//      System.err.println("comparison");
      comparisons++;
      if (array[j] <= pivot) {
        i++;
        swaps++;
//        System.err.println("swap");
        swap(array, i, j);
      }
    }
//    System.err.println("swap");
    swaps++;
    swap(array, i + 1, high);
    return (i + 1);
  }

  private void insertionSortAsc(int start, int end,int array[]) {

    for (int i = start + 1; i <= end; i++) {
      int key = array[i];
      int j = i - 1;

//      System.err.println("comparison");
      comparisons++;
//      System.err.println("comparison");
      comparisons++;
      while (j >= start && array[j] > key) {
//        System.err.println("swap");
        swaps++;
        array[j + 1] = array[j];
        j--;
//        System.err.println("comparison");
        comparisons++;
//        System.err.println("comparison");
        comparisons++;
      }
      array[j + 1] = key;
    }

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
    int median = calcMedian(low, high, array);
//    System.err.println("swap");
    swaps++;
    swap(array, median, high);
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
    swap(array, i + 1, high);
    return (i + 1);
  }

  private int calcMedian(int start, int end,int array[]){

    int mid = ((end - start)/2) + start;

    int median = end;

    //      System.err.println("comparison");
    comparisons++;
    if(array[end] > array[mid]) {
      //      System.err.println("comparison");
      comparisons++;
      if(array[mid] > array[start]) {
        median = mid;
      } else if (array[end] > array[start]) {
        //      System.err.println("comparison");
        comparisons++;
        median = start;
      }
    } else {
      //      System.err.println("comparison");
      comparisons++;
      if (array[end] > array[start]) {
        //median = end
      } else if (array[mid] > array[start]) {
        //      System.err.println("comparison");
        comparisons++;
        median = start;
      } else {
        median = mid;
      }
    }

    return median;
  }

}
