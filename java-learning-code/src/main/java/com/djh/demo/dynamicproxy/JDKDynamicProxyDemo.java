package com.djh.demo.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 使用动态代理实现IHello接口的sayHello方法并调用
 */
public class JDKDynamicProxyDemo {


    public static void main(String[] args) {
        JDKDynamicProxyDemo demo = new JDKDynamicProxyDemo();

        demo.doProxy();
        demo.proxyAbstractClass();//代理抽象类会报错
    }

    /**
     * 使用jdk的动态代理生成实现类
     */
    public void doProxy(){
        IHello helloImpl = (IHello)Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class[]{IHello.class},
                new InvocationHandlerImpl());
        System.out.println(helloImpl.sayHello());

    }


    /**
     * 不能代理抽象类
     */
    public void proxyAbstractClass(){
        CglibDynamicProxyDemo.AbstractDog abstractDog = (CglibDynamicProxyDemo.AbstractDog)Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class[]{CglibDynamicProxyDemo.AbstractDog.class},//不能代理抽象类 只能代理接口
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return method.invoke(proxy, args);
                    }
                }
        );
        abstractDog.yep();

    }

    public static class InvocationHandlerImpl implements InvocationHandler{

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return "Hello JDK proxy";
        }
    }

}
