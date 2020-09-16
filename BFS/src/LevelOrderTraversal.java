import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {
    /*
    Given a binary tree, populate an array to represent its level-by-level traversal.
    You should populate the values of all nodes of each level from left to right in separate sub-arrays.
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode next;
        TreeNode(int x){
            val = x;
            next = null;
        }

    }

    public static List<List<Integer>> levelOrderTraverse(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            int levelSize = q.size();
            List<Integer> curLevel = new ArrayList<>(levelSize);
            for (int i = 0; i < levelSize; i++){
                TreeNode curNode = q.poll();
                curLevel.add(curNode.val);
                if (curNode.left != null){
                    q.offer(curNode.left);
                }
                if (curNode.right != null){
                    q.offer(curNode.right);
                }
            }
            res.add(curLevel);
        }
        return res;
    }

    public static List<List<Integer>> reverseLevelOrderTraverse(TreeNode root) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if (root == null)
            return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()){
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++){
                TreeNode curNode = q.poll();
                level.add(curNode.val);
                if (curNode.right != null)
                    q.offer(curNode.right);
                if (curNode.left != null)
                    q.offer(curNode.left);
            }
            res.add(0, level);
        }

        return res;
    }

    public static List<List<Integer>> zigZagLevelOrderTraverse(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean leftToRight = true;

        while (!q.isEmpty()){
            int size = q.size();
            List<Integer> level = new LinkedList<>();
            for (int i = 0; i < size; i++){
                TreeNode curNode = q.poll();

                if (leftToRight)
                    level.add(curNode.val);
                else
                    level.add(0, curNode.val);

                if (curNode.left != null)
                    q.offer(curNode.left);
                if (curNode.right != null)
                    q.offer(curNode.right);

            }
            res.add(level);
            leftToRight = !leftToRight;
        }

        return res;
    }

    public static List<Double> avgLevelOrderTraverse(TreeNode root){
        List<Double> res = new ArrayList<>();
        if (root == null)
            return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int levelSize = q.size();
            double sum = 0;
            for (int i = 0; i < levelSize; i++){
                TreeNode curNode = q.poll();
                sum += curNode.val;
                if (curNode.left != null){
                    q.offer(curNode.left);
                }
                if (curNode.right != null){
                    q.offer(curNode.right);
                }
            }
            res.add(sum/levelSize);
        }
        return res;
    }

    public static int findDepth(TreeNode root){
        int res = 0;
        if (root == null)
            return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            res++;
            for (int i = 0; i < size; i++){
                TreeNode curNode = q.poll();


                if (curNode.left != null)
                    q.offer(curNode.left);
                if (curNode.right != null)
                    q.offer(curNode.right);
            }
        }


        return res;
    }

    public static TreeNode findSuccessor(TreeNode root, int key){
        if (root == null)
            return null;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            for (int i = 0; i < size; i++){
                TreeNode curNode = q.poll();
                // insert children of node into queue
                if (curNode.left != null)
                    q.offer(curNode.left);
                if (curNode.right != null)
                    q.offer(curNode.right);
                // break if key is found
                if (curNode.val == key)
                    break;
            }
        }
        return q.peek();
    }

    public static void connect(TreeNode root){
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            TreeNode prevNode = null;
            int size = q.size();
            for (int i = 0; i < size; i++){
                TreeNode curNode = q.poll();
                // insert children of node into queue
                if (prevNode != null)
                    prevNode.next = curNode;

                prevNode = curNode;

                if (curNode.left != null)
                    q.offer(curNode.left);
                if (curNode.right != null)
                    q.offer(curNode.right);

            }
        }
    }

    public static void connectAllLevelOrder(TreeNode root){
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            TreeNode prevNode = null;
            TreeNode curNode = null;
            int size = q.size();
            for (int i = 0; i < size; i++){
                curNode = q.poll();

                if (prevNode != null)
                    prevNode.next = curNode;
                prevNode = curNode;

                if (curNode.left != null)
                    q.offer(curNode.left);
                if (curNode.right != null)
                    q.offer(curNode.right);

            }
        }
    }

    public static List<TreeNode> rightMostNodes(TreeNode root){
        if (root == null)
            return null;
        List<TreeNode> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            for (int i = 0; i < q.size(); i++){
                TreeNode curNode = q.poll();
                if (i == q.size() - 1)
                    res.add(curNode);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        root.right.left.left = new TreeNode(20);
        root.right.left.right = new TreeNode(17);
        root.right.left.right.left = new TreeNode(22);

        System.out.println(findDepth(root));

        TreeNode root2 = new TreeNode(12);
        root2.left = new TreeNode(7);
        root2.right = new TreeNode(1);
        root2.left.left = new TreeNode(9);
        root2.left.right = new TreeNode(2);
        root2.right.left = new TreeNode(10);
        root2.right.right = new TreeNode(5);

        System.out.println(findDepth(root2));

    }
}
