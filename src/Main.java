public class Main {
    //You are given an array 'ARR' of 'N' integers and a value 'K'. A position 'i' is good if the value obtained on increasing 'ARR(il' by 'K' exceeds the sum of other elements. Calculate the number of aood positions in the array.
    //Example:
    //Input: ARR = [1, 2, 3, 4, 5], K = 2
    //Output: 3
    //Explanation: The good positions are 1, 3, and 4.
    //1 -> 1 + 2 > 2 + 3 + 4 + 5
    //3 -> 3 + 2 > 1 + 4 + 5
    //4 -> 4 + 2 > 1 + 2 + 3 + 5
    //Input: ARR = [1, 1, 1, 1], K = 1


    public static int goodPosition(int[] arr, int size, int k) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            int sum = 0;
            for (int j = 0; j < size; j++)
                if (i != j)
                    sum += arr[j];
            if (arr[i] + k > sum)
                count++;
        }
        return count;
    }
}