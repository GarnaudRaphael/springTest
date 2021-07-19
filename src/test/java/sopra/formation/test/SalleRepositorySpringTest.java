package sopra.formation.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sopra.formation.config.ApplicationConfig;
import sopra.formation.model.Evaluation;
import sopra.formation.model.Salle;
import sopra.formation.repository.ISalleRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class SalleRepositorySpringTest {

	@Autowired
	private ISalleRepository salleRepo;

	@Test
	public void createAndFindById() {
		System.out.println("testCreate Début ###################");

		Salle salle1 = new Salle("Ruche", 42, true);

		salle1 = salleRepo.save(salle1);

		Salle salle1find = salleRepo.findById(salle1.getId());

		Assert.assertEquals("Ruche", salle1find.getNom());

		Assert.assertEquals((Integer) 42, salle1find.getCapacite());
		
		Assert.assertEquals(true, salle1find.getVideoProjecteur());

		System.out.println("testCreate Fin ###################");
	}

	@Test
	public void modify() {
		System.out.println("testModify Début ###################");

		Salle salle1 = new Salle("Ruche", 42, true);

		salle1 = salleRepo.save(salle1);

		Salle salle1find = salleRepo.findById(salle1.getId());

		salle1find.setNom("Ruche");
		salle1find.setTechnique(2);
		salle1find.setCommentaires("hello");

		salle1find = salleRepo.save(salle1find);

		salle1find = salleRepo.findById(salle1find.getId());

		Assert.assertEquals((Integer) 32, salle1find.getComportemental());

		Assert.assertEquals((Integer) 2, salle1find.getTechnique());
		
		Assert.assertEquals("hello", salle1find.getCommentaires());

		System.out.println("testModify Fin ###################");
	}

	@Test
	public void delete() {
		System.out.println("testDelete Début ###################");

		Evaluation eval1 = new Evaluation(32, 2, "hello");

		eval1 = salleRepo.save(eval1);

		Evaluation eval1find = salleRepo.findById(eval1.getId());

		salleRepo.delete(eval1find);

		eval1find = salleRepo.findById(eval1.getId());

		Assert.assertNull(eval1find);

//		if(htmlFind != null) {
//			Assert.fail("La suppression de la matière a échouée");
//		}

		System.out.println("testDelete Fin ###################");
	}

	@Test
	public void findAll() {
		System.out.println("testFindAll Début ###################");

		int sizeStart = salleRepo.findAll().size();

		Evaluation eval1 = new Evaluation(32, 2, "hello");

		eval1 = salleRepo.save(eval1);

		Evaluation eval2 = new Evaluation(100, 10, "RageQuit");

		eval2 = salleRepo.save(eval2);

		int sizeEnd = salleRepo.findAll().size();

		Assert.assertEquals(2, sizeEnd - sizeStart);

		System.out.println("testFindAll Fin ###################");
	}

}
