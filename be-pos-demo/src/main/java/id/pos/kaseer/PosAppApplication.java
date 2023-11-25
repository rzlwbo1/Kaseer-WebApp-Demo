package id.pos.kaseer;


import id.pos.kaseer.repository.CategoryRepo;
import id.pos.kaseer.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PosAppApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ProductRepo productRepo;

	public static void main(String[] args) {
		SpringApplication.run(PosAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


	}
}
