package com.example.wagonmanager;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@Configuration
public class LiquibaseDiagnostics {
    @Bean
    public ApplicationRunner liqDiagnostics(ApplicationContext ctx, Environment env) {
        return args -> {
            System.out.println("=== Liquibase diagnostics ===");
            System.out.println("spring.config.location (system property) = " + System.getProperty("spring.config.location"));
            System.out.println("spring.profiles.active (system property) = " + System.getProperty("spring.profiles.active"));
            System.out.println("ENV spring.liquibase.enabled = " + env.getProperty("spring.liquibase.enabled"));
            System.out.println("ENV spring.liquibase.change-log = " + env.getProperty("spring.liquibase.change-log"));
            System.out.println("ENV spring.datasource.url = " + env.getProperty("spring.datasource.url"));
            System.out.println("ENV spring.datasource.username = " + env.getProperty("spring.datasource.username"));
            System.out.println("Listing bean names that contain 'liquibase' (case-insensitive):");
            String[] liqBeans = Arrays.stream(ctx.getBeanDefinitionNames())
                    .filter(n -> n.toLowerCase().contains("liquibase"))
                    .toArray(String[]::new);
            System.out.println("liquibase beans: " + Arrays.toString(liqBeans));

            // Попытка найти bean по типу SpringLiquibase (если класс доступен)
            try {
                Class<?> springLiquibaseClass = Class.forName("liquibase.integration.spring.SpringLiquibase");
                String[] byType = ctx.getBeanNamesForType(springLiquibaseClass, true, false);
                System.out.println("Beans of type liquibase.integration.spring.SpringLiquibase: " + Arrays.toString(byType));
            } catch (ClassNotFoundException e) {
                System.out.println("Class liquibase.integration.spring.SpringLiquibase NOT found on classpath: " + e.getMessage());
            }

            // Проверяем, есть ли LiquibaseAutoConfiguration в контексте (не гарантированно — но можно показать)
            try {
                Class<?> autoConfClass = Class.forName("org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration");
                String[] names = ctx.getBeanNamesForType(autoConfClass, true, false);
                System.out.println("LiquibaseAutoConfiguration beans: " + Arrays.toString(names));
            } catch (ClassNotFoundException e) {
                System.out.println("Class org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration NOT found: " + e.getMessage());
            }

            System.out.println("=== end diagnostics ===");
        };
    }
}
