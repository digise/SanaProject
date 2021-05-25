package es.uji.ei102720gmtp.SanaProject;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


import java.time.LocalDate;
import java.util.logging.Logger;

@SpringBootApplication
public class SanaProjectApplication implements CommandLineRunner {

	private static final Logger log = Logger.getLogger(SanaProjectApplication.class.getName());

	public static void main(String[] args) {
		// Auto-configura l'aplicació
		new SpringApplicationBuilder(SanaProjectApplication .class).run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Ací va el meu codi");
	}


}
