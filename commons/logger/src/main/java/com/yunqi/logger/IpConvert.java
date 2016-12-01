package com.yunqi.logger;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpConvert extends ClassicConverter {

    private final static int LENGTH = 15;
    private static String ip;

    @Override
    public String convert(ILoggingEvent event) {
        if(ip==null){
            try {
                InetAddress inetAddress = InetAddress.getLocalHost();
                StringBuffer sb = new StringBuffer(inetAddress.getHostAddress());
                int ipLen = sb.length();
                while (ipLen < LENGTH) {
                    sb.append(" ");
                    ipLen = sb.length();
                }
                ip = sb.toString();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        return ip;
    }

}