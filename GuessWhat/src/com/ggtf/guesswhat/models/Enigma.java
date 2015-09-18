package com.ggtf.guesswhat.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ggtf on 2015/9/18.
 * Author:GGTF
 * Email:15170069952@163.com
 * Time:2015/9/18
 * ProjectName:GuessWhat
 */
public class Enigma {
    private String title;
    private String answer;
    private String id;

    public void parseJson(JSONObject jsonObject) throws JSONException {
        title = jsonObject.getString("Title");
        answer = jsonObject.getString("Answer");
        id = jsonObject.getString("id");
    }

    public String getTitle() {
        return title;
    }

    public String getAnswer() {
        return answer;
    }

    public String getId() {
        return id;
    }
}
