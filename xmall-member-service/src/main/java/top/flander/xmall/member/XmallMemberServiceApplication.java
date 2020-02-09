package top.flander.xmall.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "top.flander.xmall.member.mapper")
public class XmallMemberServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(XmallMemberServiceApplication.class, args);
    }

}
