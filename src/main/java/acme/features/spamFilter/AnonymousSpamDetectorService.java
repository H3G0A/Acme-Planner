package acme.features.spamFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.SpamWord;
import acme.entities.spam.Word;
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

		assert request != null;
		
		return true;
	}

	@Override
	public void unbind(final Request<SpamWord> request, final SpamWord entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "word");
		
	}

	@Override
	public Collection<SpamWord> findMany(final Request<SpamWord> request) {

		assert request != null;
        
        return Collections.emptySet();
	}
	public Double simpleThreshold(final String sentence, final List<String> spamWordList) {
		Double threshold = 0.;
		final List<String> textList = new ArrayList<>(Arrays.asList(sentence.split(" ")));
		 int i =0;
		 while(i<textList.size()) {
			 final String low = textList.get(i).toLowerCase();
			 int z=0;
			 while(z<spamWordList.size()) {
				 final String spLow= spamWordList.get(z).toLowerCase();
				 if(low.equals(spLow) || low.contains(spLow)) {
					 threshold += ((1./textList.size())*100);					 
					 break;					 
				 }else {					 
					 threshold += 0.;					 
				 }
				 z++;
			}
			 i++;
			 
		 }
		 return threshold;
	}
	
	public Double sentenceThreshold(final String word, final List<String> spamWordList) {
		Double threshold = 0.;
		final List<String> textList = new ArrayList<>(Arrays.asList(word.split(" ")));
		int i =0;
		while(i<spamWordList.size()) {
			final String low = spamWordList.get(i).toLowerCase();
			if(word.contains(low)) {
				final List<String> wN = new ArrayList<>(Arrays.asList(low.split(" ")));
				threshold += (((double)wN.size()/(double)textList.size())*100);					 
				break;					 
			}else {					 
				threshold += 0.;					 
			}
			i++;
			 
		 }
		 return threshold;
	}
	
	public boolean detectSpam(final String text) {
		 boolean esSpam;
		 final List<String> spamWordsList= this.repository.findOne().getSpamWords().stream().map(Word::getPalabra).collect(Collectors.toList());
		 final List<String> spamWordsListWord = new ArrayList<>();
		 final List<String> spamWordsListSentence = new ArrayList<>();

		 for(int i=0; i<spamWordsList.size(); i++) {
			 final String x= spamWordsList.get(i).trim();
			 if(x.contains(" ")) {
				 spamWordsListSentence.add(x);
			 }else {
				 spamWordsListWord.add(x);
			 }
		 }
		 
		 final Double thresholdSentence = this.sentenceThreshold(text.toLowerCase().trim(),spamWordsListSentence);
		 final Double thresholdSimple = this.simpleThreshold(text.toLowerCase().trim(),spamWordsListWord);
		 final Double threshold = thresholdSentence+thresholdSimple;		 
		 
		 if(threshold<=10.) {
			 esSpam=false; 
		 }else {
			 
			 esSpam=true; 
		 }
		 
		 return esSpam;
		
	
	}


}
