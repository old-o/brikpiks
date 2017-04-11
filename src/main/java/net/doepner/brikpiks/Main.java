package net.doepner.brikpiks;

import org.guppy4j.log.Log;
import org.guppy4j.log.LogProvider;
import org.guppy4j.log.Slf4jLogProvider;

import static org.guppy4j.log.Log.Level.info;

/**
 * Application entry point
 */
public final class Main {

    public static void main(String[] args) {
        final LogProvider logProvider = new Slf4jLogProvider();
        final Log log = logProvider.getLog(Main.class);

        final Options options = parseOptions(args);
        log.as(info, options.toString());



        // TODO : Get started by reviewing
        // http://stackoverflow.com/questions/15777821/how-can-i-pixelate-a-jpg-with-java
        // http://stackoverflow.com/questions/25761438/understanding-bufferedimage-getrgb-output-values
        // http://stackoverflow.com/questions/28162488/get-average-color-on-bufferedimage-and-bufferedimage-portion-as-fast-as-possible
    }


    private static Options parseOptions(String[] args) {
        try {
            return Options.parse(args);
        } catch (IllegalArgumentException e) {
            System.err.println(Options.usage(e));
            System.exit(1);
            throw e;
        }
    }
}
