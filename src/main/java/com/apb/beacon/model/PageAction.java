package com.apb.beacon.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aoe on 12/24/13.
 */
public class PageAction {
    private String title;
    private String link;
    private String status;

    public PageAction() {
    }

    public PageAction(String title, String link, String status) {
        this.title = title;
        this.link = link;
        this.status = status;
    }

    public static List<PageAction> parsePageItems(JSONArray actionArray){
        List<PageAction> actionList = new ArrayList<PageAction>();

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        try {
            for(int i=0; i < actionArray.length(); i++){

                JSONObject thisActionItem = actionArray.getJSONObject(i);
                if(thisActionItem != null){
                    String jsonString = thisActionItem.toString();
                    PageAction item = gson.fromJson(jsonString, PageAction.class);
                    actionList.add(item);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return actionList;
    }

    public static PageAction parsePageAction(JSONObject actionObj) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        if (actionObj != null) {
            String jsonString = actionObj.toString();
            PageAction action = gson.fromJson(jsonString, PageAction.class);
            return action;
        }

        return null;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}