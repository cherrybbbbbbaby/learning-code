package com.djh.demo.dynamicproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibDynamicProxyDemo {

    public static void main(String[] args) {
        CglibDynamicProxyDemo demo = new CglibDynamicProxyDemo();
//        demo.proxyInterface();
//        demo.proxyClass();
        demo.proxyAbstractClass();
    }

    /**
     * 使用cglib生成实现类
     */
    public void proxyInterface(){
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[]{IHello.class});
        enhancer.setCallback(new MethodInterceptor() {
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                return "hello cglib";
            }
        });
        IHello biz = (IHello)enhancer.create();
        String hello = biz.sayHello();
        System.out.println(hello);

    }


    public void proxyClass(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Dog.class);
        enhancer.setCallback(new MethodInterceptor() {
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("前置");
                Object result = proxy.invokeSuper(obj,args);
                method.invoke(obj,args);
//                proxy.invoke(obj, args);
                System.out.println("后置");
                return result;
            }
        });
        Dog dog = (Dog)enhancer.create();
        dog.yep();
    }


    public void proxyAbstractClass(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(AbstractDog.class);
        enhancer.setCallback(new MethodInterceptor() {
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                return proxy.invokeSuper(obj, args);
            }
        });
        AbstractDog abstractDog = (AbstractDog)enhancer.create();
        abstractDog.yep();
    }

    public static class Dog{
        public void yep(){
            System.out.println("汪汪");
        }

    }

    public static abstract class AbstractDog{
        public void yep(){
            System.out.println("抽象的叫");
        }

    }

}
