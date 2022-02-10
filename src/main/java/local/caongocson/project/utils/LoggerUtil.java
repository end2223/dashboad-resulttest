package local.caongocson.project.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil {
    public static Logger getLogger(Class<?> T){
        Logger logger = LogManager.getLogger(T);
        return logger;
    }
}
