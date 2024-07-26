package com.innercircle.survey.infrastruture.survey;

import com.innercircle.survey.domain.survey.SelectOption;
import com.innercircle.survey.infrastruture.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "select_option")
public class SelectOptionEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long selectOptionId;

    private String selectItemName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_item_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private SurveyItemEntity surveyItem;


    public static SelectOptionEntity toEntity(SelectOption selectOption) {

        return SelectOptionEntity.builder()
                .selectOptionId(selectOption.getSelectOptionId() != null ? selectOption.getSelectOptionId() : null)
                .surveyItem(SurveyItemEntity.toEntity(selectOption.getSurveyItem()))
                .selectItemName(selectOption.getSelectItemName())
                .build();
    }

    public SelectOption toDomain() {

        return SelectOption.builder()
                .selectOptionId(selectOptionId)
                .surveyItem(surveyItem.toDomain())
                .selectItemName(selectItemName)
                .build();
    }
}
