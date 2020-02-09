package top.flander.xmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "top.flander.xmall.manage.mapper")
public class XmallManageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(XmallManageServiceApplication.class, args);
    }

}
