package xyz.auriium.opentutorial.core.consumer;

import xyz.auriium.opentutorial.core.MissingServiceException;

import java.util.function.Supplier;

public class StageMissingServiceSupplier implements Supplier<RuntimeException> {

    private final String service;

    public StageMissingServiceSupplier(String service) {
        this.service = service;
    }

    @Override
    public RuntimeException get() {
        return new MissingServiceException("Service " + service + " was not found in the platform service registry!");
    }

}
