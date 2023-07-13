import org.junit.*;
import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import java.util.ArrayList;

public class P3_Test {
  
  @Test
  public void insertTest(){
    BinarySearchTree<Integer, String> list = new BinarySearchTree<Integer, String>();
//inserting to an empty list
    list.insert(6, "John");
    assertEquals("6 ", list.toString());
    
//inserting into a smaller key than the parent
    list.insert(2, "Joseph");
    assertEquals("2 6 ", list.toString());
    
//inserting into a larger key than the parent
    list.insert(9, "Juma");
    assertEquals("2 6 9 ", list.toString());
  }
  
  @Test
  public void searchTest(){
    BinarySearchTree<Integer, String> tree = new BinarySearchTree<Integer, String>();
//searching through an empty list
    assertEquals(null, tree.search(8));
    tree.insert(6, "John");
    tree.insert(5, "Juma");
    tree.insert(8, "Joseph");
    tree.insert(2, "Julia");
    tree.insert(3, "Juru");
    tree.insert(1, "Jackson");
    tree.insert(7, "Julius");
    tree.insert(10, "Jeremia");
    tree.insert(9, "Jimmy");
    tree.insert(4, "Jemima");
    
//searching a left key
    assertEquals("Jackson", tree.search(1));
    
//searching the root
    assertEquals("John", tree.search(6));
    
//searching a right key
    assertEquals("Jeremia", tree.search(10));
  }
  
  @Test
  public void deleteTest(){
    BinarySearchTree<Integer, String> tree = new BinarySearchTree<Integer, String>();
    tree.insert(6, "John");
    tree.insert(5, "Juma");
    tree.insert(8, "Joseph");
    tree.insert(2, "Julia");
    tree.insert(3, "Juru");
    tree.insert(1, "Jackson");
    tree.insert(7, "Julius");
    tree.insert(10, "Jeremia");
    tree.insert(9, "Jimmy");
    tree.insert(4, "Jemima");
    
//deleting a left leaf node
    tree.delete(4);
    assertEquals("1 2 3 5 6 7 8 9 10 ", tree.toString());
    
//deleting a right leaf node
    tree.delete(9);
    assertEquals("1 2 3 5 6 7 8 10 ", tree.toString());
    
//deleting a node with one child
    tree.delete(2);
    assertEquals("1 3 5 6 7 8 10 ", tree.toString());
    
//deleting a node with two children
    tree.delete(6);
    assertEquals("1 3 5 7 8 10 ", tree.toString());
  }
  
  @Test
  public void kthSmallest(){
    BinarySearchTree<Integer, String> tree = new BinarySearchTree<Integer, String>();
    tree.insert(6, "John");
    tree.insert(5, "Juma");
    tree.insert(8, "Joseph");
    tree.insert(2, "Julia");
    tree.insert(3, "Juru");
    tree.insert(1, "Jackson");
    tree.insert(7, "Julius");
    tree.insert(10, "Jeremia");
    tree.insert(9, "Jimmy");
    tree.insert(4, "Jemima");
    
    assertEquals("Jackson", tree.kthSmallest(1));
    assertEquals("Jimmy", tree.kthSmallest(10));
// assertEquals("Juru", tree.kthSmallest(3));
  }
  
  @Test
  public void inOrderRecTest(){
    BinarySearchTree<Integer, String> tree = new BinarySearchTree<Integer, String>();
    tree.insert(6, "John");
    tree.insert(5, "Juma");
    tree.insert(8, "Joseph");
    tree.insert(2, "Julia");
    tree.insert(3, "Juru");
    tree.insert(1, "Jackson");
    tree.insert(7, "Julius");
    tree.insert(10, "Jeremia");
    tree.insert(9, "Jimmy");
    tree.insert(4, "Jemima");
    
    ArrayList<String> arr = new ArrayList<String>();
    arr.add("Jackson");
    arr.add("Julia");
    arr.add("Juru");
    arr.add("Jemima");
    arr.add("Juma");
    arr.add("John");
    arr.add("Julius");
    arr.add("Joseph");
    arr.add("Jimmy");
    arr.add("Jeremia");
    assertEquals(arr, tree.inorderRec());
  }
}  