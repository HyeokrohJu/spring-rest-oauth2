package com.hrpj.core.config;

import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VelocityConfig {

	@Bean
	public VelocityEngine velocityEngine( ) throws Exception {
		final Properties properties = new Properties( );
		properties.setProperty( "input.encoding", "UTF-8" );
		properties.setProperty( "output.encoding", "UTF-8" );
		properties.setProperty( RuntimeConstants.RESOURCE_LOADER, "classpath" );
		properties.setProperty( "classpath.resource.loader.class", ClasspathResourceLoader.class.getName( ) );
		final VelocityEngine velocityEngine = new VelocityEngine( properties );
		return velocityEngine;
	}
}
