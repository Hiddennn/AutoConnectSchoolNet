package qzx;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @Author: qzx
 * @Date: 2021/5/22 14:43
 */
@Slf4j
public class Main {

    public static void main(String[] args) {
        Connection connection = new Connection();
        while (true) {
            if (connection.isConnected()) {
                log.info("The internet is connected, test again after 1 min...");
                try {
                    TimeUnit.SECONDS.sleep(60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                log.info("disconnect, try login...");
                while (!connection.connectBuptNet()) {
                    log.info("login failed, try again...");
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("connected now.");
                try {
                    TimeUnit.SECONDS.sleep(60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
