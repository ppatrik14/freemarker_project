package com.project;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class HtmlGenerator {

    private Configuration cfg;

    public HtmlGenerator() {
        cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setClassForTemplateLoading(HtmlGenerator.class, "/templates");
    }

    public void generateHtml(String fileName, String name, String repositoryUrl, String email) throws IOException, TemplateException {
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("name", name);
        dataModel.put("repositoryUrl", repositoryUrl);
        dataModel.put("email", email);

        Template template = cfg.getTemplate("template.ftl");

        try (Writer fileWriter = new FileWriter(fileName)) {
            template.process(dataModel, fileWriter);
        }

        System.out.println("HTML file generated successfully: " + fileName);
    }

    public static void main(String[] args) {
        try {
            HtmlGenerator htmlGenerator = new HtmlGenerator();
            htmlGenerator.generateHtml("output.html", "Pirvuta Patrik", "https://github.com/example", "patrikpirvuta@gmail.com");
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}



