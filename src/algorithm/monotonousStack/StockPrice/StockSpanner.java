package algorithm.monotonousStack.StockPrice;


import java.util.Stack;

/**
 * @Author： xinjingjie
 * @Date：2021/5/21 15:52
 **/
public class StockSpanner {
    Stack<Integer> prices, weights;

    public StockSpanner() {
// ["StockSpanner","next","next","next","next","next","next","next"],
// [[],[100],[80],[60],[70],[60],[75],[85]]
//输出：[null,1,1,1,2,1,4,6]
        prices = new Stack();
        weights = new Stack();
    }

    public static void main(String[] args) {
        StockSpanner spanner= new StockSpanner();
    }


    public int next(int price) {
        int w = 1;
        while (!prices.isEmpty() && prices.peek() <= price) {
            prices.pop();
            w += weights.pop();
        }

        prices.push(price);
        weights.push(w);
        return w;

    }
}
