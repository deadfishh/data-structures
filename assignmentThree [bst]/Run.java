package assignmentThree;

public class Run
{
    public static void main(String[] args)
    {
        demonstrate();
    }


    // does the demonstrations
    public static void demonstrate()
    {
        // adding things to the tree
        BST tree = new BST();
        tree.insert(45);
        tree.insert(10);
        tree.insert(7);
        tree.insert(12);
        tree.insert(90);
        tree.insert(50);

        // printing the tree
        System.out.println("here is the tree");
        tree.inOrder(tree.root);
        System.out.println();

        // deleting a leaf node
        System.out.println("now we will delete 12, a leaf node");
        tree.deleteKey(12);
        tree.inOrder(tree.root);
        System.out.println();

        // deleting a node with one child
        System.out.println("now we will delete 90, a node with one child");
        tree.deleteKey(90);
        tree.inOrder(tree.root);
        System.out.println();

        // deleting a node with two children
        System.out.println("now we will delete 45, a node with two children");
        tree.deleteKey(45);
        tree.inOrder(tree.root);
        System.out.println();

        // searching for various keys
        System.out.println("key 50 is in the tree: " + tree.search(50));
        System.out.println("key 12 is in the tree: " + tree.search(12));

        // preorder traversal
        System.out.println("here is a preorder traversal");
        tree.preOrder(tree.root);
        System.out.println();

        // inorder traversal
        System.out.println("here is an inorder traversal");
        tree.inOrder(tree.root);
        System.out.println();

        // postorder traversal
        System.out.println("here is a postorder traversal");
        tree.postOrder(tree.root);
        System.out.println();
    }
   

}
