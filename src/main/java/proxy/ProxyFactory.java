package proxy;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Нужно найти перечень прокси серверов и сохранить в файл proxy.txt
 * Например скачать перечень http://free-proxy.cz/ru/proxylist/country/RU/https/ping/level1
 * Проверить работспособность http://free.proxy-sale.com/proxy-checker/ (убираем медленные, иначе будут проблемы)
 */
public class ProxyFactory {

    public static Queue<Proxy> getProxy(String path) {
        ConcurrentLinkedQueue<Proxy> proxyQueue = new ConcurrentLinkedQueue<>();
        proxyQueue.offer(Proxy.NO_PROXY);
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                proxyQueue.offer(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(line.split(";")[0]
                        , new Integer(line.split(";")[1]))));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return proxyQueue;
    }
}
