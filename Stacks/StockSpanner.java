class StockSpanner {

    /*Stack<Integer> prices;
    Stack<Integer> weights;
    
    public StockSpanner() {         
        prices = new Stack<>();
        weights = new Stack<>();
    }
    
    public int next(int price) {
       
        int weight = 1;
        
        while(!prices.isEmpty() && price >= prices.peek()){
            
            weight += weights.pop();
            prices.pop();
        }
        
        prices.push(price);
        weights.push(weight);
        
        return weight;
        
    }*/
    
    
    
    List<Node> prices;
        
    public StockSpanner() {         
        prices = new ArrayList<>();        
    }
    
    public int next(int price) {
       
        int weight = 1; 
        
       
        int size = prices.size();
        
        while(size > 0 && price >= prices.get(size - 1).price ){
                            
            weight += prices.remove(size - 1).weight; 
            size = prices.size();
        }
        
         prices.add(new Node(price,weight));        
                
        return weight;
        
    }

}

class Node {
    
    int price;
    int weight;
    
    public Node(int price, int weight){
        
        this.price = price;
        this.weight = weight;        
    }
    
    
}




/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */