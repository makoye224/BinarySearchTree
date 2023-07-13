import java.util.List;
import java.util.ArrayList;
import java.lang.Math;
/**
 * a class BinarySearchTree
 * @author Emmanuel Makoye
 */
public class AVLTree<T extends Comparable<T>, V> {
  private Node<T, V> parent;
  private int counter;
  private ArrayList<V> list;
  
//a constructor for the class BST
  public AVLTree() {
    parent = null;
    list = new ArrayList<V>();
  }
  
//inserts a key and value into the BST
  public void insert(T key, V val) {
    parent = add(parent, key, val);
  }
  
//a helper method for the insert that makes recursion easy
  public Node<T, V> add(Node<T, V> root, T key, V val) {
//inserting when the node is empty
    if(root == null) {
      return new Node<T, V>(key, val);
    }
    else {
//inserting to the left node
      if(key.compareTo(root.getKey()) < 0) {
        root.setLeft(add(root.getLeft(), key, val));
      }
      else {
        root.setRight(add(root.getRight(), key, val));
      }
    }
    updateHeight(root);
//rotates the node where necessary;
    return applyRotation(root);
  }
  
//returns the value given a specific key
  public V search(T key) {
    return find(parent, key);
  }
  
//a helper method for the search method that makes recursion easy
  public V find(Node<T, V> root, T key) {
//searching from an empty node
    if(root == null){
      return null;
    }
//searching from the left nodes
    if(key.compareTo(root.getKey()) < 0) {
      return find(root.getLeft(), key);
    }
//searching from the right nodes
    else if(key.compareTo(root.getKey()) > 0){
      return find(root.getRight(), key);
    }
//found the node, so return it's value
    else{
      return root.getValue();
    }
  }
  
//returns a List that contains all elements of the BST
  public List<V> inorderRec() {
    inorderPrint(parent);
    ArrayList<V> arr = list;
    list = new ArrayList<V>();
    return arr;
  }
  
//a helper method that adds elements into List inOrder traversal
  public void inorderPrint(Node<T, V> root) {
    list.add(root.getValue());
    if(root.getLeft() != null) {
      inorderPrint(root.getLeft());
    }
    if(root.getRight() != null) {
      inorderPrint(root.getRight());
    }
  }
  
//returns the value of a specific position in the BST
  public V kthSmallest(int k) {
    return kthSmallest(parent, k).getValue();
  }
  
//helper method for the kthSmallest that uses recursion
  public Node<T, V> kthSmallest(Node<T, V> root, int k){
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
  
//deletes a specific node from the BST
  public void delete(T key) {
    parent = remove(key, parent);
  }
  
//a helper method that returns a node with all nodes except the one deleted
  private Node<T, V> remove(T x , Node<T, V> t )
  {
    if( t == null )
      return null ; // Item not found ; do nothing
    int compareResult = x.compareTo (t.getKey()) ;
    if( compareResult < 0 )
      t.setLeft(remove ( x , t.getLeft())) ;
    else if( compareResult > 0 )
      t.setRight(remove (x , t.getRight())) ;
    else{
      if(t.getLeft() == null){
        return t.getRight();
      }
      else if(t.getRight() == null){
        return t.getLeft();
      }
      t.setKey(findMax(t.getLeft()).getKey()) ;
      t.setLeft(remove(t.getKey(), t.getLeft()));
    }
//updates the height of the node
    updateHeight(t);
//rotates the node where necessary
    return applyRotation(t);
    
  }
  
  
//finds the minimum node given a node
  private Node<T, V> findMax(Node<T, V> root){
    while(root.getRight() != null) {
      root = root.getRight();
    }
    return root;
  }
  
//returns a string representation of the BST
  public String toString(){
    return parent.toString();
  }
  
  private void updateHeight(Node<T, V> node) {
    int maxHeight = Math.max(height(node.getLeft()), height(node.getRight()));
    node.setHeight(maxHeight + 1);
  }
  
  private int height(Node<T, V> node) {
    return node != null ? node.getHeight() : 0;
  }
  
  private Node<T, V> applyRotation(Node<T, V> node){
    int balance = balance(node);
    if(balance > 1) {
      if(balance(node.getLeft()) < 0){
        node.setLeft(rotateLeft(node.getLeft()));
      }
      return rotateRight(node);
    }
    if(balance < -1) {
      if(balance(node.getRight()) > 0){
        node.setRight(rotateRight(node.getRight()));
      }
      return rotateLeft(node);
    }
    return node;
  }
  
  private Node<T, V> rotateRight(Node<T, V> node){
    Node<T, V> leftNode = node.getLeft();
    Node<T, V> centreNode = leftNode.getRight();
    leftNode.setRight(node);
    node.setLeft(centreNode);
    updateHeight(node);
    updateHeight(leftNode);
    return leftNode;
  }
  
  private Node<T, V> rotateLeft(Node<T, V> node){
    Node<T, V> rightNode = node.getRight();
    Node<T, V> centreNode = rightNode.getLeft();
    rightNode.setLeft(node);
    node.setRight(centreNode);
    updateHeight(node);
    updateHeight(rightNode);
    return rightNode;
  }
  
  
  private int balance(Node<T, V> node) {
    return node != null ? height(node.getLeft()) - height(node.getRight()): 0;
  }
  
  public int balance() {
    return balance(parent);
  }
  
  public static void main(String[] args) {
    AVLTree<Integer, Integer> tree = new AVLTree<Integer, Integer>();
    tree.insert(2, 2);
    tree.insert(1, 1);
    tree.insert(4, 4);
    tree.insert(5, 5);
    tree.insert(9, 9);
    tree.insert(0, 0);
    tree.insert(6, 6);
    tree.insert(-1, -1);
    tree.insert(10, 10);
    tree.insert(12, 12);
    tree.insert(11, 11);
    tree.delete(4);
    tree.delete(9);
    System.out.println("ArrayList inOrderTravesal: " + tree.inorderRec());
    System.out.println("4 is deleted so returns null: " + tree.search(4)); // 4 is deleted so it will return null
    System.out.println("searching 12: " + tree.search(12));
    System.out.println("3rd smallest value: " + tree.kthSmallest(3));
    
    for(int i = 0; i < 10; i++) {
      int n = (int)( Math.random() * 10 + 0.5);
      tree.insert(n, n);
      System.out.println("Balance after inserting " + n + ' ' + tree.balance());
    }
    System.out.println("ArrayList preOrderTravesal: " + tree.inorderRec());
  }
}
