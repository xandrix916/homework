package edu.hw6;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem1 {

    private void fill(DiskMap diskMap, Map<String, String> toAdd, List<String> toRemove) {
        for (var key: toAdd.keySet()) {
            diskMap.put(key, toAdd.get(key));
        }
        for (var key: toRemove) {
            diskMap.remove(key);
        }
    }

    public DiskMap createFromFileAndWrite(Path path, Map<String, String> toAdd,
        List<String> toRemove) {
        DiskMap diskMap = new DiskMap(path);
        fill(diskMap, toAdd, toRemove);
        return diskMap;
    }

    public DiskMap createToFileAndWrite(HashMap<String, String> initValues, Map<String, String> toAdd,
        List<String> toRemove) {
        DiskMap diskMap = new DiskMap(initValues);
        fill(diskMap, toAdd, toRemove);
        return diskMap;
    }
}
