import java.util.*;
class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, Node> nodes = new HashMap<>();
        //초기화
        for(String en : enroll){ nodes.put(en, new Node(en)); }
        
        //부모 노드 등록
        for(int i = 0; i < enroll.length; i++){
            Node cNode = nodes.get(enroll[i]);
            
            String parentName = referral[i];
            if(parentName == "-") continue;
            
            Node pNode = nodes.get(parentName);
            cNode.setParent(pNode);
        }
        
        //수입 정산
        for(int i = 0; i < seller.length; i++){
            Node sellNode = nodes.get(seller[i]);
            int income = amount[i]*100;
            
            sellNode.income(income);
        }
        
        //결과 리턴
        int[] result = new int[enroll.length];
        for(int i = 0; i < enroll.length; i++){
            result[i] = nodes.get(enroll[i]).totalMoney;
        }
        
        
        return result;
    }
    
    class Node{
        String name;
        Node parent;
        int totalMoney;
        
        public Node(String name){
            this.name = name;
        }
        
        public void income(int money){
            int cost = (int)Math.floor(money * 0.1);        
            totalMoney += money - cost;
            
            if(cost != 0 && parent != null){
                parent.income(cost);
            }
        }
        
        public void setParent(Node node){
            this.parent = node;
        }
    }
}