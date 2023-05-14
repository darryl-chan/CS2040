/**
 * Name: Darryl Chan
 * Matric. No: A0177482U
 */

import java.util.*;
public class Soccer {
  
  public class Team implements Comparable<Team>{
    public String name;
    public int score;
    public int order;
    
    public Team(String n, int s, int o){
      name = n;
      score = s;
      order = o;
    }
  
  @Override
  public int compareTo(Team team){
    if (team.score > score){return -1;}
    else if (team.score < score){return 1;}
    else{
      if(team.order < order){return -1;}
      else{return -1;}
    }
  }
  
     public void changeScore(int x){
     score += x; 
     }
  }
  
  class AVLTree<Key extends Comparable<Key>, Value> {
  private Node root;

  private class Node {
    private final Key key;
    private Value val;
    private int height;
    private int size;
    private Node left;
    private Node right;

    public Node(Key key, Value val, int height, int size) {
      this.key = key;
      this.val = val;
      this.size = size;
      this.height = height;
    }
  }

  public int size() {
    return size(root);
  }

  private int size(Node x) {
    if (x == null)
      return 0;
    return x.size;
  }

  private int height(Node x) {
    if (x == null)
      return -1;
    return x.height;
  }

  public Value get(Key key) {
    Node x = get(root, key);
    if (x == null)
      return null;
    return x.val;
  }

  private Node get(Node x, Key key) {
    if (x == null)
      return null;
    int cmp = key.compareTo(x.key);
    if (cmp < 0)
      return get(x.left, key);
    else if (cmp > 0)
      return get(x.right, key);
    else
      return x;
  }

  public boolean contains(Key key) {
    return get(key) != null;
  }

  public void put(Key key, Value val) {
    if (val == null) {
      delete(key);
      return;
    }
    root = put(root, key, val);
  }

  private Node put(Node x, Key key, Value val) {
    if (x == null)
      return new Node(key, val, 0, 1);
    int cmp = key.compareTo(x.key);
    if (cmp < 0) {
      x.left = put(x.left, key, val);
    } else if (cmp > 0) {
      x.right = put(x.right, key, val);
    } else {
      x.val = val;
      return x;
    }
    x.size = 1 + size(x.left) + size(x.right);
    x.height = 1 + Math.max(height(x.left), height(x.right));
    return balance(x);
  }

  private Node balance(Node x) {
    if (balanceFactor(x) < -1) {
      if (balanceFactor(x.right) > 0) {
        x.right = rotateRight(x.right);
      }
      x = rotateLeft(x);
    } else if (balanceFactor(x) > 1) {
      if (balanceFactor(x.left) < 0) {
        x.left = rotateLeft(x.left);
      }
      x = rotateRight(x);
    }
    return x;
  }

  private int balanceFactor(Node x) {
    return height(x.left) - height(x.right);
  }

  private Node rotateRight(Node x) {
    Node y = x.left;
    x.left = y.right;
    y.right = x;
    y.size = x.size;
    x.size = 1 + size(x.left) + size(x.right);
    x.height = 1 + Math.max(height(x.left), height(x.right));
    y.height = 1 + Math.max(height(y.left), height(y.right));
    return y;
  }

  private Node rotateLeft(Node x) {
    Node y = x.right;
    x.right = y.left;
    y.left = x;
    y.size = x.size;
    x.size = 1 + size(x.left) + size(x.right);
    x.height = 1 + Math.max(height(x.left), height(x.right));
    y.height = 1 + Math.max(height(y.left), height(y.right));
    return y;
  }

  public void delete(Key key) {
    if (!contains(key))
      return;
    root = delete(root, key);
  }

  private Node delete(Node x, Key key) {
    int cmp = key.compareTo(x.key);
    if (cmp < 0) {
      x.left = delete(x.left, key);
    } else if (cmp > 0) {
      x.right = delete(x.right, key);
    } else {
      if (x.left == null) {
        return x.right;
      } else if (x.right == null) {
        return x.left;
      } else {
        Node y = x;
        x = min(y.right);
        x.right = deleteMin(y.right);
        x.left = y.left;
      }
    }
    x.size = 1 + size(x.left) + size(x.right);
    x.height = 1 + Math.max(height(x.left), height(x.right));
    return balance(x);
  }

  private Node deleteMin(Node x) {
    if (x.left == null)
      return x.right;
    x.left = deleteMin(x.left);
    x.size = 1 + size(x.left) + size(x.right);
    x.height = 1 + Math.max(height(x.left), height(x.right));
    return balance(x);
  }

  private Node min(Node x) {
    if (x.left == null)
      return x;
    return min(x.left);
  }
}
  
  public static void main(String args[]) {
    
  }
}

