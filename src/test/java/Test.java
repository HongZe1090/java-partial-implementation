import com.ni.sourceCode.example.AppConfig;
import com.ni.sourceCode.example.service.UserService;
import com.ni.sourceCode.spring.ApplicationContext;

import java.lang.reflect.InvocationTargetException;

public class Test {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ApplicationContext(AppConfig.class);
        UserService service = (UserService)applicationContext.getBean("userService");
        System.out.println(service.test());

    }
}
