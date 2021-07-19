package sopra.formation.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sopra.formation.config.ApplicationConfig;
import sopra.formation.model.Dispositif;
import sopra.formation.model.Filiere;
import sopra.formation.model.Formateur;
import sopra.formation.repository.IFiliereRepository;
import sopra.formation.repository.IPersonneRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class FiliereRepositorySpringTest {
	@Autowired
	private IFiliereRepository filiereRepo;
	
	@Autowired
	private IPersonneRepository personneRepo;
	
	@Test
	public void createAndFindById() {
		System.out.println("testCreate Début ###################");

		Filiere tpt = new Filiere("TPT");
		tpt.setIntitule("TPT best session");
		tpt.setDispositif(Dispositif.POEI);
		tpt.setDuree(3);

		tpt = filiereRepo.save(tpt);

		Filiere tptFind = filiereRepo.findById(tpt.getId());

		Assert.assertEquals("TPT", tptFind.getPromotion());
		Assert.assertEquals("TPT best session", tptFind.getIntitule());
		Assert.assertEquals(Dispositif.POEI, tptFind.getDispositif());
		Assert.assertEquals((Integer) 3, tptFind.getDuree());

		System.out.println("testCreate Fin ###################");
	}

	@Test
	public void modify() {
		System.out.println("testModify Début ###################");

		Filiere tpt = new Filiere("TPT");

		tpt = filiereRepo.save(tpt);

		Filiere tptFind = filiereRepo.findById(tpt.getId());

		tptFind.setPromotion("dreamTeam");;

		tptFind = filiereRepo.save(tptFind);

		tptFind = filiereRepo.findById(tptFind.getId());

		Assert.assertEquals("dreamTeam", tptFind.getPromotion());

		System.out.println("testModify Fin ###################");
	}

	@Test
	public void delete() {
		System.out.println("testDelete Début ###################");

		Filiere tpt = new Filiere("TPT");

		tpt = filiereRepo.save(tpt);

		Filiere tptFind = filiereRepo.findById(tpt.getId());

		filiereRepo.delete(tptFind);

		tptFind = filiereRepo.findById(tpt.getId());

		Assert.assertNull(tptFind);

//		if(tptFind != null) {
//			Assert.fail("La suppression de la matière a échouée");
//		}

		System.out.println("testDelete Fin ###################");
	}

	@Test
	public void findAll() {
		System.out.println("testFindAll Début ###################");

		int sizeStart = filiereRepo.findAll().size();

		Filiere tpt = new Filiere("TPT");

		tpt = filiereRepo.save(tpt);

		Filiere dreamTeam = new Filiere("dreamTeam");

		dreamTeam = filiereRepo.save(dreamTeam);

		int sizeEnd = filiereRepo.findAll().size();

		Assert.assertEquals(2, sizeEnd - sizeStart);

		System.out.println("testFindAll Fin ###################");
	}
	
	@Test
	public void findByIdWithReferent() {
		System.out.println("testfindByIdWithReferent Début ###################");
		
		Filiere tpt = new Filiere("TPT");
		Formateur ericSultan = new Formateur("eric.sultan@gmail.com");
		
		ericSultan.setNom("Sultan");
		ericSultan.setPrenom("Eric");
		ericSultan = (Formateur) personneRepo.save(ericSultan);
		
		tpt.setReferent(ericSultan);
		tpt = filiereRepo.save(tpt);
		
		Filiere tptFind = filiereRepo.findByIdWithReferent(tpt.getId());

		Assert.assertEquals(ericSultan.getId(), tptFind.getReferent().getId());
		
		System.out.println("testfindByIdWithReferent Début ###################");
	}
}
