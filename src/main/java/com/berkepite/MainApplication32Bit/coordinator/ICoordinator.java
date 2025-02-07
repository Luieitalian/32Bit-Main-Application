package com.berkepite.MainApplication32Bit.coordinator;

import com.berkepite.MainApplication32Bit.status.ConnectionStatus;
import com.berkepite.MainApplication32Bit.rates.IRate;
import com.berkepite.MainApplication32Bit.rates.RateEnum;
import com.berkepite.MainApplication32Bit.status.RateStatus;
import com.berkepite.MainApplication32Bit.subscribers.ISubscriber;

public interface ICoordinator {
    void onConnect(ISubscriber subscriber, ConnectionStatus status);

    void onSubscribe(ISubscriber subscriber, ConnectionStatus status);

    void onUnSubscribe(ISubscriber subscriber, ConnectionStatus status);

    void onDisConnect(ISubscriber subscriber);

    void onRateAvailable(ISubscriber subscriber, RateEnum rate);

    void onRateUpdate(ISubscriber subscriber, IRate rate);

    void onRateError(ISubscriber subscriber, RateStatus status);

    void onConnectionError(ISubscriber subscriber, ConnectionStatus status);
}
