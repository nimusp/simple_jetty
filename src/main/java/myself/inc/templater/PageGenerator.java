package myself.inc.templater;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class PageGenerator {

    private static PageGenerator generator;
    private final Configuration configuration;

    public static PageGenerator getInstance() {
        if (generator == null) {
            generator = new PageGenerator();
        }
        return generator;
    }

    private PageGenerator() {
        configuration = new Configuration();
    }

    public String getPage(String filename, Map<String, Object> data) {
        Writer stream = new StringWriter();
        try {
            Template template = configuration.getTemplate( filename);
            template.process(data, stream);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream.toString();
    }

}
