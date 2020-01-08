// credits: https://www.mathsisfun.com/games/towerofhanoi.html   &  https://www.geeksforgeeks.org/c-program-for-tower-of-hanoi/

class TowerOfHanoi {

      
   public void towerOfHanoi(int numOfDisks, char fromRod, char toRod , char helperRod ){
         
        if(numOfDisks == 1) {
            System.out.println("move disk 1 from :" + fromRod + " to : " + toRod + " | ");
        }
        
        else{
         
         /*imagine a TOH of 3 disks*/
                          
         towerOfHanoi(numOfDisks - 1, fromRod, helperRod, toRod);
         
         System.out.println("move "+ numOfDisks + " disk from :" + fromRod + " to : " + toRod + " | ");
         
         towerOfHanoi(numOfDisks - 1, helperRod, toRod, fromRod);
        
        }
         
   
   }   



   public static void main(String[] args){
         
         TowerOfHanoi obj = new TowerOfHanoi();
            obj.towerOfHanoi(3,'A', 'C', 'B');       

   }

}