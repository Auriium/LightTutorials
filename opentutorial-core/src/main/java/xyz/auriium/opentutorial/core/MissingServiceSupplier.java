package xyz.auriium.opentutorial.core;

import java.util.function.Supplier;

public class MissingServiceSupplier implements Supplier<RuntimeException> {

    private final String service;

    public MissingServiceSupplier(String service) {
        this.service = service;
    }


    @Override
    public RuntimeException get() {
        return new MissingServiceException("Service " + service + " was not found in the platform service registry!");
    }
}
