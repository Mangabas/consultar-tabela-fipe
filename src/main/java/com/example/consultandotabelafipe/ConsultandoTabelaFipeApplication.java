package com.example.consultandotabelafipe;

import com.example.consultandotabelafipe.main.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsultandoTabelaFipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ConsultandoTabelaFipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal main = new Principal();
		main.menu();

	}
}
