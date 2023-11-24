package edu.hw6;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


@Slf4j
public class DiskMap implements Map<String, String> {
    private static final String FORMAT = "%s:%s\n";
    private final Map<String, String> bufferMap = new HashMap<>();
    private final Path currentPath;

    public static String readFile(Path mapPath) throws IOException {
        StringBuilder mapBuilder = new StringBuilder();
        try (FileChannel inChannel = FileChannel.open(mapPath)) {
            ByteBuffer buff = ByteBuffer.allocate((int) inChannel.size());
            int bytesRead = inChannel.read(buff);
            while (bytesRead != -1) {
                buff.flip();

                while (buff.hasRemaining()) {
                    mapBuilder.append((char) buff.get());
                }

                buff.clear();
                bytesRead = inChannel.read(buff);
            }
        }
        return mapBuilder.toString();
    }

    private void saveMapFile() throws IOException {
        try (FileOutputStream fos = new FileOutputStream(currentPath.toString());
             FileChannel outChannel = fos.getChannel()) {
            for (var key: bufferMap.keySet()) {
                ByteBuffer buff = ByteBuffer
                    .wrap(String.format(FORMAT, key, bufferMap.get(key))
                        .getBytes(StandardCharsets.UTF_8));
                outChannel.write(buff);
            }
        }
    }

    private void saveMapFileHandler() {
        try {
            saveMapFile();
        } catch (IOException e) {
            log.error("It seem to be a problem with writing in file");
        }
    }

    private void fillBuffer(String fromFile) {
        var stringArray = fromFile.split("\n");
        for (var s: stringArray) {
            if (!s.contains(":")) {
                log.warn("Illegal string format. Entry ignored");
                continue;
            }
            String key = s.substring(0, s.indexOf(":")); // формат строки в файле - ключ:значение
            String value = s.substring(s.indexOf(":") + 1);
            bufferMap.put(key, value);
        }
    }

    private void putEntryInFile(String key, String value) {
        bufferMap.put(key, value);
        saveMapFileHandler();
    }

    private void removeEntryFromFile(Object key) throws IllegalArgumentException, NoSuchElementException {
        if (key.getClass() != String.class) {
            throw new IllegalArgumentException();
        }
        if (!keySet().contains(key)) {
            throw new NoSuchElementException();
        }
        bufferMap.remove(key);
        saveMapFileHandler();
    }

    /**
    * Конструктор предназначен для создания класса
     * при непосредственной загрузке значений из файла
    **/
    public DiskMap(Path mapPath) {
        String fromFile = "";
        currentPath = mapPath;
        try {
            fromFile = readFile(mapPath);
        } catch (IOException ioException) {
            log.error("Problems with reading file");
        }
        fillBuffer(fromFile);
    }

    /**
     * Конструктор предназначен для создания из действующей хеш-таблицы и последующей выгрузке в файл.
     */
    public DiskMap(HashMap<String, String> hashMap) {
        this.bufferMap.putAll(hashMap);
        currentPath = Path.of("src/main/java/edu/hw6/diskmap.txt");
        saveMapFileHandler();
    }

    public DiskMap(HashMap<String, String> hashMap, Path customPath) {
        this.bufferMap.putAll(hashMap);
        currentPath = customPath;
        saveMapFileHandler();
    }


    @Override
    public int size() {
        return bufferMap.size();
    }

    @Override
    public boolean isEmpty() {
        return bufferMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return bufferMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return bufferMap.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return bufferMap.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        putEntryInFile(key, value);
        return null;
    }


    @Override
    public String remove(Object key) {
        String removedString = null;
        try {
            removedString = get(key);
            removeEntryFromFile(key);
        } catch (IllegalArgumentException exception) {
            log.error("Forbidden type of argument");
        } catch (NoSuchElementException exception) {
            log.error("No such element found");
        }
        return removedString;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        this.bufferMap.putAll(m);
        saveMapFileHandler();
    }

    @Override
    public void clear() {
        this.bufferMap.clear();
        saveMapFileHandler();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return bufferMap.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return bufferMap.values();
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        return bufferMap.entrySet();
    }

    public Map<String, String> getBufferMap() {
        return bufferMap;
    }
}
