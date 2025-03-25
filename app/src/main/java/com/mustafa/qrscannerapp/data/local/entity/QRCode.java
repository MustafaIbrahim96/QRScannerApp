package com.mustafa.qrscannerapp.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "qr_codes")
public class QRCode {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String content;
    private long timestamp;
    private boolean isFavorite;

    public QRCode(String content, long timestamp, boolean isFavorite) {
        this.content = content;
        this.timestamp = timestamp;
        this.isFavorite = isFavorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
