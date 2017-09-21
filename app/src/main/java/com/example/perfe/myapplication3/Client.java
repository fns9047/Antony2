package com.example.perfe.myapplication3;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by perfe on 5/09/2017.
 */

public class Client {

    Context context = this.getApplicationContext;
    String host;
    int port;
    int len;
    Socket socket = new Socket();
    byte buf[]= new byte[1024];

    try{
        /**
              * Create a client socket with the host,
              * port, and timeout information.
              */
        try {
            socket.bind(null);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            socket.connect((new InetSocketAddress(host, port)), 500);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        /**
              * Create a byte stream from a JPEG file and pipe it to the output stream
              * of the socket. This data will be retrieved by the server device.
              */
        OutputStream outputStream = socket.getOutputStream();
        ContentResolver cr = context.getContentResolver();
        InputStream inputStream = null;
        inputStream = cr.openInputStream(Uri.parse("path/to/picture.jpg"));
        while ((len = inputStream.read(buf)) != -1) {
            outputStream.write(buf, 0, len);
        }
        outputStream.close();
        inputStream.close();
    } catch (FileNotFoundException e) {
        //catch logic
    } catch(IOException e) {
        //catch logic
    }

/**
  * Clean up any open sockets when done
  * transferring or if an exception occurred.
  */
finally {
        if (socket != null) {
            if (socket.isConnected()) {
                try {
                    socket.close();
                } catch (IOException e) {
                    //catch logic
                }
            }
        }
    }


}


