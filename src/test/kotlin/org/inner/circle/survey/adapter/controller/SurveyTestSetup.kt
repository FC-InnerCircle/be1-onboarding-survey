package org.inner.circle.survey.adapter.controller

class SurveyTestSetup {

    fun initSurveyCreateParameter(): Map<String, Any> {
        return mapOf(
            "title" to "설문조사 제목",
            "description" to "설문조사 설명",
            "writer" to "작성자",
            "questions" to
                    listOf(
                        mapOf(
                            "title" to "짧은 질문1",
                            "type" to "SHOT",
                            "description" to "질문 설명",
                            "options" to
                                    listOf(
                                        mapOf(
                                            "content" to "옵션 내용",
                                            "orderNumber" to 1,
                                        ),
                                    ),
                            "isRequired" to true,
                            "orderNumber" to 1,
                        ),
                        mapOf(
                            "title" to "복수선택 질문",
                            "type" to "MULTIPLE",
                            "description" to "질문 설명",
                            "options" to
                                    listOf(
                                        mapOf(
                                            "content" to "선택1",
                                            "orderNumber" to 1,
                                        ),
                                        mapOf(
                                            "content" to "선택2",
                                            "orderNumber" to 2,
                                        ),
                                        mapOf(
                                            "content" to "선택3",
                                            "orderNumber" to 3,
                                        ),
                                    ),
                            "isRequired" to false,
                            "orderNumber" to 2,
                        ),
                    ),
        )
    }
}