package sopra.formation.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sopra.formation.config.ApplicationConfig;
import sopra.formation.model.Evaluation;
import sopra.formation.repository.IEvaluationRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class EvaluationRepositorySpringTest {

	@Autowired
	private IEvaluationRepository evaluationRepo;

	@Test
	public void createAndFindById() {
		System.out.println("testCreate Début ###################");

		Evaluation eval1 = new Evaluation(32, 2, "hello");

		eval1 = evaluationRepo.save(eval1);

		Evaluation eval1find = evaluationRepo.findById(eval1.getId());

		Assert.assertEquals((Integer) 32, eval1find.getComportemental());

		Assert.assertEquals((Integer) 2, eval1find.getTechnique());
		
		Assert.assertEquals("hello", eval1find.getCommentaires());

		System.out.println("testCreate Fin ###################");
	}

	@Test
	public void modify() {
		System.out.println("testModify Début ###################");

		Evaluation eval1 = new Evaluation(32, 2, "hello");

		eval1 = evaluationRepo.save(eval1);

		Evaluation eval1find = evaluationRepo.findById(eval1.getId());

		eval1find.setComportemental(72);
		eval1find.setTechnique(12);
		eval1find.setCommentaires("hello truc");

		eval1find = evaluationRepo.save(eval1find);

		eval1find = evaluationRepo.findById(eval1find.getId());

		Assert.assertEquals((Integer) 72, eval1find.getComportemental());

		Assert.assertEquals((Integer) 12, eval1find.getTechnique());
		
		Assert.assertEquals("hello truc", eval1find.getCommentaires());

		System.out.println("testModify Fin ###################");
	}

	@Test
	public void delete() {
		System.out.println("testDelete Début ###################");

		Evaluation eval1 = new Evaluation(32, 2, "hello");

		eval1 = evaluationRepo.save(eval1);

		Evaluation eval1find = evaluationRepo.findById(eval1.getId());

		evaluationRepo.delete(eval1find);

		eval1find = evaluationRepo.findById(eval1.getId());

		Assert.assertNull(eval1find);

//		if(htmlFind != null) {
//			Assert.fail("La suppression de la matière a échouée");
//		}

		System.out.println("testDelete Fin ###################");
	}

	@Test
	public void findAll() {
		System.out.println("testFindAll Début ###################");

		int sizeStart = evaluationRepo.findAll().size();

		Evaluation eval1 = new Evaluation(32, 2, "hello");

		eval1 = evaluationRepo.save(eval1);

		Evaluation eval2 = new Evaluation(100, 10, "RageQuit");

		eval2 = evaluationRepo.save(eval2);

		int sizeEnd = evaluationRepo.findAll().size();

		Assert.assertEquals(2, sizeEnd - sizeStart);

		System.out.println("testFindAll Fin ###################");
	}

}
