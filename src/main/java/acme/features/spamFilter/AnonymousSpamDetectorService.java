package acme.features.spamFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spamWord.SpamWord;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousSpamDetectorService implements AbstractListService<Anonymous,SpamWord> {

	@Autowired
	AdministratorSpamWordRepository repository;
	@Override
	public boolean authorise(final Request<SpamWord> request) {
		// TODO Auto-generated method stub
		assert request != null;
		
		return true;
	}

	@Override
	public void unbind(final Request<SpamWord> request, final SpamWord entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "word");
		
	}

	@Override
	public Collection<SpamWord> findMany(final Request<SpamWord> request) {
		// TODO Auto-generated method stub
		assert request != null;
		
		Collection<SpamWord> result;
		
		result = this.repository.findMany();
		return result;
	}
	
	public boolean detectSpam(final String text) {
		 boolean esSpam;
		 final List<String> spamWordsList= this.repository.findMany().stream().map(x->x.getWord()).collect(Collectors.toList());
		 final List<String> spamWordsListWithoutSpaces = new ArrayList<>();
		 
		 for(int i=0; i<spamWordsList.size(); i++) {
			 final String x= spamWordsList.get(i).replace(" ", "");
			 spamWordsListWithoutSpaces.add(x);
		 }
		 
		 final List<String> textList = new ArrayList<>(Arrays.asList(text.split(" ")));
		 Double threshold=0.;
		 int i =0;
		 while(i<textList.size()) {
			 final String low = textList.get(i).toLowerCase();
			 int z=0;
			 while(z<spamWordsListWithoutSpaces.size()) {
				 final String spLow= spamWordsListWithoutSpaces.get(z).toLowerCase();
				 if(low.equals(spLow)) {
					 
					 threshold += ((1./textList.size())*100);
					 
					 break;
					 
				 }else {
					 if((!(i==textList.size()-1)) && (low+textList.get(i+1)).equals(spLow)) {
						 threshold += ((1./textList.size())*100);
						 break;
					 }else {
						 	threshold += 0.;
					 }
				 }
				 z++;
				 
			 }
			 i++;
			 
		 }
		 
		 if(threshold<=10.) {
			 esSpam=false; 
		 }else {
			 
			 esSpam=true; 
		 }
		 
		 return esSpam;
		
	
	}


}
