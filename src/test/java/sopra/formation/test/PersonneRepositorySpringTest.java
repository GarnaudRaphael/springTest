package sopra.formation.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sopra.formation.config.ApplicationConfig;
import sopra.formation.model.Formateur;
import sopra.formation.model.Personne;
import sopra.formation.model.Stagiaire;
import sopra.formation.repository.IPersonneRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class PersonneRepositorySpringTest {

	@Autowired
	private IPersonneRepository personneRepo;

	@Test
	public void createAndFindById() {
		System.out.println("testCreate Début ###################");

		Personne aurelienDispot = new Stagiaire("aurelien.dispot@gmail.com");

		aurelienDispot = personneRepo.save(aurelienDispot);

		Personne aurelienDispotFind = personneRepo.findById(aurelienDispot.getId());

		Assert.assertEquals("aurelien.dispot@gmail.com", aurelienDispotFind.getEmail());

		System.out.println("testCreate Fin ###################");
	}

	@Test
	public void modify() {
		System.out.println("testModify Début ###################");

		Personne aurelienDispot = new Stagiaire("aurelien.dispot@gmail.com");

		aurelienDispot = personneRepo.save(aurelienDispot);

		Personne aurelienDispotFind = personneRepo.findById(aurelienDispot.getId());

		aurelienDispotFind.setNom("aurelien.dispot@hotmail.fr");

		aurelienDispotFind = personneRepo.save(aurelienDispotFind);

		aurelienDispotFind = personneRepo.findById(aurelienDispotFind.getId());

		Assert.assertEquals("aurelien.dispot@hotmail.fr", aurelienDispotFind.getNom());

		System.out.println("testModify Fin ###################");
	}

	@Test
	public void delete() {
		System.out.println("testDelete Début ###################");

		Personne aurelienDispot = new Stagiaire("aurelien.dispot@gmail.com");

		aurelienDispot = personneRepo.save(aurelienDispot);

		Personne aurelienDispotFind = personneRepo.findById(aurelienDispot.getId());

		personneRepo.delete(aurelienDispotFind);

		aurelienDispotFind = personneRepo.findById(aurelienDispot.getId());

		Assert.assertNull(aurelienDispotFind);

//		if(aurelienDispotFind != null) {
//			Assert.fail("La suppression de la matière a échouée");
//		}

		System.out.println("testDelete Fin ###################");
	}

	@Test
	public void findAll() {
		System.out.println("testFindAll Début ###################");

		int sizeStart = personneRepo.findAll().size();

		Personne aurelienDispot = new Stagiaire("aurelien.dispot@gmail.com");

		aurelienDispot = personneRepo.save(aurelienDispot);

		Personne ericSultan = new Formateur("eric.sultant@gmail.com");

		ericSultan = personneRepo.save(ericSultan);

		int sizeEnd = personneRepo.findAll().size();

		Assert.assertEquals(2, sizeEnd - sizeStart);

		System.out.println("testFindAll Fin ###################");
	}

}
