/*54. Spiral Matrix*/

class SpiralMatrix1 {
    public List<Integer> spiralOrder(int[][] matrix) {
               
        
        List<Integer> list = new ArrayList<>();
        
        if(matrix == null || matrix.length < 1 || matrix[0].length < 1) return list;
        
        int rowEnd = matrix.length-1;
        int colEnd = matrix[0].length-1;
        
        int rowStart = 0;
        int colStart = 0;
    
    while(rowStart <= rowEnd && colStart <= colEnd){
        
        for(int i = colStart; i <= colEnd ; i++){
            
            list.add(matrix[rowStart][i]);
            System.out.println(list);
            
        }
        
        rowStart++;
        
        
        for(int j = rowStart; j <= rowEnd ; j++ ){
            
            list.add(matrix[j][colEnd]);
            System.out.println(list);
            
        }
        
        colEnd--;
        
        for(int k = colEnd; k >= colStart; k-- ){
            
           if(rowStart <= rowEnd){ /*note- else the inner spiral will continue to malfunction*/
            
               list.add(matrix[rowEnd][k]);
               System.out.println(list);
           }
        }
        
        rowEnd--;
        
        for(int l = rowEnd; l >= rowStart ; l--){
            
            if(colStart <= colEnd){  /*note- else the inner spiral will continue to malfunction*/
            
                list.add(matrix[l][colStart]);
                System.out.println(list);
            }
        }
        
        colStart++;
        /*for inner spiral like in case of 5 X 5*/
        
       // rowStart++; /*wrong approach- will lead to revisit of elements*/
       // rowEnd--;
       // colStart++;
       // colStart--;
        
    }
    return list;
        
    }
}