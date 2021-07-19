package sopra.formation.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sopra.formation.config.ApplicationConfig;
import sopra.formation.model.Filiere;
import sopra.formation.repository.IFiliereRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class FiliereRepositorySpringTest {
	@Autowired
	private IFiliereRepository filiereRepo;
	
	@Test
	public void createAndFindById() {
		System.out.println("testCreate Début ###################");

		Filiere tpt = new Filiere("TPT");

		tpt = filiereRepo.save(tpt);

		Filiere tptFind = filiereRepo.findById(tpt.getId());

		Assert.assertEquals("TPT", tptFind.getPromotion());

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

		Assert.assertEquals((Integer) 3, tptFind.getDuree());

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
}
