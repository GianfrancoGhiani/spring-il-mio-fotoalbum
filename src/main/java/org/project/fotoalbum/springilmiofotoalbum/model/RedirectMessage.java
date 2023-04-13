package org.project.fotoalbum.springilmiofotoalbum.model;

public class RedirectMessage {
    public enum RedirectMessageType {
        SUCCESS, ERROR
    }
    private RedirectMessageType type;
    private String content;

    public RedirectMessage(){

    }
    public RedirectMessage(RedirectMessageType type, String content) {
        this.type = type;
        this.content = content;
    }

    public RedirectMessageType getType() {
        return type;
    }

    public void setType(RedirectMessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
