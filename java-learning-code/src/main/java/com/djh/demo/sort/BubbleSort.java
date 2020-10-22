package com.djh.demo.sort;

/**
 * 冒泡排序
 */
public class BubbleSort extends Base {

    public void sort(){
        //相邻两两相比，左边>右边则交换位置

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length-i-1; j++) {
                if (array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1]=temp;
                }

            }
        }
        for (int i : array) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        new BubbleSort().sort();
    }

}
