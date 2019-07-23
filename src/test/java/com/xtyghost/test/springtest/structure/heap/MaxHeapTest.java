package com.xtyghost.test.springtest.structure.heap;

import java.util.Random;

public class MaxHeapTest {
    public static void main(String[] args) {
        int n = 100;
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        random.ints(n).forEach(maxHeap::add);
//        for (int i = 0; i < n; i++) {
//            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
//
//        }
        System.out.println(maxHeap);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i]=maxHeap.extractMax();
        }
        for (int i = 1; i < n; i++) {
            if (arr[i-1]<arr[i]){
                System.out.println(i);
                throw new IllegalArgumentException("Error");
            }

        }
        System.out.println("test maxheap completed");
    }


}