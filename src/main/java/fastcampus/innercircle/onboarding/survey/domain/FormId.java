package fastcampus.innercircle.onboarding.survey.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Embeddable
public class FormId implements Serializable {
    @Column(name = "FORM_ID")
    private UUID id;

    @Column(name = "VERSION")
    private Long version;

    public void versionUp() {
        this.version++;
    }

    public static FormId createFormId() {
        return new FormId(UUID.randomUUID(), 1L);
    }
}
