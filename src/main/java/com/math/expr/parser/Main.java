package com.math.expr.parser;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> result;
        try (Stream<String> lines = Files.lines(Paths.get("in.txt"))) {
            result = lines.collect(Collectors.toList());
        }
        List<String> outputData = new ArrayList<>();
        for (String input : result) {
            outputData.add(input + "=" + new ExpressionEvalUtil(input).parse());
        }

        Path out = Paths.get("output.txt");
        Files.write(out, outputData, Charset.defaultCharset());

    }

}
