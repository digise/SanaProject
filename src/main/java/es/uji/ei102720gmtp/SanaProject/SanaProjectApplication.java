package es.uji.ei102720gmtp.SanaProject;


import es.uji.ei102720gmtp.SanaProject.dao.CiutadaDao;
import es.uji.ei102720gmtp.SanaProject.model.Ciutada;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


import java.time.LocalDate;
import java.util.List;
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
