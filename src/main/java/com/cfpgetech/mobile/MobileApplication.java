package com.cfpgetech.mobile;

import com.cfpgetech.mobile.entities.Departement;
import com.cfpgetech.mobile.entities.Role;
import com.cfpgetech.mobile.entities.User;
import com.cfpgetech.mobile.repos.DepartementRepository;
import com.cfpgetech.mobile.repos.FormationRepository;
import com.cfpgetech.mobile.repos.ProgrammeRepository;
import com.cfpgetech.mobile.security.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MobileApplication implements CommandLineRunner{

	@Autowired
	private AccountService accountService;
	@Autowired
	private DepartementRepository departementRepository;
	@Autowired
	private FormationRepository formationRepository;
	@Autowired
	private ProgrammeRepository programmeRepository;

	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}


	public static void main(String[] args) {
		SpringApplication.run(MobileApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

//		accountService.saveRole(new Role("ADMIN","Administrateur"));
//		accountService.saveRole(new Role("CANDIDAT","Utilisateur"));

//		accountService.saveUser(new User("Fama18", "@Fama5", "sofama1997@gmail.com"));
//		accountService.addRoleToUser("Fama18","ADMIN");
//		accountService.saveUser(new User("Orbit555", "@orbitKagina12", "orbitturner@gmail.com"));
//		accountService.addRoleToUser("Orbit555","ADMIN");
//		accountService.saveUser(new User("Moustapha47", "Gittag158", "moustapha@gmail.com"));
//		accountService.addRoleToUser("Moustapha47","CANDIDAT");
//		accountService.saveUser(new User("Moussa18", "MoussaNar15", "moussa@gmail.com"));
//		accountService.addRoleToUser("Moussa18","CANDIDAT");
//		accountService.saveUser(new User("Adn158", "Grinch16", "adn@gmail.com"));
//		accountService.addRoleToUser("Adn158","CANDIDAT");

//		departementRepository.save(new Departement("Metier du numerique",null));
//		departementRepository.save(new Departement("Metier de l'industrie",null));
//		departementRepository.save(new Departement("Metier de la gestion",null));
//		departementRepository.save(new Departement("Metier du tertiaire",null));
	}
}
