package dev.npex42.npgfx;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record EngineSpec(
        int width,
        int height,
        String title,
        double pollingTimeout,
        Config config
    ) {


    public static EngineSpec LoadFile(String filepath) throws RuntimeException {
            Config conf = Config.LoadFile(filepath);

            int width = conf.GetIntOr("Width", 1080);
            int Height = conf.GetIntOr("Height", 720);
            String title = conf.GetStringOr("Title", "Sandbox");
            boolean lazyPresent = conf.GetIntOr("LazyPresenting", 0) == 1;

            double pollingTime  = (lazyPresent) ? 1.0 / conf.GetIntOr("MinFrameRate", 20) : 0.0;

            return new EngineSpec(
                    width, Height,
                    title,
                    pollingTime,
                    conf
            );
    }
}
