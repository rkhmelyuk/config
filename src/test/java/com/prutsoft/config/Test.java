package com.prutsoft.config;

import com.prutsoft.config.service.ConfigurationService;
import com.prutsoft.config.service.ConfigurationServiceImpl;

/**
 * TODO - Write JavaDoc comment
 *
 * @author Ruslan Khmelyuk
 * @since 2010-01-06
 */
public class Test {

    public static void main(String... args) throws Exception {
        String configuration = "string:<configuration name='test' version='1.0.23'>  " +
                "<metadata>" +
                "<property name='x' value='y'/>" +
                "<property name='x2' value='y2'/>" +
                "</metadata>" +
                "<expression name='t' value='\"wdt\"'/>" +
                "<property name='t1'><value type='integer'>2292</value></property>" +
                "<set name='x1'><set name='x1'><property name='xxx' value='23' type='integer'/></set><property name='x2' value='xx'/></set>" +
                "<switch name='ttk'>" +
                "<on condition='x == 1'><value>x1</value></on>" +
                "<on condition='x == 2'><value>x2</value></on>" +
                "<else><value>xxx</value></else>" +
                "</switch>" +
                "" +
                "<pojo name='student' class='com.prutsoft.config.Student'>" +
                "<property name='firstName' value='Ruslan'/>" +
                "<property name='lastName' value='Khmelyuk'/>" +
                "</pojo>" +
                " </configuration>";


        ConfigurationService service = new ConfigurationServiceImpl();
        service.load(configuration);

        Configuration config = service.getConfiguration("test", "1.0.23");

        System.out.println(config.getName());
        System.out.println(config.getVersion());

        System.out.println(config.getMetadata().getProperty("x").getValue());
        System.out.println(config.getMetadata().getPropertyValue("x2"));
        System.out.println(config.getString("t"));
        System.out.println(config.getInteger("t1"));
        System.out.println(config.getString("x1", "x1", "xxx"));
        System.out.println(config.getString("ttk", new ContextMap("x", 1)));
        System.out.println(config.getString("ttk", new ContextMap("x", 2)));
        System.out.println(config.getString("ttk", new ContextMap("x", 2)));

        Thread.sleep(10000);

        Student s= (Student) config.getValue("student");

        System.out.println(s.getFirstName());
        System.out.println(s.getLastName());

        service.destroy();


    }
}
