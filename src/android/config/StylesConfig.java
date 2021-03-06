package com.manifoldjs.webapptoolkit.config;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

public class StylesConfig {
  private String customCssFile = "";
  private List<String> hiddenElements = new ArrayList<String>();
  private String customString = "";
  private boolean enabled = false;

  public StylesConfig() {
  }

  public StylesConfig(JSONObject manifestObject) {
    if (manifestObject != null) {
      if (manifestObject.has("customCssFile")) {
        this.enabled = true;
        this.customCssFile = manifestObject.optString("customCssFile");
      }

      if (manifestObject.has("hiddenElements")) {
        JSONArray hiddenElements = manifestObject.optJSONArray("hiddenElements");

        if (hiddenElements != null && hiddenElements.length() > 0) {
          this.enabled = true;

          for (int i = 0; i < hiddenElements.length(); i++) {
            this.hiddenElements.add(hiddenElements.optString(i));
          }
        }
      }

      if (manifestObject.has("customCssString")) {
        this.enabled = true;
        this.customString = manifestObject.optString("customCssString");
      }
    }
  }

  public String getCssFile() {
    return this.customCssFile;
  }

  public String getCustomString() {
    return this.customString;
  }

  public String getInlineStyles() {
    StringBuilder builder = new StringBuilder();
    if (this.hiddenElements.size() > 0){
      for (String hiddenElement: this.hiddenElements){
        builder.append(hiddenElement);
        builder.append(",");
      }
      builder.deleteCharAt(builder.length() - 1);

      builder.append("{display:none !important;}");
    }

    builder.append(this.customString);
    return builder.toString();
  }

  public boolean isEnabled() {
    return this.enabled;
  }
}
