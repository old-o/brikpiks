package net.doepner.brikpiks;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Command line options
 */
public final class Options {

    private final Path imagePath;
    private final int colors;
    private final boolean ignoreWhite;

    static String usage(IllegalArgumentException e) {
        final String s = System.lineSeparator();
        return "Error : " + e.getMessage() + s
                + "Supported options: [-w] [-N] image-file" + s
                + " -w : ignore white pixels" + s
                + " -N : where N is the number of colors to use" + s
                + " image-file : The image file to process";
    }

    static Options parse(String[] args) {
        boolean ignoreWhite = false;
        int colors = 8;
        Path path = null;
        for (String arg : args) {
            if ("-w".equals(arg)) {
                ignoreWhite = true;
            } else if (arg.startsWith("-")) {
                try {
                    colors = Integer.parseInt(arg.substring(1));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Unrecognized option: " + arg);
                }
            } else {
                path = Paths.get(arg);
            }
        }
        return new Options(path, colors, ignoreWhite);
    }

    Options(Path imagePath, int colors, boolean ignoreWhite) {
        this.imagePath = imagePath;
        if (!Files.isReadable(imagePath)) {
            throw new IllegalArgumentException("Cannot read file : " + imagePath);
        }
        this.colors = colors;
        this.ignoreWhite = ignoreWhite;
    }

    public Path getImagePath() {
        return imagePath;
    }

    public int getColors() {
        return colors;
    }

    public boolean isIgnoreWhite() {
        return ignoreWhite;
    }

    @Override
    public String toString() {
        return "Options{" +
                "imagePath=" + imagePath +
                ", colors=" + colors +
                ", ignoreWhite=" + ignoreWhite +
                '}';
    }
}
