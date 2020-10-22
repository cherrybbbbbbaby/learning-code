package com.djh.demo.sort;

public class SelectionSort extends Base{

    public static void main(String[] args) {
        new SelectionSort().sort();
    }

    @Override
    public void sort() {
        //头指针指向数组的第一个下标，每遍历一次向后移动一位
        //遍历length-1次数组，找出头指针往后的数组中最小的数，和头指针交换位置


        int arrayLength = array.length;
        for (int i = 0; i < arrayLength; i++) {
            int temp = array[i];
            int min = array[i];
            int exchangeIndex = -1;
            for (int j = i; j < arrayLength -1; j++) {
                if (array[j] < min){
                    min = array[j];
                    exchangeIndex = j;
                }
            }
            if (exchangeIndex != -1){
                array[exchangeIndex] = temp;
                array[i] = min;
            }
        }

        for (int i : array) {
            System.out.println(i);
        }
    }
}
