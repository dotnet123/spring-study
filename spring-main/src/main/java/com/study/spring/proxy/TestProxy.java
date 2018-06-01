package com.study.spring.proxy;

import com.study.spring.proxy.cglib.CGLibProxy;
import com.study.spring.proxy.jdk.JDKProxy;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * JDK代理是不需要以来第三方的库，只要要JDK环境就可以进行代理，它有几个要求
 * 1、实现InvocationHandler
 * 2、使用Proxy.newProxyInstance产生代理对象
 * 3、被代理的对象必须要实现接口
 *
 * CGLib 必须依赖于CGLib的类库，但是它需要类来实现任何接口代理的是指定的类生成一个子类，覆盖其中的方法，是一种继承
 * 但是针对接口编程的环境下推荐使用JDK的代理
 * 在Hibernate中的拦截器其实现考虑到不需要其他接口的条件Hibernate中的相关代理采用的是CGLib来执行。
 */
public class TestProxy {

    public static void main(String[] args) {

        System.out.println("-----------CGLibProxy-------------");
        UserManager userManager = (UserManager) new CGLibProxy().createProxyObject(new UserManagerImpl());
        userManager.addUser("tom", "root");

        System.out.println("-----------JDKProxy-------------");
        JDKProxy jdkProxy = new JDKProxy();
        UserManager userManagerJDK = (UserManager) jdkProxy.newProxy(new UserManagerImpl());
        userManagerJDK.addUser("tom", "root");

        String s = StringUtils.applyRelativePath("D:/下载/test.xlsx", "new.txt");
        System.out.println(s);

        LocalDate ld = LocalDate.parse("2018-09-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(ld);
        LocalDateTime of = LocalDateTime.of(ld, LocalTime.of(8, 0));
        System.out.println(of.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        LocalDateTime of2 = LocalDateTime.of(ld, LocalTime.of(9, 0));
        System.out.println(of2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
