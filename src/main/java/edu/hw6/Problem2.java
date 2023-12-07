package edu.hw6;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Problem2 {
    private void writeDown(String path, String content) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(path);
             FileChannel outChannel = fos.getChannel()) {
            ByteBuffer buff = ByteBuffer
                .wrap(content.getBytes(StandardCharsets.UTF_8));
            outChannel.write(buff);
        }
    }

    public boolean cloneFile(Path path) {
        String format = "%s%s.txt";
        String currentPath = path.toString();
        String directory = currentPath.substring(0, currentPath.lastIndexOf("/") + 1);
        StringBuilder filename =
            new StringBuilder(currentPath.substring(currentPath.lastIndexOf("/") + 1, currentPath.lastIndexOf(".")));
        File file = new File(currentPath);
        if (file.exists() && !file.isDirectory()) {
            String content = "";
            try {
                content = DiskMap.readFile(path);
            } catch (IOException e) {
                log.error("Problem with input");
            }
            filename.append(" — копия");
            currentPath = String.format(format, directory, filename);
            File newFile = new File(currentPath);
            int counter = 2;
            while (newFile.exists() && !newFile.isDirectory()) {
                filename.append(" (%d)".formatted(counter));
                currentPath = String.format(format, directory, filename);
                newFile = new File(currentPath);
            }
            try {
                writeDown(currentPath, content);
            } catch (IOException e) {
                log.error("Problem with output");
            }
            return true;
        } else {
            return false;
        }
    }
}
