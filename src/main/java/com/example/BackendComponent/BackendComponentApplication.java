package com.example.BackendComponent;

import com.example.BackendComponent.entity.*;
import com.example.BackendComponent.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.ParseException;
import java.util.stream.Stream;

@SpringBootApplication
public class BackendComponentApplication{
	public static void main(String[] args) {
		SpringApplication.run(BackendComponentApplication.class, args);
	}
}
