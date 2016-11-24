package com.yunqi.logger;

import redis.clients.jedis.Jedis;

/**
 * Created by bestaone on 2016/11/24.
 */
public class RedisPusher {

    private String host;
    private int port;
    private String channel;
    private String auth;
    private Jedis jedis;

    public void init(){
        jedis = new Jedis(host, port);
        jedis.auth(auth);
    }

    public void push(String type, String msg){

        if (null == msg) return;

        if(null == channel){
            System.out.println("LOGGER WARRING: NO CHANNEL GENERATED.");
            return;
        }

        if(null == jedis){
            System.out.println("LOGGER WARRING: NO REDIS GENERATED.");
            return;
        }

        jedis.publish(channel + "." + type, msg);

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
