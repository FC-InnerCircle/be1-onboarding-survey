package lshh.be1onboardingsurvey.common.lib.clock;

import java.time.LocalDateTime;

public class ClockImplement implements Clock{

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
