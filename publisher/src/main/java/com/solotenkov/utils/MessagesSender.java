package com.solotenkov.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solotenkov.entity.Message;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MessagesSender{
    private final String urlName;
    private final String reqMethod;
    private static Logger logger = Logger.getLogger(MessagesSender.class);

    public MessagesSender(String urlName, String reqMethod) {
        this.urlName = urlName;
        this.reqMethod = reqMethod;
    }

    private HttpURLConnection installation() throws IOException {
        URL url = null;
        HttpURLConnection con = null;
        try {
            url = new URL(urlName);
            con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod(reqMethod);
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            con.connect();
        } catch (MalformedURLException e) {
            logger.error("Unknown protocol:", e);
        }

        return con;
    }


    public void sendMessage(Message message) throws IOException {
        HttpURLConnection con = installation();
        DataOutputStream out1 = new DataOutputStream(con.getOutputStream());
        logger.info("Отправили: " + message);
        out1.writeBytes(getParamString(message));
        out1.flush();
        out1.close();
        getResponse(con);
    }

    private static String getParamString(Message message) {
        ObjectMapper mapper = new ObjectMapper();
        String result = null;
        try {
            result = mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            logger.error("Problems encountered when processing " +
                    "(parsing, generating) JSON content:", e);
        }
        return result;
    }

    private void getResponse(HttpURLConnection con){
        try {
            if (HttpURLConnection.HTTP_OK == con.getResponseCode()) {
                InputStreamReader inputStreamReader = new InputStreamReader(con.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    logger.info("Received answer: "+ line);
                }
            }
        } catch (IOException e) {
            logger.error("Error receiving response!", e);
        }
    }

}
