package cz.upce.diplomovaprace;


import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.context.annotation.Configuration;

//https://stackoverflow.com/questions/23527833/spring-4-sitemesh-with-java-config-and-no-xml
@Configuration
public class MySiteMeshFilter extends ConfigurableSiteMeshFilter {
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
       // builder.addDecoratorPath("/*", "/WEB-INF/view/fragments/header.jsp");
      //  builder. addExcludedPath("/WEB-INF/view/fragments/*");
        /*
        // Map default decorator. This shall be applied to all paths if no other paths match.
        builder.addDecoratorPath("/*", "/default-decorator.html")
                // Map decorators to path patterns.
                .addDecoratorPath("/admin/*", "/another-decorator.html")
                .addDecoratorPath("/*.special.jsp", "/special-decorator.html")
                // Map multiple decorators to the a single path.
                .addDecoratorPaths("/articles/*", "/decorators/article.html",
                        "/decoratos/two-page-layout.html",
                        "/decorators/common.html")
                // Exclude path from decoration.
                .addExcludedPath("/javadoc/*")
                .addExcludedPath("/brochures/*");*/
    }
}