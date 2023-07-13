import java.util.List;
import java.util.ArrayList;

/**
 * a class BinarySearchTree
 * 
 * @author Emmanuel Makoye
 */
public class BinarySearchTree<T extends Comparable<T>, V> {
  private Node<T, V> parent;
  private int counter;
  private ArrayList<V> list;

  // a constructor for the class BST
  public BinarySearchTree() {
    parent = null;
    list = new ArrayList<V>();
  }

  // inserts a key and value into the BST
  public void insert(T key, V val) {
    // inserting when the BST is empty
    if (parent == null) {
      parent = new Node<T, V>(key, val);
    } else {
      // inserting when the BST is not empty
      add(parent, key, val);
    }
  }

  // a helper method for the insert that makes recursion easy
  public void add(Node<T, V> root, T key, V val) {
    // inserting when the node is empty
    if (root == null) {
      root = new Node<T, V>(key, val);
    } else {
      // inserting to the left node
      if (key.compareTo(root.getKey()) <= 0) {
        if (root.getLeft() == null) {
          root.setLeft(new Node<T, V>(key, val));
        } else {
          add(root.getLeft(), key, val);
        }
      }
      // inserting to the right node
      else if (key.compareTo(root.getKey()) > 0) {
        if (root.getRight() == null) {
          root.setRight(new Node<T, V>(key, val));
        } else {
          add(root.getRight(), key, val);
        }
      }

    }

  }

  // returns the value given a specific key
  public V search(T key) {
    return find(parent, key);
  }

  // a helper method for the search method that makes recursion easy
  public V find(Node<T, V> root, T key) {
    // searching from an empty node
    if (root == null) {
      return null;
    }
    // searching from the left nodes
    if (key.compareTo(root.getKey()) < 0) {
      return find(root.getLeft(), key);
    }
    // searching from the right nodes
    else if (key.compareTo(root.getKey()) > 0) {
      return find(root.getRight(), key);
    }
    // found the node, so return it's value
    else {
      return root.getValue();
    }
  }

  // returns a List that contains all elements of the BST
  public List<V> inorderRec() {
    inOrderPrint(parent);
    ArrayList<V> arr = list;
    list = new ArrayList<V>();
    return arr;
  }

  // a helper method that adds elements into List inOrder traversal
  public void inOrderPrint(Node<T, V> root) {
    if (root.getLeft() != null) {
      inOrderPrint(root.getLeft());
    }
    list.add(root.getValue());
    if (root.getRight() != null) {
      inOrderPrint(root.getRight());
    }
  }

  // returns the value of a specific position in the BST
  public V kthSmallest(int k) {
    return kthSmallest(parent, k).getValue();
  }

  // helper method for the kthSmallest that uses recursion
  public Node<T, V> kthSmallest(Node<T, V> root, int k) {
    // base case
    if (root == null)
      return null;

    // search in left subtree
    Node<T, V> left = kthSmallest(root.getLeft(), k);

    // if k'th smallest is found in left subtree, return it
    if (left != null)
      return left;

    // if current element is k'th smallest, return it
    counter++;
    if (counter == k)
      return root;

    // else search in right subtree
    return kthSmallest(root.getRight(), k);
  }

  // deletes a specific node from the BST
  public void delete(T key) {
    parent = remove(key, parent);
  }

  // a helper method that returns a node with all nodes except the one deleted
  private Node<T, V> remove(T x, Node<T, V> t) {
    if (t == null)
      return t; // Item not found ; do nothing
    int compareResult = x.compareTo(t.getKey());
    if (compareResult < 0)
      t.setLeft(remove(x, t.getLeft()));
    else if (compareResult > 0)
      t.setRight(remove(x, t.getRight()));
    else if (t.getLeft() != null && t.getRight() != null) // Two children
    {
      t.setKey(findMin(t.getRight()).getKey());
      t.setValue(findMin(t.getRight()).getValue());
      t.setRight(remove(t.getKey(), t.getRight()));
    } else
      t = (t.getLeft() != null) ? t.getLeft() : t.getRight();
    return t;
  }

  // finds the minimum node given a node
  private Node<T, V> findMin(Node<T, V> root) {
    while (root.getLeft() != null) {
      root = root.getLeft();
    }
    return root;
  }

  // returns a string representation of the BST
  public String toString() {
    return parent.toString();
  }

  public static void main(String[] args) {
    BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<Integer, Integer>();
    tree.insert(2, 2);
    tree.insert(1, 1);
    tree.insert(4, 4);
    tree.insert(5, 5);
    tree.insert(9, 9);
    tree.insert(3, 3);
    tree.insert(6, 6);
    tree.insert(7, 7);
    tree.insert(10, 10);
    tree.insert(12, 12);
    tree.insert(11, 11);
    tree.delete(4);
    tree.delete(9);
    System.out.println("ArrayList inOrderTravesal: " + tree.inorderRec());
    System.out.println("null because 4 is deleted: " + tree.search(4)); // 4 is deleted so it will return null
    System.out.println("searching 12: " + tree.search(12));
    System.out.println("3rd smallest value: " + tree.kthSmallest(3));

    System.out.println("............................................................................\n");

    BinarySearchTree<Double, String> list = new BinarySearchTree<Double, String>();
    list.insert(2.0, "John");
    list.insert(1.0, "James");
    list.insert(4.0, "Justice");
    list.insert(5.0, "Juru");
    list.insert(9.0, "Juma");
    list.insert(3.0, "Jackson");
    list.insert(6.0, "Jeniffer");
    list.insert(7.0, "Jamshed");
    list.insert(10.0, "Jun");
    list.insert(12.0, "Jemima");
    list.insert(11.0, "Jojo");

    list.delete(4.0);
    list.delete(9.0);
    System.out.println("ArayList inOrderTravesal: " + list.inorderRec());
    System.out.println("searching 5.0: " + list.search(5.0));
    System.out.println("searching 11.0: " + list.search(11.0));
    System.out.println("3rd smallest value: " + list.kthSmallest(3));
  }
}
