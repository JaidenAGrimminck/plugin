package rocks.jaiden.plugin;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okio.ByteString;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WSClient {

    private WebSocket socket;

    public void connect(String url) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        WebSocket socket = client.newWebSocket(request, new okhttp3.WebSocketListener() {
            @Override
            public void onOpen(okhttp3.WebSocket webSocket, okhttp3.Response response) {
                System.out.println("Connected to server");
            }

            @Override
            public void onMessage(okhttp3.WebSocket webSocket, String text) {
                System.out.println("Received message: " + text);
            }

            @Override
            public void onClosed(okhttp3.WebSocket webSocket, int code, String reason) {
                System.out.println("Connection closed");
            }

            @Override
            public void onFailure(okhttp3.WebSocket webSocket, Throwable t, okhttp3.Response response) {
                System.out.println("Connection failed");
                System.out.println(t.getMessage());
                System.out.println(t.getCause());
                System.out.println(response.code());
            }
        });

        this.socket = socket;
    }

    public void write(byte[] data) throws IOException {
        socket.send(ByteString.of(data));
    }

    public void close() {
        socket.close(1000, "Goodbye!");
    }

    public void sendLocation(double x, double y, long timestamp, double lastX, double lastY, long lastTimestamp, double rot) throws IOException {
        byte[] byteList = new byte[4 + Double.BYTES * 3];
        //first four bytes is 0x01, 0x00, 0x00, 0x00
        System.arraycopy(new byte[]{0x01, 0x00, 0x00, 0x00}, 0, byteList, 0, 4);


        double timeDiff = (timestamp - lastTimestamp) / 1000d;
        double xMotion = (x - lastX) / -timeDiff;
        double yMotion = (y - lastY) / timeDiff;

//        Bukkit.getPlayer(PlayerRobotMonitor.playerUUID).sendMessage("X: " + xMotion + ", Y: " + yMotion + ", Time: " + timeDiff);
//        Bukkit.getPlayer(PlayerRobotMonitor.playerUUID).sendMessage("NOW (" + x + ", " + y + "), Time: " + timestamp);
//        Bukkit.getPlayer(PlayerRobotMonitor.playerUUID).sendMessage("LAST (" + lastX + ", " + lastY + "), Time: " + lastTimestamp);

        byte[] xBytes = new byte[Double.BYTES];
        ByteBuffer.wrap(xBytes).putDouble(xMotion);
        byte[] yBytes = new byte[Double.BYTES];
        ByteBuffer.wrap(yBytes).putDouble(yMotion);

        byte[] rotation = new byte[Double.BYTES];
        ByteBuffer.wrap(rotation).putDouble(rot);

        System.arraycopy(xBytes, 0, byteList, 4, Double.BYTES);
        System.arraycopy(yBytes, 0, byteList, 4 + Double.BYTES, Double.BYTES);
        System.arraycopy(rotation, 0, byteList, 4 + Double.BYTES * 2, Double.BYTES);

        write(byteList);
    }

    public void stopRobot() throws IOException {
        byte[] byteList = new byte[4];
        //first four bytes is 0x02, 0x00, 0x00, 0x00
        System.arraycopy(new byte[]{0x02, 0x00, 0x00, 0x00}, 0, byteList, 0, 4);

        write(byteList);
    }
}
