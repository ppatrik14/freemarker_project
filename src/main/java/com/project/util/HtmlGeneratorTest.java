package com.project.util;

import com.project.HtmlGenerator;
import freemarker.template.TemplateException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

public class HtmlGeneratorTest {
    private HtmlGenerator htmlGenerator;

    @Before
    public void setUp() {
        htmlGenerator = new HtmlGenerator();
    }

    @After
    public void tearDown() throws IOException {
        // Törli a generált fájlt a tesztek után
        Files.deleteIfExists(Path.of("output.html"));
    }

    @Test
    public void testGenerateHtml() throws IOException, TemplateException {
        String name = "John Doe";
        String repositoryUrl = "https://github.com/example";
        String email = "john.doe@example.com";

        htmlGenerator.generateHtml("output.html", name, repositoryUrl, email);

        File outputFile = new File("output.html");
        assertTrue(outputFile.exists());
        assertTrue(outputFile.isFile());

        String content = Files.readString(Path.of("output.html"));
        assertTrue(content.contains("<title>" + name + " - Teszt Feladat</title>"));
        assertTrue(content.contains("<a href=\"" + repositoryUrl + "\">Megoldás</a>"));
        assertTrue(content.contains("<td>" + name + "</td>"));
        assertTrue(content.contains("<td>" + email + "</td>"));
    }

    @Test(expected = IOException.class)
    public void testGenerateHtml_InvalidFileName() throws IOException, TemplateException {
        String name = "John Doe";
        String repositoryUrl = "https://github.com/example";
        String email = "john.doe@example.com";

        // Nem létező könyvtárba próbálunk generálni, IOException-t várunk
        htmlGenerator.generateHtml("nonexistent/output.html", name, repositoryUrl, email);
    }
}

