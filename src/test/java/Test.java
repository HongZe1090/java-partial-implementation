import com.ni.sourceCode.example.AppConfig;
import com.ni.sourceCode.spring.ApplicationContext;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        ApplicationContext applicationContext = new ApplicationContext(AppConfig.class);
    }
}
