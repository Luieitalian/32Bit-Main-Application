package com.berkepite.MainApplication32Bit.rates;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile({"dev"})
public class RateCacheServiceSB implements IRateCacheService {
    @Override
    @Cacheable(value = "rates", key = "'rates:' + #rate.provider.toString() + ':' + #rate.type.toString()")
    public CalculatedRate getCalcRate(CalculatedRate rate) {
        return null;
    }

    @Override
    @Cacheable(value = "raw_rates", key = "'rates:' + #rate.provider.toString() + ':' + #rate.type.toString()")
    public RawRate getRawRate(RawRate rate) {
        return rate;
    }

    @Override
    @CachePut(value = "rates", key = "'rates:' + #rate.provider.toString() + ':' + #rate.type.toString()")
    public CalculatedRate saveCalcRate(CalculatedRate rate) {
        return rate;
    }

    @Override
    public Double saveUSDMID(Double value) {
        return 0.0;
    }

    @Override
    public Double getUSDMID() {
        return 0.0;
    }

    @Override
    @CachePut(value = "raw_rates", key = "'rates:' + #rate.provider.toString() + ':' + #rate.type.toString()")
    public RawRate saveRawRate(RawRate rate) {
        return rate;
    }

    @Override
    public List<RawRate> getAllRawRatesForType(String type) {
        return List.of();
    }
}