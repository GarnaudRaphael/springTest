package sopra.formation.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sopra.formation.config.ApplicationConfig;
import sopra.formation.model.Formateur;
import sopra.formation.model.Matiere;
import sopra.formation.model.Personne;
import sopra.formation.model.Stagiaire;
import sopra.formation.repository.IMatiereRepository;
import sopra.formation.repository.IPersonneRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class PersonneRepositorySpringTest {

	@Autowired
	private IPersonneRepository personneRepo;
	@Autowired
	private IMatiereRepository matiereRepo;

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
	@Test
	public void findAllStagiaire() {
		int sizeStart=personneRepo.findAllStagiaire().size();
		System.out.println("testFindAllStagiaire Début ###################");
		Personne aurelienDispot = new Stagiaire("aurelien.dispot@gmail.com");
		aurelienDispot = personneRepo.save(aurelienDispot);
		Personne raphaelGarnaud = new Stagiaire("raphael.garnaud@gmail.com");
		raphaelGarnaud = personneRepo.save(raphaelGarnaud);
		Personne ericSultan = new Formateur("eric.sultant@gmail.com");
		ericSultan = personneRepo.save(ericSultan);
		int sizeEnd=personneRepo.findAllStagiaire().size();
		Assert.assertEquals(2, sizeEnd - sizeStart);
		System.out.println("testFindAllStagiaire Fin ###################");
	}
	@Test
	public void findAllFormateur() {
		int sizeStart=personneRepo.findAllFormateur().size();
		System.out.println("testFindAllFormateur Début ###################");
		Personne aurelienDispot = new Stagiaire("aurelien.dispot@gmail.com");
		aurelienDispot = personneRepo.save(aurelienDispot);
		Personne jordanAbid = new Formateur("jordan.adbid@gmail.com");
		jordanAbid = personneRepo.save(jordanAbid);
		Personne ericSultan = new Formateur("eric.sultant@gmail.com");
		ericSultan = personneRepo.save(ericSultan);
		int sizeEnd=personneRepo.findAllFormateur().size();
		Assert.assertEquals(2, sizeEnd - sizeStart);
		System.out.println("testFindAllFormateur Fin ###################");
	}
	@Test
	public void findFormateurByEmail() {
		int sizeStart=personneRepo.findAllFormateur().size();
		System.out.println("testFindAllFormateur Début ###################");
		Personne ericSultan = new Formateur("eric.sultant@gmail.com");
		ericSultan = personneRepo.save(ericSultan);
		Formateur formateur=personneRepo.findFormateurByEmail(ericSultan.getEmail());
		Assert.assertEquals("eric.sultant@gmail.com", formateur.getEmail());
		System.out.println("testFindAllFormateur Fin ###################");
	}
	public void findStagiaireByEmail() {
		int sizeStart=personneRepo.findAllFormateur().size();
		System.out.println("testFindAllFormateur Début ###################");
		Personne aurelienDispot = new Stagiaire("aurelien.dispot@gmail.com");
		aurelienDispot = personneRepo.save(aurelienDispot);
		Formateur stagiaire=personneRepo.findFormateurByEmail(aurelienDispot.getEmail());
		Assert.assertEquals("aurelien.dispot@gmail.com", stagiaire.getEmail());
		System.out.println("testFindAllFormateur Fin ###################");
	}
	@Test
	public void findAllFormateurByMatiere() {
		int sizeStart=personneRepo.findAllFormateur().size();
		System.out.println("testFindAllFormateur Début ###################");
		Formateur ericSultan = new Formateur();
		Formateur jordanAbid = new Formateur();
		Matiere matiere=new Matiere("PHP",3);
		matiere=matiereRepo.save(matiere);
		ericSultan.addCompetence(matiere);
		jordanAbid.addCompetence(matiere);
		ericSultan = (Formateur) personneRepo.save(ericSultan);
		jordanAbid = (Formateur) personneRepo.save(jordanAbid);
		int sizeEnd=personneRepo.findAllFormateurByMatiere(matiere.getId()).size();
		Assert.assertEquals(2, sizeEnd - sizeStart);
		System.out.println("testFindAllFormateur Fin ###################");
	}
}
