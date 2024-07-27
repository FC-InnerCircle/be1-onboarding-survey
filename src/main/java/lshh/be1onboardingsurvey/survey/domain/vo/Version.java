package lshh.be1onboardingsurvey.survey.domain.vo;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@Embeddable
public record Version<Entity> (
        LocalDateTime created,
        LocalDateTime overwritten,
        @OneToOne(fetch = FetchType.LAZY)
        Entity previous
){
    public static <Entity> Version<Entity> forCreate(LocalDateTime now){
        return Version.<Entity>builder()
                .created(now)
                .build();
    }

    public static <Entity> Version<Entity> forUpdate(Entity previous, LocalDateTime now){
        return Version.<Entity>builder()
                .created(now)
                .previous(previous)
                .build();
    }

    public static <Entity> Version<Entity> forOverwriten(Version<Entity> prestate, LocalDateTime now){
        var created = prestate == null ? null : prestate.created();
        var previous = prestate == null ? null : prestate.previous();
        return Version.<Entity>builder()
                .created(created)
                .previous(previous)
                .overwritten(now)
                .build();
    }


}
