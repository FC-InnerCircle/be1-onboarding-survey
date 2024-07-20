package lshh.be1onboardingsurvey.survey.domain.command;

public record CreateSurveyCommand(
        String name,
        String description
) {
    public CreateSurveyCommand(String name, String description){
        if(name == null || name.isBlank())
            throw new IllegalArgumentException("Name cannot be null or empty");
        if(description == null || description.isBlank())
            throw new IllegalArgumentException("Description cannot be null or empty");
        this.name = name;
        this.description = description;
    }
}
