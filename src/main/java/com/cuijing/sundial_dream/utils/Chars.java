package com.cuijing.sundial_dream.utils;

import com.google.common.base.*;
import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public interface Chars {
    static String toString(InputStream is) throws IOException {
        return CharStreams.toString(new InputStreamReader(is, Charsets.UTF_8));
    }

    static String toString(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    static int startWithAny(String s, String... matches) {
        if (matches == null) {
            return -1;
        } else {
            int i = 0;
            String[] var3 = matches;
            int var4 = matches.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String match = var3[var5];
                if (s.startsWith(match)) {
                    return i;
                }

                ++i;
            }

            return -1;
        }
    }

    static int endWithAny(String s, String... matches) {
        if (matches == null) {
            return -1;
        } else {
            int i = 0;
            String[] var3 = matches;
            int var4 = matches.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String match = var3[var5];
                if (s.endsWith(match)) {
                    return i;
                }

                ++i;
            }

            return -1;
        }
    }

    static int startWithAny(String s, Iterable<String> matches) {
        if (matches == null) {
            return -1;
        } else {
            int i = 0;

            for(Iterator var3 = matches.iterator(); var3.hasNext(); ++i) {
                String match = (String)var3.next();
                if (s.startsWith(match)) {
                    return i;
                }
            }

            return -1;
        }
    }

    static int endWithAny(String s, Iterable<String> matches) {
        if (matches == null) {
            return -1;
        } else {
            int i = 0;

            for(Iterator var3 = matches.iterator(); var3.hasNext(); ++i) {
                String match = (String)var3.next();
                if (s.endsWith(match)) {
                    return i;
                }
            }

            return -1;
        }
    }

    static String reverse(String s) {
        return (new StringBuilder(s)).reverse().toString();
    }

    static Converter<String, String> upperCamelToUnderscoreConverter() {
        return Chars.Holder.UPPER_CAMEL_UNDERSCORE;
    }

    static Splitter commaSplitter() {
        return Chars.Holder.SP_COMMA;
    }

    static Joiner dotJoiner() {
        return Chars.Holder.JO_DOT;
    }

    static Joiner commaJoiner() {
        return Chars.Holder.JO_COMMA;
    }

    static Joiner.MapJoiner commaEqualMapJoiner() {
        return Chars.Holder.JO_COMMA_EQUAL;
    }

    static Joiner equalJoiner() {
        return Chars.Holder.JO_EQUAL;
    }

    public static final class Holder {
        private static final Splitter SP_COMMA = Splitter.on(',').omitEmptyStrings().trimResults();
        private static final Joiner JO_DOT = Joiner.on('.');
        private static final Joiner JO_COMMA = Joiner.on(',');
        private static final Joiner.MapJoiner JO_COMMA_EQUAL = Chars.commaJoiner().withKeyValueSeparator("=");
        private static final Joiner JO_EQUAL = Joiner.on('=');
        private static final Converter<String, String> UPPER_CAMEL_UNDERSCORE;

        public Holder() {
        }

        static {
            UPPER_CAMEL_UNDERSCORE = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.UPPER_UNDERSCORE);
        }
    }
}
