package com.mibanma.api;

public class Traduire {
	private String textFr;
    private String langue;
    private String textLangue;
    private String audioUrl;
    
    public Traduire(String textFr, String textLangue) {
        this.textFr = textFr;
        this.textLangue = textLangue;
    }

    public Traduire() {
    }

    public String getTextFr() {
        return textFr;
    }

    public void setTextFr(String textFr) {
        this.textFr = textFr;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getTextLangue() {
        return textLangue;
    }

    public void setTextLangue(String textLangue) {
        this.textLangue = textLangue;
    }
    public String getAudioUrl()
    {
    	return audioUrl;
    }
    public void setAudioUrl(String urlAudio)
    {
    	this.audioUrl = urlAudio;
    }
}
