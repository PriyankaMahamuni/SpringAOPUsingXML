package org.bridgelabz.SpringAOPUsingXML;

import org.bridgelabz.BusinessCode.Bank;

import org.bridgelabz.BusinessCode.LogIn;
import org.bridgelabz.service.LogAfterService;
import org.bridgelabz.service.LogAroundService;
import org.bridgelabz.service.LogBeforeService;
import org.bridgelabz.service.LogInExceptionService;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApplication {
	public static void main(String[] args) {
		/**
		 * purpose : for using MethodBeforeAdvice
		 */
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("logBeforeAdvice.xml");
		Bank bproxy = (Bank) context.getBean("beforeProxy");

		// get generated proxy object
		int amount = bproxy.deposite("mha909", 500);
		System.out.println(amount);
		/**
		 * purpose : for using MethodAftereAdvice
		 */
		Bank bankProxy = (Bank) context.getBean("afterProxy");
		int amt = bankProxy.withdraw("mha909", 500);
		System.out.println(amt);
		
		
		/**
		 * purpose : for using MethodAroundAdvice
		 */
		LogIn loginObject=(LogIn)context.getBean("aroundProxy");
		boolean status = loginObject.checking("admin", "admin");
		if (status) {
			System.out.println("Login success....");
		} else {
			System.out.println("Login failed");
		}
		
		
		/**
		 * purpose : for using throwAdvice
		 */
		LogIn login=(LogIn)context.getBean("throwProxy");
		boolean flag = login.checking("adm", "admin");
		if (flag) {
			System.out.println("Login success....");
		} else {
			System.out.println("Login failed");
		}

	}
}
