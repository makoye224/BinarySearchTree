public class Node<T extends Comparable<T>, V> {
  private T key;
  private V value;
  private Node<T, V> left;
  private Node<T, V> right;
  private int height;

  // the Node Constructor
  public Node(T key, V value) {
    this.key = key;
    this.value = value;
    height = 1;
  }

  // sets left Node
  public void setLeft(Node<T, V> node) {
    left = node;
  }

  // sets Right Node
  public void setRight(Node<T, V> node) {
    right = node;
  }

  // retrieves left Node
  public Node<T, V> getLeft() {
    return left;
  }

  // retrieves right Node
  public Node<T, V> getRight() {
    return right;
  }

  // retrieves the Node value
  public V getValue() {
    return value;
  }

  // retrieves the Node key
  public T getKey() {
    return key;
  }

  // sets the Node value
  public void setValue(V val) {
    value = val;
  }

  // sets the Node key
  public void setKey(T ke) {
    key = ke;
  }

  public void setHeight(int h) {
    height = h;
  }

  public int getHeight() {
    return height;
  }

  // returns a string representation of this Node
  public String toString() {
    String s = "";

    if (left != null) {
      s = s + left.toString();
    }
    s = s + key.toString() + ' ';
    if (right != null) {
      s = s + right.toString();
    }
    return s;
  }

}
