package com.ProductSystem.Entity;

import org.springframework.core.io.Resource;

public class LoadedImage {
    private final Resource resource;
    private final String contentType;


    public LoadedImage(Resource resource, String contentType) {
        this.resource = resource;
        this.contentType = contentType;
    }

    public Resource getResource() {
        return resource;
    }

    public String getContentType() {
        return contentType;
    }
}