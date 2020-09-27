package com.michin.ai.conversation.service;

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
import org.springframework.stereotype.Service;

import com.michin.ai.conversation.model.Conversation;
import com.michin.ai.conversation.model.ConversationDto;

@Service
public class ConversationServiceImpl implements ConversationService {
	public final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public final String WEB_DRIVER_PATH = "src\\main\\resources\\chromedriver.exe";
	private WebDriver driver;

	@Override
	public List<Conversation> scrapingConversation(LocalDate date) {
		if (date == null)
			date = LocalDate.now();
		String dateStr = date.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		driver = new ChromeDriver();
		String url = "https://learn.dict.naver.com/conversation#/endic/" + dateStr;
		driver.get(url);
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<Conversation> list = new ArrayList<>();
		try {
			WebElement radioBtn = driver.findElement(By.id("interpret1"));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", radioBtn);

			List<WebElement> speakers = driver.findElements(By.cssSelector(".reading_lst .img_thumb"));
			List<WebElement> origins = driver
					.findElements(By.cssSelector(".reading_lst .txt_origin .ng-isolate-scope:not(a)"));
			List<WebElement> transs = driver.findElements(By.cssSelector(".reading_lst .txt_trans"));

			if (speakers.size() == 0 || origins.size() == 0 || transs.size() == 0)
				return null;

			for (int i = 0; i < speakers.size(); i++) {
				String speaker = speakers.get(i).getAttribute("class").replace("img_thumb", "").trim();
				Conversation conversation = new Conversation(date, speaker, origins.get(i).getText(),
						transs.get(i).getText());

				list.add(conversation);
			}
		} catch (NoSuchElementException e) {
			return null;
		} finally {
			driver.close();
		}

		return list;
	}

	@Override
	public List<ConversationDto> loadConversation(LocalDate date) {
		if (date == null)
			date = LocalDate.now();
		
		
		
		return null;
	}

	@Override
	public boolean saveConversation(List<Conversation> list) {
		// TODO Auto-generated method stub
		return false;
	}

}
