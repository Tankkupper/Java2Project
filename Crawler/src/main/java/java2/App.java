package java2;



import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("java2.mapper")
@EnableKnife4j
public class App {

    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);

    }
}
