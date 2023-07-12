package com.cac.minibank;

import com.cac.minibank.model.Currency;
import com.cac.minibank.repository.CurrencyRepository;
import org.springframework.boot.CommandLineRunner;
import  org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
public class MiniBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniBankApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
		CurrencyRepository currencyRepository
	){
		return args -> {
			currencyRepository.save(new Currency(1, "Euro", "EUR"));
			currencyRepository.save(new Currency(2, "Dolar", "USD"));
		};
	}


}
