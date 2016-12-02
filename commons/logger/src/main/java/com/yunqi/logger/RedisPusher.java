package com.yunqi.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bestaone on 2016/11/24.
 */
public class RedisPusher {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String host;
    private int port;
    private String channel;
    private String auth;
    private Jedis jedis;
    private Pipeline pipeline;
    private static int count = 0;

    public void init(){
        jedis = new Jedis(host, port);
        jedis.auth(auth);
        pipeline = jedis.pipelined();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                submit();
            }
        }, 10000, 5000); //延时10秒，每5秒执行一次
    }

    public void push(String type, String msg){

        if (null == msg) return;

        if(null == channel){
            logger.debug("LOGGER WARRING: NO CHANNEL GENERATED.");
            return;
        }

        if(null == jedis){
            logger.debug("LOGGER WARRING: NO REDIS GENERATED.");
            return;
        }

        pipeline.publish(channel + "." + type, msg);

        if(++count>=5){
            submit();
        }

    }

    private void submit(){
        pipeline.sync();
        count = 0;
    }

    public void close() {
        if (null != jedis) {
            jedis.close();
        }
    }

    @Override
    public void finalize() throws Throwable {
        close();
        super.finalize();
    }


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

}
