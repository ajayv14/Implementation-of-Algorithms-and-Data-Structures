public class FlipAndInvertingImage {

  public int[][] flipAndInvertImage(int[][] image) {

    // For each row
    for (int[] img : image) {

      // iterate half way thro and swap elements at j and image[0].length - 1 -j
      for (int j = 0; j < image[0].length / 2; j++) {

        // Optimization - if both numbers are same, don't have to swap
        if (img[j] == img[image[0].length - 1 - j])
          continue;

        int temp = img[j];
        img[j] = img[image[0].length - 1 - j];
        img[image[0].length - 1 - j] = temp;
      }

      // Invert post flipping image
      for (int j = 0; j < image[0].length; j++) {
        img[j] = img[j] == 1 ? 0 : 1;
      }
    }
    return image;
  }

  // Alternate approach
  /*
   * int m = A[0].length;
   * 
   * for(int[] row : A){ // loop thro rows
   * 
   * for(int c = 0; c < (m + 1)/2; c++){
   * int temp = row[c] ^ 1;
   * row[c] = row[m - 1 - c] ^ 1;
   * row[m - 1 - c] = temp;
   * }
   * }
   * return A;
   * }
   */

  public static void main(String[] args) {
    FlipAndInvertingImage obj = new FlipAndInvertingImage();

    int[][] res1 = obj.flipAndInvertImage(new int[][] { { 1, 1, 0 }, { 1, 0, 1 }, { 0, 0, 0 } });
    System.out.println("Expected output : [[1,0,0],[0,1,0],[1,1,1]]");
    System.out.println("Actual output : ");
    Util.printArray(res1);

    int[][] res2 = obj
        .flipAndInvertImage(new int[][] { { 1, 1, 0, 0 }, { 1, 0, 0, 1 }, { 0, 1, 1, 1 }, { 1, 0, 1, 0 } });
    System.out.println("Expected output : [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]");
    System.out.println("Actual output : ");
    Util.printArray(res2);
  } 

}

/*
 * (m+1)/2 is to stop the loop at the center including the center while
 * reversing teh 1D array
 * 
 * 5 - 4 - 3 - 2 - 1
 * 
 * 1 - 4 - 3 - 2 - 5
 * 
 * 1 - 2 - 3 - 4 - 5
 * 
 */