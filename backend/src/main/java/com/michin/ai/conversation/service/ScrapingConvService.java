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
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.michin.ai.conversation.model.Conversation;
import com.michin.ai.conversation.repository.ConvRepository;

@Service
public class ScrapingConvService {
	@Value("${WEB_DRIVER_ID}")
	private String WEB_DRIVER_ID;
	@Value("${WEB_DRIVER_PATH}")
	private String WEB_DRIVER_PATH;

	@Autowired
	private ConvRepository convRepo;
	private WebDriver driver;

	public List<Conversation> scrapingConversation(LocalDate date) {
		if (date == null)
			date = LocalDate.now();

		System.out.println("SCRAPING CONVERSATION : " + date);
		String dateStr = date.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
		options.addArguments("start-maximized"); // open Browser in maximized mode
		options.addArguments("disable-infobars"); // disabling infobars
		options.addArguments("--disable-extensions"); // disabling extensions
		options.addArguments("--disable-gpu"); // applicable to windows os only
		options.addArguments("--no-sandbox"); // Bypass OS security model
		options.setHeadless(true);
//		options.addArguments("--no-sandbox");
//		options.addArguments("--disable-dev-shm-usage");
//		options.addArguments("--remote-debugging-port=9222");

		driver = new ChromeDriver(options);
		String url = "https://learn.dict.naver.com/conversation#/endic/" + dateStr;
		driver.get(url);
		try {
			TimeUnit.SECONDS.sleep(3);
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

			if (convRepo.findByDate(date).size() == 0)
				convRepo.saveAll(list);

		} catch (NoSuchElementException e) {
			return null;
		} finally {
			if (driver != null) {
				driver.close();
				driver.quit();
				driver = null;
			}
		}
		return list;
	}

	// 초 분 시 일 월 요일
	// 매일 0시 1분마다 실행
	@Scheduled(cron = "0 1 0 * * *")
	public void scrapingConvSch() {
		scrapingConversation(null);
	}

}
