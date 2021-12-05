package com.maygul.game.rsp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class ScheduledConfig {
  private static final Integer POOL_SIZE = 4;

  @Bean
  public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
    ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
    scheduler.setPoolSize(POOL_SIZE);
    return scheduler;
  }
}
