import java.util.LinkedList;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int number;
        int num1;
        int num2;
        int res1;
        int res2;
        String reader;
        String nums[];
        Scanner scanner = new Scanner(System.in);
        number = Integer.valueOf(scanner.nextLine());
        Tree tree = new Tree(number);

        for (int i = 0; i < number - 1; i++) {
            reader = scanner.nextLine();
            nums = reader.split(" ");
            num1 = Integer.valueOf(nums[0]);
            num2 = Integer.valueOf(nums[1]);
            tree.setEdge(num1 - 1, num2 - 1);
        }

        res1 = tree.findLongestDistance(true, 0);
        res2 = tree.findLongestDistance(false, res1);
        System.out.println(res2);

    }
}

class Tree {

    private int numberOfNodes;
    private ArrayList<Integer>[] treeNodes;

    Tree(int size) {
        numberOfNodes = size;
        treeNodes = new ArrayList[numberOfNodes];

        for (int i = 0; i < numberOfNodes; i++) {
            treeNodes[i] = new ArrayList<>();
        }
    }

    public void setEdge(int n1, int n2) {
        treeNodes[n1].add(n2);
        treeNodes[n2].add(n1);
    }

    public int findLongestDistance(boolean index, int root) {

        int distance[] = new int[numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) {
            distance[i] = -1;
        }
        distance[root] = 0;

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {

            int node = queue.getFirst();
            queue.removeFirst();

            for (int i = 0; i < treeNodes[node].size(); i++) {

                int child = treeNodes[node].get(i);

                if (distance[child] == -1) {
                    queue.add(child);
                    distance[child] = distance[node] + 1;
                }
            }
        }

        int maxDistance = 0;
        int maxDistanceIndex = 0;

        for (int i = 0; i < numberOfNodes; i++) {
            if (distance[i] > maxDistance) {
                maxDistance = distance[i];
                maxDistanceIndex = i;
            }
        }

        if (index) {
            return maxDistanceIndex;
        } else {
            return maxDistance;
        }

    }

}
