package qzx;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author: qzx
 * @Date: 2021/5/22 14:47
 */
public class Connection {

    private HttpURLConnection baidu;
    private HttpURLConnection buptLogin;

    public Connection() {
        try {
            baidu = (HttpURLConnection) new URL("http://www.baidu.com").openConnection();
            baidu.setRequestMethod("GET");
            baidu.setConnectTimeout(15000);
            baidu.setReadTimeout(60000);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            buptLogin = (HttpURLConnection) new URL("http://10.3.8.211/login").openConnection();
            buptLogin.setRequestMethod("POST");
            buptLogin.setConnectTimeout(15000);
            buptLogin.setReadTimeout(60000);
            buptLogin.setDoOutput(true);
            buptLogin.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        assert (baidu != null);
        try {
            baidu.connect();
            return baidu.getResponseCode() == 200;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            baidu.disconnect();
        }
    }

    public boolean connectBuptNet() {
        assert (buptLogin != null);
        try {
            buptLogin.connect();
            OutputStream os = new BufferedOutputStream(buptLogin.getOutputStream());
            os.write("user=***&pass=123456".getBytes()); //用户名密码
            os.flush();
            os.close();
            return buptLogin.getResponseCode() == 200;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            buptLogin.disconnect();
        }
    }
}
