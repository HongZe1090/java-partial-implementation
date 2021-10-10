import com.ni.sourceCode.example.AppConfig;
import com.ni.sourceCode.example.service.UserInterface;
import com.ni.sourceCode.example.service.UserService;
import com.ni.sourceCode.spring.ApplicationContext;

public class Test {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ApplicationContext(AppConfig.class);
//        UserService service = (UserService) applicationContext.getBean("userService"); 会报错，unnamed module of loader 'app'
        UserInterface service = (UserInterface) applicationContext.getBean("userService");
        service.test();
//        先执行代理逻辑，再执行业务逻辑
    }
}