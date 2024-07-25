package lshh.be1onboardingsurvey.survey.domain.vo;

import lshh.be1onboardingsurvey.survey.domain.entity.SurveyItemFormType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SurveyResponseItemValueConverterTest {
    private final SurveyResponseItemValueConverter converter = new SurveyResponseItemValueConverter();

    @Nested
    @DisplayName("Entity 필드값 형태로 변환")
    class ConvertToEntityAttributeTest{
        @Test
        @DisplayName("TEXT 타입으로 변환")
        public void testConvertToEntityAttributeText() {
            String dbData = "TEXT::Test message";
            SurveyResponseItemValue<?> attribute = converter.convertToEntityAttribute(dbData);

            assertEquals(SurveyItemFormType.TEXT, attribute.type());
            assertInstanceOf(String.class, attribute.value());
            assertEquals("Test message", attribute.value());
        }
        @Test
        @DisplayName("TEXTAREA 타입으로 변환")
        public void testConvertToEntityAttributeTextarea() {
            String dbData = "TEXTAREA::Test message";
            SurveyResponseItemValue<?> attribute = converter.convertToEntityAttribute(dbData);

            assertEquals(SurveyItemFormType.TEXTAREA, attribute.type());
            assertInstanceOf(String.class, attribute.value());
            assertEquals("Test message", attribute.value());
        }
        @Test
        @DisplayName("RADIO 타입으로 변환")
        public void testConvertToEntityAttributeRadio() {
            String dbData = "RADIO::3";
            SurveyResponseItemValue<?> attribute = converter.convertToEntityAttribute(dbData);

            assertEquals(SurveyItemFormType.RADIO, attribute.type());
            assertInstanceOf(Long.class, attribute.value());
            assertEquals(3L, attribute.value());
        }
        @Test
        @DisplayName("CHECKBOX 타입으로 변환")
        public void testConvertToEntityAttributeCheckbox() {
            String dbData = "CHECKBOX::[1, 2]";
            SurveyResponseItemValue<?> attribute = converter.convertToEntityAttribute(dbData);

            assertEquals(SurveyItemFormType.CHECKBOX, attribute.type());
            assertInstanceOf(Long[].class, attribute.value());
            Long[] values = (Long[])attribute.value();
            assertEquals(2, values.length);
            assertEquals(1, values[0]);
            assertEquals(2, values[1]);
        }
    }

    @Nested
    @DisplayName("DB 데이터 형태로 변환")
    class ConvertToDatabaseColumnTest{
        @Test
        @DisplayName("TEXT 타입으로 변환")
        public void testConvertToDatabaseColumnText() {
            SurveyResponseItemValue<?> attribute = SurveyResponseItemValue.of(SurveyItemFormType.TEXT, "Test message");
            String dbData = converter.convertToDatabaseColumn(attribute);

            System.out.println(dbData);
            assertEquals("TEXT::Test message", dbData);
        }
        @Test
        @DisplayName("TEXTAREA 타입으로 변환")
        public void testConvertToDatabaseColumnTextarea() {
            SurveyResponseItemValue<?> attribute = SurveyResponseItemValue.of(SurveyItemFormType.TEXTAREA, "Test message");
            String dbData = converter.convertToDatabaseColumn(attribute);

            assertEquals("TEXTAREA::Test message", dbData);
        }
        @Test
        @DisplayName("RADIO 타입으로 변환")
        public void testConvertToDatabaseColumnRadio() {
            SurveyResponseItemValue<?> attribute = SurveyResponseItemValue.of(SurveyItemFormType.RADIO, 3L);
            String dbData = converter.convertToDatabaseColumn(attribute);

            assertEquals("RADIO::3", dbData);
        }

        @Test
        @DisplayName("CHECKBOX 타입으로 변환")
        public void testConvertToDatabaseColumnCheckbox() {
            SurveyResponseItemValue<?> attribute = SurveyResponseItemValue.of(SurveyItemFormType.CHECKBOX, new Long[]{1L, 2L});
            String dbData = converter.convertToDatabaseColumn(attribute);

            assertEquals("CHECKBOX::[1, 2]", dbData);
        }
    }
}
