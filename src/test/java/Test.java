import com.ni.sourceCode.example.AppConfig;
import com.ni.sourceCode.spring.ApplicationContext;

import java.lang.reflect.InvocationTargetException;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ApplicationContext applicationContext = new ApplicationContext(AppConfig.class);

        System.out.println(applicationContext.getBean("userService"));
    }
}
