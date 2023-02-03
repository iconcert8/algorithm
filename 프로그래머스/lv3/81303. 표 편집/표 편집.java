import java.util.*;
class Solution {
    public String solution(int n, int k, String[] cmd) {
        List<LinkedNode> totalList = new ArrayList<>();
        LinkedNode prevNode = new LinkedNode(0);
        totalList.add(prevNode);
        for (int i = 1; i < n; i++) {
            LinkedNode linkedNode = new LinkedNode(i);
            linkedNode.prevNode = prevNode;
            prevNode.nextNode = linkedNode;

            totalList.add(linkedNode);
            prevNode = linkedNode;
        }


        LinkedNode currentNode = totalList.get(k);
        Stack<LinkedNode> deleteNodeStack = new Stack<>();
        for (String cm : cmd) {
            String[] splitCm = cm.split(" ");
            String command = splitCm[0];
            int distance = 0;
            if (splitCm.length > 1) {
                distance = Integer.parseInt(splitCm[1]);
            }

            switch (command) {
                case "U":
                    currentNode = currentNode.getPrevNode(distance);
                    break;
                case "D":
                    currentNode = currentNode.getNextNode(distance);
                    break;
                case "C":
                    deleteNodeStack.add(currentNode);
                    currentNode = currentNode.delete();
                    break;
                case "Z":
                    if (deleteNodeStack.peek() != null) {
                        LinkedNode deletedNode = deleteNodeStack.pop();
                        deletedNode.insert();
                    }
                    break;
            }
        }

        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < totalList.size(); i++) {
            result.append(totalList.get(i).isDeleted ? "X" : 'O');
        }

        return result.toString();
    }
    
    class LinkedNode {
        int name;
        LinkedNode prevNode;
        LinkedNode nextNode;

        boolean isDeleted = false;

        public LinkedNode(int name) {
            this.name = name;
        }

        LinkedNode delete() {
            if (prevNode != null) {
                prevNode.nextNode = nextNode;
            }
            if (nextNode != null) {
                nextNode.prevNode = prevNode;
            }

            isDeleted = true;

            return nextNode == null ? prevNode : nextNode;
        }

        void insert() {
            if (prevNode != null) {
                prevNode.nextNode = this;
            }

            if (nextNode != null) {
                nextNode.prevNode = this;
            }

            isDeleted = false;
        }


        LinkedNode getPrevNode(int distance) {
            if (distance == 0) {
                return this;
            }

            return prevNode.getPrevNode(distance - 1);
        }

        LinkedNode getNextNode(int distance) {
            if (distance == 0) {
                return this;
            }

            return nextNode.getNextNode(distance - 1);

        }
    }
}