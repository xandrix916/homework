package edu.hw6;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings({"unused", "ConstantName"})
public class Problem3 {
    public static final AbstractFilter regularFile = Files::isRegularFile;

    public static final AbstractFilter readable = Files::isReadable;

    public static AbstractFilter largerThan(int length) {
        return path -> path.toFile().length() > length;
    }

    public static AbstractFilter globMatches(String glob) {
        return path -> path.getFileSystem().getPathMatcher("glob:" + glob).matches(path.getFileName());
    }

    public static AbstractFilter regexContains(String regex) {
        return path -> Pattern.compile("*" + regex + "*").matcher(path.getFileName().toString()).matches();
    }

    @SuppressWarnings("MagicNumber")
    public void tryFilter(Path dir) {
        DirectoryStream.Filter<Path> filter = regularFile
            .and(readable)
            .and(largerThan(100_000))
            .and(globMatches("*.png"))
            .and(regexContains("[-]"));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(System.out::println);
        } catch (IOException e) {
            log.error("Problems with output");
        }
    }

    @FunctionalInterface
    public interface AbstractFilter extends DirectoryStream.Filter<Path> {
        default AbstractFilter and(AbstractFilter other) {
            assert other != null;
            return (path) -> accept(path) && other.accept(path);
        }
    }
}
