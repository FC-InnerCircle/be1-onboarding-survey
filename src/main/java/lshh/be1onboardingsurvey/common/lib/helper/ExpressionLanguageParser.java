package lshh.be1onboardingsurvey.common.lib.helper;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class ExpressionLanguageParser {
    private ExpressionLanguageParser() {
    }

    public static Object getDynamicValue(String[] parameterNames, Object[] args, String keyExpression) {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();

        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }

        return parser.parseExpression(keyExpression).getValue(context, Object.class);
    }
}