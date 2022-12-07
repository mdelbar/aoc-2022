package be.mdelbar.aoc2022.day7;

import be.mdelbar.aoc2022.AOCDay;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day7 implements AOCDay {

    private static final Logger logger = LoggerFactory.getLogger(Day7.class);

    @Override
    public void part1(byte[] inputBytes) {
        String input = new String(inputBytes, StandardCharsets.UTF_8);
        var lines = input.split("\\n");

        var root = createDirectoryStructure(lines);
        List<Directory> allDirsBelow = findDirsWithMaxSize(root, 100_000);
        var solution = allDirsBelow.stream()
                .mapToLong(Directory::size)
                .sum();

        logger.info("Solution: " + solution);
    }

    @Override
    public void part2(byte[] inputBytes) {
        String input = new String(inputBytes, StandardCharsets.UTF_8);
        var lines = input.split("\\n");

        long totalSpace = 70_000_000;
        long neededSpace = 30_000_000;

        var root = createDirectoryStructure(lines);

        long usedSpace = root.size();
        long spaceToFree = neededSpace - (totalSpace - usedSpace);

        List<Directory> allDirsBelow = findDirsWithMinSize(root, spaceToFree);
        var solution = allDirsBelow.stream()
                .min(Comparator.comparing(Directory::size));

        logger.info("Solution: " + solution.get().size());
    }


    private List<Directory> findDirsWithMaxSize(Directory root, long maxSize) {
        List<Directory> dirs = new ArrayList<>();
        findDirsWithMaxSize(dirs, root, maxSize);
        return dirs;
    }

    private void findDirsWithMaxSize(List<Directory> dirs, Directory dir, long maxSize) {
        if(dir.size() <= maxSize) {
            dirs.add(dir);
        }
        for(Directory child : dir.children) {
            findDirsWithMaxSize(dirs, child, maxSize);
        }
    }

    private List<Directory> findDirsWithMinSize(Directory root, long minSize) {
        List<Directory> dirs = new ArrayList<>();
        findDirsWithMinSize(dirs, root, minSize);
        return dirs;
    }

    private void findDirsWithMinSize(List<Directory> dirs, Directory dir, long minSize) {
        if(dir.size() >= minSize) {
            dirs.add(dir);
        }
        for(Directory child : dir.children) {
            findDirsWithMinSize(dirs, child, minSize);
        }
    }

    private Directory createDirectoryStructure(String[] lines) {
        Directory root = new Directory(null, "/");
        Directory currentDir = root;
        for(String line : lines) {
            if(line.startsWith("$ cd")) {
                String target = line.substring("$ cd ".length());
                currentDir = findOrCreateDirectory(currentDir, target);
            }
            else if(line.startsWith("$ ls")) {
                // Ignore, nothing relevant happens here
            }
            else if(line.startsWith("dir ")) {
                // Ignore, we create dirs as needed
            }
            else {
                // Files
                String[] split = line.split(" ");
                currentDir.files.add(new File(split[1], Long.parseLong(split[0])));
            }
        }
        return root;
    }

    private Directory findOrCreateDirectory(Directory currentDir, String target) {
        // Root
        if(target.equals("/")) {
            while(currentDir.getParent() != null) {
                currentDir = currentDir.getParent();
            }
            return currentDir;
        }

        // Parent
        if(target.equals("..")) {
            return currentDir.getParent() == null ? currentDir : currentDir.getParent();
        }

        // Other (sub-dir to create or enter)
        return currentDir.children.stream()
                .filter(dir -> target.equals(dir.name))
                .findFirst()
                .orElse(new Directory(currentDir, target));
    }

    @Data
    private class Directory {
        private Directory parent;
        private String name;
        private List<Directory> children = new ArrayList<>();
        private List<File> files = new ArrayList<>();

        public Directory(Directory parent, String name) {
            this.parent = parent;
            this.name = name;
            if(parent != null) {
                parent.children.add(this);
            }
        }

        public long size() {
            return children.stream().mapToLong(Directory::size).sum()
                    + files.stream().mapToLong(File::size).sum();
        }
    }

    private record File (String name, long size) {}

}
