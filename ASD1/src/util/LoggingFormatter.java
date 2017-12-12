/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Class used for logger formatting
 * @author kop0203
 */
public class LoggingFormatter extends Formatter {
    
    @Override
    public String format(LogRecord record) {
        StringBuilder builder = new StringBuilder();
        builder.append(record.getLevel()).append(": ");
        builder.append(formatMessage(record));
        builder.append(System.lineSeparator());
        // pre-Java7: builder.append(System.getProperty('line.separator'));
        return builder.toString();
    }
}
