package assignmentThree;

public class BST
{
    public Node root;
    
    public BST()
    {
        this.root = null;
    }

    public BST(int key)
    {
        this.root = new Node(key);
    }


    // inserts a node using just a key
    public void insert(int key)
    {
        root = insertRec(root, key);
    }


    // inserts a key using recursion
    private Node insertRec(Node root, int key)
    {
        //tree is empty
        if (root == null)
        {
            root = new Node(key);
            return root;
        }

        //traverse the tree
        if (key < root.data)
        {
            root.left = insertRec(root.left, key);
        }
        else if (key > root.data)
        {
            root.right = insertRec(root.right, key);
        }
        return root;
    }


    // searches for a key given just an int
    public boolean search(int key)
    {
        return searchRec(root, key);
    }


    // searches for a key using recursion
    private boolean searchRec(Node node, int key)
    {
        // base case #1: it's not there
        if (node == null)
        {
            return false;
        }
        // base case #2: we found it
        if (key == node.data)
        {
            return true;
        }
        // otherwise, traverse the tree
        if (key < node.data)
        {
            return searchRec(node.left, key);
        }
        else if (key > node.data)
        {
            return searchRec(node.right, key);
        }
        return false;
    }


    // prints the minimum (leftmost) value
    public int minValue(Node root)
    {
        if (root == null)
        {
            return 0;
        }
        Node n = root;
        while (n.left != null)
        {
            n = n.left;
        }
        return n.data;
    }


    // prints the inorder traversal of the tree
    public void inOrder(Node node)
    {
        // base case
        if (node == null)
        {
            return;
        }
        inOrder(node.left);
        System.out.print(node + " ");
        inOrder(node.right);
    }


    // prints the preorder traversal of the tree
    public void preOrder(Node node)
    {
        if (node == null)
        {
            return;
        }
        System.out.print(node + " ");
        preOrder(node.left);
        preOrder(node.right);
    }


    // prints the postorder traversal of the tree
    public void postOrder(Node node)
    {
        if (node == null)
        {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node + " ");
    }

    public void deleteKey(int key)
    {
        // find it
        root = deleteRec(root, key);
    }


    // deletes a node using recursion
    private Node deleteRec(Node root, int key)
    {
        Node parent = null;
        Node node = root;
 
        // search key in the BST and set its parent pointer
        while (node != null && node.data != key)
        {
            parent = node;
 
            // if the given key is less than the current node, go to the left subtree;
            // otherwise, go to the right subtree
            if (key < node.data)
            {
                node = node.left;
            }
            else
            {
                node = node.right;
            }
        }
 
        // return if the key is not found in the tree
        if (node == null) {
            return root;
        }
 
        // the node is a leaf node
        if (node.left == null && node.right == null)
        {
            // set the parent's child to null
            if (node != root)
            {
                if (parent.left == node)
                {
                    parent.left = null;
                }
                else
                {
                    parent.right = null;
                }
            }
            // otherwise, make the root null
            else
            {
                root = null;
            }
        }
 
        // the node has two children
        else if (node.left != null && node.right != null)
        {
            // find the inorder successor
            int successor = minValue(node.right);
            deleteRec(root, successor);
            node.data = successor;
        }
 
        // the node has only one child
        else
        {
            // choose a child node
            Node child = (node.left != null) ? node.left : node.right;
            if (node != root)
            {
                if (node == parent.left)
                {
                    parent.left = child;
                }
                else
                {
                    parent.right = child;
                }
            }
 
            // if the node to be deleted is a root node, then set the root to the child
            else
            {
                root = child;
            }
        }
 
        return root;
    }
}
