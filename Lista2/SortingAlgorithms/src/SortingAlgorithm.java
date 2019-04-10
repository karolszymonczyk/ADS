public abstract class SortingAlgorithm {

  int comparisons;
  int swaps;

  long startTime;
  long endTime;

  private void sortAsc(int n, int array[]){}
  private void sortDes(int n, int array[]){}

  private void sortAsc(int array[], int low, int high){}
  private void sortDes(int array[], int low, int high){}

  void swap(int array[], int a, int b) {
    int temp = array[a];
    array[a] = array[b];
    array[b] = temp;
  }

  int getComparisons() {
    return comparisons;
  }

  int getSwaps() {
    return swaps;
  }

  long getTimeNano() {
    return endTime - startTime;
  }
}
