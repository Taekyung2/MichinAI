package com.michin.ai.word.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.michin.ai.word.dto.payload.AddWordCommand;
import com.michin.ai.word.dto.payload.CreateWordbookCommand;
import com.michin.ai.word.dto.payload.EditNameCommand;
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
		return wordBookRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
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
		Word word = Word.create(command.getEng(), command.getKor());
		Wordbook wb = wordBookRepo.findById(new ObjectId(command.getWordbook_id())).get();
		List<Word> list = wb.getWords();
		list.add(word);
		wb.setWords(list);
		wordBookRepo.save(wb);
		return wb;
	}

	@Override
	public Wordbook editName(EditNameCommand command) {
		Wordbook wb = wordBookRepo.findById(new ObjectId(command.getWordbook_id())).get();
		wb.setName(command.getName());
		wordBookRepo.save(wb);
		return wb;
	}
	
	@Override
	public Wordbook delWord(String wordbook_id, String word_id) {
		Wordbook wb = wordBookRepo.findById(new ObjectId(wordbook_id)).get();
		List<Word> list = wb.getWords();
		for(Word word : list) {
			if(word.getId().toString().equals(word_id)) {
				list.remove(word);
				System.out.println(word);
				break;
			}
		}
		wb.setWords(list);
		wordBookRepo.save(wb);
		return wb;
	}

	@Override
	public Wordbook changeCheck(String wordbook_id, String word_id) {
		Wordbook wb = wordBookRepo.findById(new ObjectId(wordbook_id)).get();
		List<Word> list = wb.getWords();
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getId().toString().equals(word_id)) {
				Word word = list.get(i);
				word.setCheck(!word.isCheck());
				list.set(i, word);
				break;
			}
		}
		wb.setWords(list);
		wordBookRepo.save(wb);
		return wb;
	}

	@Override
	public List<Wordbook> deleteWordbook(String wordbook_id) {
		Wordbook wb = wordBookRepo.findById(new ObjectId(wordbook_id)).get();
		wordBookRepo.delete(wb);
		return wordBookRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
}