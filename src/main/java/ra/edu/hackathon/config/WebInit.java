package ra.edu.hackathon.config;

import org.springframework.lang.NonNull;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    @NonNull
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    @NonNull
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(jakarta.servlet.ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(new jakarta.servlet.MultipartConfigElement(
                "",
                50 * 1024 * 1024,
                50 * 1024 * 1024,
                0
        ));
    }
}
