package com.xiao9.user.domain.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class JavaTest {

    TreeNode left;
    TreeNode right;
    TreeNode root;


    @BeforeEach
    void init() {
        left = new TreeNode(3);
        right = new TreeNode(9);
        root = new TreeNode(6, left, right);
    }


    @Test
    void pre() {
        List<Integer> result = preorderTraversal(root);
        Assertions.assertEquals(3, result.size());
    }


    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if(node.right != null) {
                stack.push(node.right);
            }
            if(node.left != null) {
                stack.push(node.left);
            }
        }
        return result;
    }


    private void preorderTraversal(List<Integer> result, TreeNode node) {

    }



    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
