package com.maygul.game.rsp.config;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
public class InternalizationConfig {

  @Bean
  public LocaleResolver localeResolver() {
    final AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
    acceptHeaderLocaleResolver.setDefaultLocale(Locale.forLanguageTag("tr"));
    return acceptHeaderLocaleResolver;
  }

  @Bean
  public MessageSource messageSource() {
    final ReloadableResourceBundleMessageSource messageSource =
        new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("classpath:lang/messages");
    messageSource.setDefaultEncoding("UTF-8");
    messageSource.setCacheSeconds(60 * 60 * 24 * 365);
    return messageSource;
  }
}