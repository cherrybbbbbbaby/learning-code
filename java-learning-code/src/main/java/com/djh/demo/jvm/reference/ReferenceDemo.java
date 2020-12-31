package com.djh.demo.jvm.reference;

import java.lang.ref.WeakReference;

public class ReferenceDemo {


    public static void main(String[] args) {
        weakReferenceTest();
    }

    /**
     * 弱引用在GC时，无论内存是否足够都会被回收
     */
    public static void weakReferenceTest(){
        Object obj = new Object();
        WeakReference<Object> weakReference = new WeakReference<Object>(obj);
        System.out.println("before gc ---> weakReference get:"+weakReference.get());
        System.gc();
        System.out.println("after gc ---> weakReference get:"+weakReference.get());//存在第16行的强引用 所以GC此弱引用不会回收

        WeakReference<Object> weakReferenceObj1 = new WeakReference<Object>(new Object());
        System.gc();
        System.out.println(weakReferenceObj1.get() == null);//true 不存在强引用 GC后回回收


        //放入常量池中的弱引用不会被回收
        WeakReference<String> weakReferenceStr1 = new WeakReference<String>(new String("董嘉泓"));
        System.out.println("before gc ---> weakReferenceStr1 get:"+weakReferenceStr1.get());//董嘉泓
        System.gc();
        System.out.println("after gc ---> weakReferenceStr1 get:"+weakReferenceStr1.get());//null



        WeakReference<String> weakReferenceStr2 = new WeakReference<String>(new String("董嘉泓").intern());
        System.out.println("before gc ---> weakReferenceStr2 get:"+weakReferenceStr2.get());//董嘉泓
        System.gc();
        System.out.println("after gc ---> weakReferenceStr2 get:"+weakReferenceStr2.get());//董嘉泓


    }




}
