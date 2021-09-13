class Solution {
    public int maxProfit(int[] prices) {
        int profit=0;
        for(int i=1;i<prices.length;i++) profit+=(prices[i]-prices[i-1]>0)?prices[i]-prices[i-1]:0;
        return profit;
        // return IntStream.range(1, prices.length).filter(idx -> prices[idx] - prices[idx-1] > 0).map(idx -> prices[idx] - prices[idx-1]).sum();
        // 1return IntStream.range(1, prices.length).map(i -> (prices[i]-prices[i-1]>0)?prices[i]-prices[i-1]:0).sum();
        // return IntStream.range(1, prices.length).map(idx -> prices[idx] - prices[idx-1]).filter(diff -> diff > 0).sum();
    }
}