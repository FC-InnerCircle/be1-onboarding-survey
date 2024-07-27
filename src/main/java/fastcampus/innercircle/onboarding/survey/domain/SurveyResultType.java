package fastcampus.innercircle.onboarding.survey.domain;

public enum SurveyResultType {
    SHORT {
        public boolean isSubjectiveType() {
            return true;
        }
    },
    LONG {
        public boolean isSubjectiveType() {
            return true;
        }
    },
    SINGLE {
        public boolean isSubjectiveType() {
            return false;
        }
    },
    MULTI {
        public boolean isSubjectiveType() {
            return false;
        }
    };

    public abstract boolean isSubjectiveType();
}
