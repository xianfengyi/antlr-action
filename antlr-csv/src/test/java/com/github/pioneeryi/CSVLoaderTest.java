package com.github.pioneeryi;

import com.github.pioneeryi.codegen.CSVLexer;
import com.github.pioneeryi.codegen.CSVParser;
import java.io.IOException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

public class CSVLoaderTest {

    @Test
    public void testLoad() throws IOException {
        String fileContent = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("example.csv"),
                "utf-8");
        CharStream input = CharStreams.fromString(fileContent);
        CSVLexer lexer = new CSVLexer(input);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        CSVParser parser = new CSVParser(tokenStream);

        ParseTree tree = parser.file();

        ParseTreeWalker walker = new ParseTreeWalker();
        CSVLoader csvLoader = new CSVLoader();
        walker.walk(csvLoader, tree);

        String expected = "[{name=pioneeryi,  age= 29,  sex= men}, {name=dandan,  age= 26,  sex= women}]";
        Assert.assertEquals(expected, csvLoader.getRows().toString());
    }
}