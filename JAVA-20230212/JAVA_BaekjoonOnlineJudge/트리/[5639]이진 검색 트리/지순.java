import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_5639_이진검색트리 {
    static class Node {
        int data;
        Node left, right;
        Node(int data) {
            this.data = data;
        }
        public void add(int idx) {
            if(idx<this.data) {
                if(this.left == null)
                    this.left = new Node(idx);
                else
                    this.left.add(idx);
            } else {
                if(this.right == null)
                    this.right = new Node(idx);
                else
                    this.right.add(idx);
            }
        }
    }

    public static void postOrder(Node root) {
        if (root.left != null) postOrder(root.left);
        if (root.right != null) postOrder(root.right);
        System.out.println(root.data);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node root = new Node(Integer.parseInt(br.readLine()));

        while(true) {
            String input = br.readLine();
            if (input == null || input.equals("")) break;

            root.add(Integer.parseInt(input));
        }

        postOrder(root);
    }
}
