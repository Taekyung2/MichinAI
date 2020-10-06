package com.michin.ai.word.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.michin.ai.word.dto.payload.AddWordCommand;
import com.michin.ai.word.dto.payload.ChangeCheckCommand;
import com.michin.ai.word.dto.payload.ChangeContentCommand;
import com.michin.ai.word.dto.payload.CreateWordbookCommand;
import com.michin.ai.word.dto.payload.EditNameCommand;
import com.michin.ai.word.dto.payload.Word_content;
import com.michin.ai.word.model.Basewordbook;
import com.michin.ai.word.model.Word;
import com.michin.ai.word.model.Wordbook;
import com.michin.ai.word.repository.BaseWordBookRepository;
import com.michin.ai.word.repository.WordBookRepository;

@Service
public class WordServiceImpl implements WordService{
	
	@Autowired
	private WordBookRepository wordBookRepo;
	
	@Autowired
	private BaseWordBookRepository baseWordBookRepo;
	

	@Override
	public List<Wordbook> getWordbook(Long user_id) {
		return wordBookRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
	
	@Override
	public Wordbook getWordbook(Long user_id, String name) {
		return wordBookRepo.findByNameAndUserId(user_id, name);
	}

	@Override
	public Wordbook createWordbook(CreateWordbookCommand command) {
		Wordbook wb = Wordbook.create(command.getUser_id(), command.getName());
		Wordbook wb_exist = null;
		wb_exist = wordBookRepo.findByNameAndUserId(command.getUser_id(), command.getName());
		if(wb_exist == null) {
			wordBookRepo.insert(wb);
			return wb;
		}
		return null;
	}
	
	@Override
	public Wordbook addWord(AddWordCommand command) {
		List<Word_content> content_list = command.getContents();
		for(Word_content wc : content_list) {
			Word word = Word.create(wc.getEng(), wc.getKor());
			wordBookRepo.addWord(word, command.getWordbook_id());			
		}
		return wordBookRepo.findById(new ObjectId(command.getWordbook_id())).get();
	}

	@Override
	public Wordbook editName(EditNameCommand command) {
		Wordbook wb = wordBookRepo.findById(new ObjectId(command.getWordbook_id())).get();
		wb.setName(command.getName());
		wordBookRepo.save(wb);
		return wb;
	}
	
	@Override
	public void delWord(String wordbook_id, String word_id) {
		wordBookRepo.delWord(wordbook_id, word_id);
	}

	@Override
	public Wordbook changeCheck(ChangeCheckCommand command) {
		List<Word> word_list = command.getWords();
		for(Word word : word_list) 
			wordBookRepo.changeCheck(command.getWordbook_id(), word);
		return wordBookRepo.findById(new ObjectId(command.getWordbook_id())).get();
	}

	@Override
	public List<Wordbook> deleteWordbook(String wordbook_id) {
		Wordbook wb = wordBookRepo.findById(new ObjectId(wordbook_id)).get();
		wordBookRepo.delete(wb);
		return wordBookRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}

	@Override
	public Wordbook changeContent(ChangeContentCommand command) {
		List<Word> word_list = command.getWords();
		for(Word word : word_list)
			wordBookRepo.changeContent(command.getWordbook_id(), word);
		return wordBookRepo.findById(new ObjectId(command.getWordbook_id())).get();
	}

	@Override
	public void addBasewordbook(int level) {
		Basewordbook basewordbook = new Basewordbook();
		basewordbook.setLevel(level);
		baseWordBookRepo.insert(basewordbook);
	}

	@Override
	public void addWordtoBasewordbook(AddWordCommand command) {
		List<Word_content> content_list = command.getContents();
		for(Word_content wc : content_list) {
			Word word = Word.create(wc.getEng(), wc.getKor());
			baseWordBookRepo.addWord(command.getWordbook_id(), word);
		}
	}
	
	@Override
	public List<Basewordbook> getBasewordbook() {
		return baseWordBookRepo.findAll();
	}
	
}