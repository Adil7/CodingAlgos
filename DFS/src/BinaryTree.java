public class BinaryTree {

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

    public static boolean hasPathSum(TreeNode root, int sum){
        if (root == null)
            return false;

        if (root.val == sum && root.left == null && root.right == null)
            return true;

        return hasPathSum(root.left, sum - root.left.val) || hasPathSum(root.right,sum-root.right.val);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

    }
}
