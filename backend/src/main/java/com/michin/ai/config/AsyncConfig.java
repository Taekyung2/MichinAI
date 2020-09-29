package com.michin.ai.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig extends AsyncConfigurerSupport {

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor(); // 비동기로 호출하는 Thread에 대한 설정
		executor.setCorePoolSize(2);// 기본적으로 실행을 대기하고 있는 Tread의 갯수
		executor.setMaxPoolSize(10);// 동시 동작하는 최대 Thread의 갯수
		executor.setQueueCapacity(500);// MaxPoolSize를 초과하는 요청이 Thread 생성 요청시 해당 내용을 Queue에 저장하게 되고,
										// 사용할 수 있는 Thread의 여유 자리가 발생시 하나씩 꺼내어 동작
		executor.setThreadNamePrefix("Async-");// spring이 생성하는 Thread의 접두사 지정
		executor.initialize();

		return super.getAsyncExecutor();
	}
}
