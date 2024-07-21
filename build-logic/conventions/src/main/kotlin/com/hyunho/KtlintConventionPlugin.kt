import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jlleitschuh.gradle.ktlint.KtlintExtension

class KtlintConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.apply("org.jlleitschuh.gradle.ktlint")

        target.extensions.configure<KtlintExtension> {
            version.set("0.50.0")
            verbose.set(true)
            android.set(false)
            outputToConsole.set(true)
            outputColorName.set("RED")
            ignoreFailures.set(false)
            enableExperimentalRules.set(false)
            disabledRules.set(setOf("no-wildcard-imports"))
        }
    }
}
