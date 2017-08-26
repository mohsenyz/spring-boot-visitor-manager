/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.pojo;

import com.fasterxml.jackson.databind.JsonNode;
import com.utils.interfaces.Parsable;

/**
 *
 * @author mphj
 */
public class Docs_1 implements Parsable<JsonNode>{
    public String desc;
    public String file;

    @Override
    public void parse(JsonNode content) {
        desc = content.get("desc").asText();
        file = content.get("file").asText();
    }
}
