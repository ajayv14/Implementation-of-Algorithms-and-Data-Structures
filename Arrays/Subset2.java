import java.util.*;
import java.util.ArrayList;

class Subset2 {
   // subset that contains duplicates

   public List<List<Integer>> allSubsets(int[] numbers) {

      List<List<Integer>> res = new ArrayList<>();
      Arrays.sort(numbers); // to order repeated numbers in position (later nums[i]== nums[i - 1])
      backtracking(res, new ArrayList<Integer>(), numbers, 0);
      return res;
   }

   private void backtracking(List<List<Integer>> res, List<Integer> temp, int[] numbers, int start) {

      res.add(new ArrayList<>(temp));

      for (int i = start; i < numbers.length; i++) {
         if (i > start && numbers[i] == numbers[i - 1])
            continue;
         /*
          * i > start prevents index out of bounds exception, continue - breaks and moves
          * to next iteration of loop
          */
         temp.add(numbers[i]);
         backtracking(res, temp, numbers, i + 1);
         temp.remove(temp.size() - 1);
      }
   }

   public static void main(String[] args) {

      int[] numbers = { 1, 2, 2 };
      Subset2 obj = new Subset2();
      System.out.println(obj.allSubsets(numbers));
   }

}