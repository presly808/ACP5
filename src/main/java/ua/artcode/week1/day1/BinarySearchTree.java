package ua.artcode.week1.day1;

import javax.swing.tree.TreeNode;
import java.util.Comparator;

/**
 * Created by serhii on 19.01.15.
 */
public class BinarySearchTree<E> implements ITree<E> {

    private TreeNode root;
    private Comparator<E> comparator;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public boolean add(E el) {

        if(!(el instanceof Comparable)){
            System.err.println("");
            return false;
        }
        Comparable<E> compEl = (Comparable<E>) el;

        if(root == null){
            root = new TreeNode(null, el, null, null);
            return true;
        }


        TreeNode iter = root;
        while(iter != null){
            if(compEl.compareTo(iter.val) < 0){
                if(iter.lChild == null){
                    iter.lChild = new TreeNode(iter, el, null, null);
                    return true;
                } else {
                    iter = iter.lChild;
                }
            } else if(compEl.compareTo(iter.val) > 0){
                if(iter.rChild == null){
                    iter.rChild = new TreeNode(iter, el, null, null);
                    return true;
                } else {
                    iter = iter.rChild;
                }
            }
        }


        return false;
    }



    @Override
    public boolean remove(E el) {
        return false;
    }

    @Override
    public boolean contains(Object el) {
        return false;
    }

    @Override
    public void printAsc() {
        print(root);
    }

    private void print(TreeNode node){
        if(node == null){
            return;
        }

        print(node.lChild);
        System.out.println(node.val);
        print(node.rChild);

    }

    @Override
    public void printDesc() {

    }


    @Override
    public String toString() {
        return "BinarySearchTree{}";
    }

    private class TreeNode {

        TreeNode parent;
        E val;
        TreeNode lChild;
        TreeNode rChild;


        public TreeNode() {
        }

        public TreeNode(TreeNode parent, E val, TreeNode lChild, TreeNode rChild) {
            this.parent = parent;
            this.val = val;
            this.lChild = lChild;
            this.rChild = rChild;
        }
    }

}
