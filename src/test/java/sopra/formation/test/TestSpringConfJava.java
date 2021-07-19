package sopra.formation.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import sopra.formation.config.ApplicationConfig;
import sopra.formation.model.Formateur;
import sopra.formation.model.Matiere;
import sopra.formation.repository.IMatiereRepository;
import sopra.formation.repository.IPersonneRepository;

public class TestSpringConfJava {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		IPersonneRepository personneRepo=context.getBean(IPersonneRepository.class);
		IMatiereRepository matiereRepo = context.getBean(IMatiereRepository.class);

		Matiere html = new Matiere("HTML", 2);

		html = matiereRepo.save(html);

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
		System.out.println(matiere.getId());
		
		
		context.close();
	}

}
