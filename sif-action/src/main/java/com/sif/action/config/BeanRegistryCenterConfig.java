package com.sif.action.config;
import com.sif.common.entity.email.EmailModel;
import com.sif.common.util.MailSendUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zgq7 on 2019/6/6.
 * 注册一些bean进入ioc
 *
 */
@Configuration
public class BeanRegistryCenterConfig {


    /**
     * 邮箱工具类 bean 注册
     **/
    @Bean
    public MailSendUtils mailSendUtils() {
        return new MailSendUtils();
    }

}