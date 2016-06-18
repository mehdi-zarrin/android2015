package com.example.mehdi.android2015.services;

import android.os.Handler;

import com.example.mehdi.android2015.core.ApplicationBase;
import com.squareup.otto.Bus;

import java.util.Random;

public abstract class BaseInMemoryService {
    protected final Bus bus;
    protected final ApplicationBase application;
    protected final Handler handler;
    protected final Random random;

    public BaseInMemoryService(ApplicationBase application) {
        this.application = application;
        this.bus = application.getBus();
        this.handler = new Handler();
        this.random = new Random();
        bus.register(this);
    }

    protected void invokeDelayed(Runnable runnable, long milisecondMin, long milisecondMax) {
        if(milisecondMin > milisecondMax)
            throw new IllegalArgumentException("Min must be smaller than max");
        long delay = (long) (random.nextDouble() * (milisecondMax - milisecondMin)) + milisecondMin;
        handler.postDelayed(runnable, delay);
    }

    protected void postWithDelay(final Object event, long milisecondMin, long milisecondMax) {
        invokeDelayed(new Runnable() {
            @Override
            public void run() {
                bus.post(event);
            }
        }, milisecondMin, milisecondMax);
    }


    protected void postWithDelay(Object event, long miliseconds) {
        postWithDelay(event, miliseconds, miliseconds);
    }

    protected void postWithDelay(Object event) {
        postWithDelay(event, 600, 1200);
    }
}
