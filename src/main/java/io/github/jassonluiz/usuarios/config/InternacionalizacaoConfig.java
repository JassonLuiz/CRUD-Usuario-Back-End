package io.github.jassonluiz.usuarios.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class InternacionalizacaoConfig {

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource mensagemSource = new ReloadableResourceBundleMessageSource();
		mensagemSource.setBasename("classpath:mensagens");
		mensagemSource.setDefaultEncoding("ISO-8859-1");
		mensagemSource.setDefaultLocale( Locale.getDefault() );
		return mensagemSource;
	}
	
	@Bean
	public LocalValidatorFactoryBean validatorFactoryBean() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
}
