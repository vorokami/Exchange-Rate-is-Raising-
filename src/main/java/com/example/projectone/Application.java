package com.example.projectone;

import com.example.projectone.exchangeRates.service.ExchangeRatesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Slf4j
public class Application {

	private static ConfigurableApplicationContext ctx;
	private static int exitCode;

	public static void main(String[] args) {
		ctx = SpringApplication.run(Application.class, args);

		exitCode = SpringApplication.exit(ctx, new ExitCodeGenerator() {
			@Override
			public int getExitCode() {
				// no errors
				return 0;
			}
		});
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate, @Autowired ExchangeRatesService exchangeRatesService) throws Exception {
		return args -> {
			log.info("exchange rate is raised? - {}", exchangeRatesService.exchangeRateIsRaised(restTemplate));
			System.exit(exitCode);
		};
	}

}
