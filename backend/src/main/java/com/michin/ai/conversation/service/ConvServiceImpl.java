package com.michin.ai.conversation.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michin.ai.conversation.model.Conversation;
import com.michin.ai.conversation.repository.ConvRepository;

@Service
public class ConvServiceImpl implements ConvService {

	@Autowired
	private ConvRepository convRepo;

	@Autowired
	private ScrapingConvService scrapService;

	@Override
	public List<Conversation> loadConversation(LocalDate date) {
		if (date == null)
			date = LocalDate.now();

		DayOfWeek dayOfWeek = date.getDayOfWeek();
		if (dayOfWeek == DayOfWeek.SUNDAY) {
			return null;
		}

		List<Conversation> convList = convRepo.findByDate(date);
		if (convList.size() == 0)
			convList = scrapService.scrapingConversation(date);

		return convList;
	}

	@Override
	public void saveConversation(List<Conversation> list) {
		convRepo.saveAll(list);
	}

}
