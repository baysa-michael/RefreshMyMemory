package com.refreshmymemory.control;

public interface ServletConnectionListener {
    void onServerResponse(boolean isSuccess, String message);
}
