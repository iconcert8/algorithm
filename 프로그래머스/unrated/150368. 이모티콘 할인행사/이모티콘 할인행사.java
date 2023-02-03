class Solution {
    static int[] DCArray = new int[]{40, 30, 20, 10};
    static int plusCount = 0;
    static int payCost = 0;
    public int[] solution(int[][] users, int[] emoticons) {
        dfs(users, emoticons, new int[emoticons.length], 0);
        return new int[]{plusCount, payCost};
    }
    
    public void dfs(int[][] users, int[] emoticons, int[] dcs, int depth){
        if(depth > emoticons.length-1){
            int[] result = getResult(users, emoticons, dcs);
            if(result[0] > plusCount){
                plusCount = result[0];
                payCost = result[1];
            }else if(result[0] == plusCount){
                if(result[1] > payCost){
                    plusCount = result[0];
                    payCost = result[1];
                }
            }
            return;
        }
        
        for(int i = 0; i < 4; i++){
            dcs[depth] = DCArray[i];
            dfs(users, emoticons, dcs, depth+1);
        }
    }
    
    public int[] getResult(int[][] users, int[] emoticons, int[] dcs){
        int count = 0;
        double allTotalPay = 0;
        for(int[] user : users){
            int userDc = user[0];
            int userLimit = user[1];
            double totalPay = 0;
            for(int i = 0; i < dcs.length; i++){
                if(dcs[i] < userDc) continue;
                totalPay += (((double)100 - dcs[i])/100)*emoticons[i];
            }
            if(totalPay >= userLimit){
                count++;
            }else{
                allTotalPay += totalPay;
            }
        }
        
        return new int[]{count, (int)allTotalPay};
    }
}