package br.com.alura.adopet.api.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class Messages {

    @Value("${spring.messages.default-locale}")
    private String defaultLocale;

    @Bean
    public LocaleResolver localeResolver() {
        return new SessionLocaleResolver();
    }

    @Bean(name="messageSource")
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    /**
     * retrieved from the message source with the given code, no arguments presented
     *
     * @param  message    code of the message to be retrieved from the message source
     * @return           the retrieved message
     */
    public String getMessage(String message) {
        return getMessage(message,null);
    }

    /**
     * Retrieves a localized message from the message source based on the provided message code and arguments.
     *
     * @param  message    the code of the message to be retrieved from the message source
     * @param  args       optional arguments to be used in the message
     * @return             the retrieved message, or an empty string if the message code is blank or the message source is not found
     */
    public String getMessage(String message, Object... args) {
        if (StringUtils.isNotBlank(defaultLocale)) defaultLocale = "pt-BR";

        if (StringUtils.isNotBlank(message)) {
            return messageSource().getMessage(message, args, Locale.forLanguageTag(defaultLocale));
        }

        return "";
    }

}