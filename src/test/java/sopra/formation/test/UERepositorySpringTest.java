package sopra.formation.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sopra.formation.config.ApplicationConfig;
import sopra.formation.model.UE;
import sopra.formation.model.UE;
import sopra.formation.repository.IUERepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)

public class UERepositorySpringTest {
	@Autowired
	private IUERepository UERepo;

	@Test
	public void createAndFindById() {
		System.out.println("testCreate Début ###################");

		UE ue = new UE(12, 23, 4);

		ue= UERepo.save(ue);

		UE UEFind = UERepo.findById(ue.getId());

		Assert.assertEquals((Integer) 12, UEFind.getCode());

		Assert.assertEquals((Integer) 23, UEFind.getDuree());
		Assert.assertEquals((int) 4, UEFind.getOrdre());

		System.out.println("testCreate Fin ###################");
	}

	@Test
	public void modify() {
		System.out.println("testModify Début ###################");

		UE ue = new UE(12, 23, 4);

		ue= UERepo.save(ue);

		UE UEFind = UERepo.findById(ue.getId());

		UEFind.setCode(13);
		UEFind.setDuree(7);
		UEFind.setOrdre(49);

		UEFind = UERepo.save(UEFind);

		UEFind = UERepo.findById(UEFind.getId());

		Assert.assertEquals((Integer)13, UEFind.getCode());

		Assert.assertEquals((Integer) 7, UEFind.getDuree());
		Assert.assertEquals((int) 49, UEFind.getOrdre());

		System.out.println("testModify Fin ###################");
	}

	@Test
	public void delete() {
		System.out.println("testDelete Début ###################");

		UE html = new UE(12, 23, 4);

		html = UERepo.save(html);

		UE UEFind = UERepo.findById(html.getId());

		UERepo.delete(UEFind);

		UEFind = UERepo.findById(html.getId());

		Assert.assertNull(UEFind);

//		if(UEFind != null) {
//			Assert.fail("La suppression de la matière a échouée");
//		}

		System.out.println("testDelete Fin ###################");
	}

	@Test
	public void findAll() {
		System.out.println("testFindAll Début ###################");

		int sizeStart = UERepo.findAll().size();

		UE ue = new UE(12, 23, 4);

		ue = UERepo.save(ue);

		UE js = new UE(14, 25, 6);

		js = UERepo.save(js);

		int sizeEnd = UERepo.findAll().size();

		Assert.assertEquals(2, sizeEnd - sizeStart);

		System.out.println("testFindAll Fin ###################");
	}
}
