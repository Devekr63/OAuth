package com.appsdeveloperblog.ws.clients.data;

public record Album(
        String userId,
        String albumTitle,
        String albumId,
        String albumDescription,
        String albumUrl
){}
