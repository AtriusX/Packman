package io.atri.packman.data.exception;

public class ResourcePathException extends RuntimeException {

    public ResourcePathException(String path) {
        super(String.format("Given path '%s' does not point to a directory!", path));
    }
}
