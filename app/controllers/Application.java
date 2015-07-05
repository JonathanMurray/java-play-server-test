package controllers;

import play.libs.F;
import play.mvc.Controller;
import play.mvc.WebSocket;

import java.util.ArrayList;
import java.util.List;

public class Application extends Controller {

    private List<String> values = new ArrayList<>();

    public WebSocket<String> connect() {
        return new WebSocket<String>() {
            public void onReady(WebSocket.In<String> in, WebSocket.Out<String> out) {
                in.onMessage(new F.Callback<String>() {
                    @Override
                    public void invoke(String s) throws Throwable {
                        values.add(s);
                    }
                });

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(true){
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            out.write(values.toString());
                        }
                    }
                }).start();
            }

        };
    }
    
}
