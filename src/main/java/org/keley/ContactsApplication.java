package org.keley;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.keley.dao.ContactRepository;
import org.keley.entites.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContactsApplication implements CommandLineRunner {

	@Autowired
	private ContactRepository cr;
	
	public static void main(String[] args) {
		SpringApplication.run(ContactsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		DateFormat df= new SimpleDateFormat("dd/MM/yyyy");
		
		cr.save(new Contact("daha", "hicham", df.parse("20/07/1983"), "hicham@gmail.com", 672532669, "hicham.png"));
		cr.save(new Contact("bilbal", "hajar", df.parse("02/07/2014"), "hajar@gmail.com", 672531169, "hajar.png"));
		cr.save(new Contact("jedi", "wissam", df.parse("10/07/1993"), "wissam@gmail.com", 672533369, "wissam.png"));
		
		cr.findAll().forEach(c->{
			System.out.print(c.getName());
		});

		
		
	}

}
