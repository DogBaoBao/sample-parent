package com.dbb.hyjal.boot.loader.util;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tc
 * @date 2019-10-18
 */
public final class LogPrintUtils {

    public static void printJar(List<File> fatJars) {
        print(fatJars.parallelStream().map(File::getName).collect(Collectors.toList()));
    }

    public static void print(List<String> fatJars) {
        int line = 72;
        String spLine = "------------ ---------- ------------";
        String start = "HyjalBoot classloader load";
        String empty = "jar is empty";
        String xx = " ** ";
        String x = "  *  ";

        StringBuilder sb = new StringBuilder();

        boolean nilFatJar = true;
        int size;
        if (fatJars == null || fatJars.size() == 0) {
            size = 5;
        } else {
            nilFatJar = false;
            size = fatJars.size() + 4;
        }

        for (int i = 1; i <= size; i++) {
            if (i == 1) {
                line(sb, line, xx, spLine);
            } else if (i == 2) {
                line(sb, line, xx, start);
            } else if (i == 3) {
                line(sb, line, x, "");
            } else if (i == 4) {
                if (nilFatJar) {
                    line(sb, line, x, empty);
                } else {
                    line(sb, line, x, fatJars.get(i - 4));
                }
            } else if (i == size) {
                line(sb, line, xx, spLine);
                continue;
            } else {
                line(sb, line, x, fatJars.get(i - 4));
            }
            sb.append("\r\n");
        }

        System.out.println(sb);
    }

    private static void line(StringBuilder sb, int length, String fix, String content) {
        String nilS = " ";
        int nilSL = length - fix.length() * 2 - content.length();

        int i = nilSL & 1;
        boolean b = (1 == i) ? true : false;

        sb.append(fix);
        for (int j = 0; j < nilSL / 2; j++) {
            sb.append(nilS);
        }
        sb.append(content);
        for (int j = 0; j < nilSL / 2; j++) {
            sb.append(nilS);
        }

        if (b) {
            sb.append(nilS);
        }

        sb.append(fix);
    }

}
