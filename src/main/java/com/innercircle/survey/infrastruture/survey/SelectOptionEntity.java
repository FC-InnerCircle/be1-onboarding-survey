package com.innercircle.survey.infrastruture.survey;

import com.innercircle.survey.infrastruture.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "select_list")
public class SelectOptionEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long selectListId;

    private String selectListName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "select_option_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private SurveyItemOptionEntity selectOption;


}
