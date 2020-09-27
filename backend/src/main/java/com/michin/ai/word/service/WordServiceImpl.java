package com.michin.ai.word.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michin.ai.controller.command.AddWordCommand;
import com.michin.ai.controller.command.CreateWordbookCommand;
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
	public List<Wordbook> getWordbook(String user_id) {
		return wordBookRepo.findAll();
	}

	@Override
	public Wordbook createWordbook(CreateWordbookCommand command) {
		Wordbook wb = Wordbook.create(command.getUser_id());
		wordBookRepo.insert(wb);
		return wb;
	}

	@Override
	public Wordbook addWord(AddWordCommand command) {
		Word word = Word.create(command.getEng(), command.getKor());
		Wordbook wb = wordBookRepo.findById(new ObjectId(command.getWordbook_id())).get();
		List<Word> list = wb.getWords();
		list.add(word);
		wb.setWords(list);
		wordBookRepo.save(wb);
		return wb;
	}
}