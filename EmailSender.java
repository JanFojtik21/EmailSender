
import java.net.*;
import java.io.*;

public class EmailSender {

    Socket socket;

    public EmailSender(String host, int port) throws IOException {
        socket = new Socket(host, port);
    }


    public void send(String from, String to, String subject, String text) throws IOException, InterruptedException {
        try (var socket = new Socket("smtp.utb.cz", 25)) {
            var in = socket.getInputStream();
            var out = socket.getOutputStream();
            out.write(("HELO FOJTAS\r\n" +
                    "MAIL FROM: " + from + "\r\n" +
                    "RCPT TO: " + to + "\r\n" +
                    "DATA\r\n" +
                    "Subject: " + subject + "\r\n" +
                    text + "\r\n.\r\n"
            ).getBytes());

            out.flush();
            var buffer = new byte[1024];
            var count = in.read(buffer);

            Thread.sleep(100);
            System.out.write(buffer, 0, 100);
        }


    }

        public void close() throws IOException {
            var out = socket.getOutputStream();
            out.write("QUIT\r\n".getBytes());
            out.flush();
            socket.close();

        }
    }








