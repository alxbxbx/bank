package com.poslovna.informatika;

import com.poslovna.informatika.entities.User;
import com.poslovna.informatika.frames.LoginFrame;
import com.poslovna.informatika.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.poslovna.informatika.frames.MainFrame;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class BankApplication {

    public static User loggedUser = null;
    public static boolean isLoggedIn = false;

    public static void main(String[] args) {
        @SuppressWarnings("unused")
        ConfigurableApplicationContext context = new SpringApplicationBuilder(BankApplication.class).headless(false).web(false).run(args);
        @SuppressWarnings("unused")
        LoginFrame loginFrame = LoginFrame.getInstance();
        loginFrame.setVisible(true);
    }

}
