package lshh.be1onboardingsurvey.common.lib.sequnce.local;

import lshh.be1onboardingsurvey.common.lib.sequnce.SequenceClient;

import java.util.Map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class LocalSequenceClient implements SequenceClient {
    private final Map<String, AtomicLong> sequenceMap;

    public LocalSequenceClient() {
        this.sequenceMap = new ConcurrentHashMap<>();
    }

    @Override
    public Long nextLongValue(String sequenceName) {
        return sequenceMap.computeIfAbsent(sequenceName, k -> new AtomicLong(0)).incrementAndGet();
    }
}
