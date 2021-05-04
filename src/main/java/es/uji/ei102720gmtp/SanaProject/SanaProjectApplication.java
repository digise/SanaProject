package es.uji.ei102720gmtp.SanaProject;

import es.uji.ei102720gmtp.SanaProject.model.enums.Provincia;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
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
