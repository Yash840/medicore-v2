package org.cross.medicore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableAspectJAutoProxy
public class MedicoreApplication {
    // TODO: Add validation in all DTOs
	public static void main(String[] args) { SpringApplication.run(MedicoreApplication.class, args); }
}
