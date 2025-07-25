
// LC : 1137 
// Basic dp
public class NTribonacci {

    public int tribonacci(int n) {

        if(n == 0) return 0;
        if(n == 1 || (n == 2)) return 1;

        int[] trib = new int[n + 1];

        trib[0] = 0;
        trib[1] = 1;
        trib[2] = 1;       

        for(int i = 3; i < trib.length; i++){
            
            trib[i] = trib[i - 1] + trib[i - 2] + trib[i-3];
            
        }
        return trib[n];        
    }


    public static void main(String[] args) {
        NTribonacci obj = new NTribonacci();

        int n1 = 4;
        System.out.println("Expected :  4");
        System.out.println("Actual : " + obj.tribonacci(n1));

        int n2 = 25;
        System.out.println("Expected :  1389537");
        System.out.println("Actual : " + obj.tribonacci(n2));
    }

}
