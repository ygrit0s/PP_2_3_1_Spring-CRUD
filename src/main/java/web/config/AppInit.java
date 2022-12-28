package web.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	// Метод, указывающий на класс конфигурации
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}
	
	// Добавление конфигурации, в которой инициализируем ViewResolver, для корректного отображения jsp.
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{WebConfig.class};
	}
	
	/* Данный метод указывает url, на котором будет базироваться приложение */
	@Override
	protected String @NotNull [] getServletMappings() {
		return new String[]{"/"};
	}
	
//	@Override
//	public void onStartup(@NotNull ServletContext servletContext) throws ServletException {
//		super.onStartup(servletContext);
//		registerHiddenFieldFilter(servletContext);
//	}
//
//	private void registerHiddenFieldFilter(ServletContext servletContext) {
//	}
}